package sWave;

import Dtos.Song;
import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 *
 * @author Brian Millar
 */

//WARNING: Here be dragons, bright coloured fish and general insanity.

public class ID3V2 {
    
    private static final boolean DEBUG = sWave.Server.DEBUGGING;

    private static ByteBuffer buffer;

    public static void extractTags(Song song) {
        /*
            This method extracts ID3V2.3 metadata from the MP3 data in a Song 
            object passed in and fills its fields with the information found in 
            the ID3 metadata.
        
            I built this ID3 extractor from the documentaion of the ID3 spec 
            found at id3.org. It is a stripped down, basic implementation of 
            ID3V2.3 that only extracts the information we need for our project, 
            irrelevant data is simply ignored.
        */
        
        /*
            Here we begin reading the ID3 Header from the start of the file, we 
            ensure the buffer is not null then check that the first 3 bytes of 
            it is 'ID3' otherwise the file does not have ID3 metadata in it.
        */

        buffer = ByteBuffer.wrap(song.getSongdata());
        
        //First 3 bytes should be 'ID3' if the file contains ID3 data
        String id3Check = "" + (char)buffer.get() + (char)buffer.get() + (char)buffer.get();
        
        //Next 2 bytes gives the ID3 Version being used
        int majorVersion = buffer.get();
        int minorVersion = buffer.get();

        if (buffer != null && id3Check.equals("ID3") && majorVersion == 3) {
            System.out.print("ID3V2 Tag Found, Extracting Data... ");
            //The flags byte contains bits that flag certain features as 'on' or 'off
            int flags[]      = dec2bin(buffer.get());
            int unsync       = flags[7];
            int extHeader    = flags[6];
            int experimental = flags[5];
            
            //All other flags should be clear according to the spec.

            //Next 4 bytes describe the tag size
            
            byte tagSize[] = {buffer.get(), buffer.get(), buffer.get(), buffer.get()};
            int totalSize  = calcID3Size(tagSize);
            
            if (buffer.position() != 10) {
                System.out.println("Unexpected Byte Position, Possible Header Corruption, Aborting...");
                return;
            }
            
            /*
                Check if there is an extended header (there usually isn't) and 
                if there is skip past it
            */

            if (extHeader == 1) {
                int extHeadSize = buffer.getInt();
                buffer.position(buffer.position() + extHeadSize);
            }

            String currentSearch = "";
        
            while (buffer.position() < totalSize - 4) {
                currentSearch = "" + (char)buffer.get() + 
                                     (char)buffer.get() + 
                                     (char)buffer.get() + 
                                     (char)buffer.get();

                switch (currentSearch) {
                    case "TIT2" :
                        song.setTitle(processTextFrame("TIT2"));
                        break;
                    case "TPE1" :
                        song.setArtist(processTextFrame("TPE1"));
                        break;
                    case "TALB" :
                        song.setAlbum(processTextFrame("TALB"));
                        break;
                    case "TCON" : 
                        song.setGenre(processGenre());
                        break;
                    case "TLEN" :
                        try {
                            song.setDuration(Integer.parseInt(processTextFrame("TLEN")));
                        } catch (NumberFormatException e) {
                            if (DEBUG)
                                System.out.println("Not a Valid Length");
                        }
                        break;
                    case "TYER" :
                        try {
                            song.setYear(Integer.parseInt(processTextFrame("TYER")));
                        } catch (NumberFormatException e) {
                            if (DEBUG)
                                System.out.println("Not a Year");
                        }
                        break;
                    case "TCOP" :
                        song.setLicense(processTextFrame("TCOP"));
                        break;
                    case "APIC" :
                        song.setArtwork(processArtwork());
                        break;
                    default :
                        buffer.position(buffer.position() - 3);
                }
            }
            System.out.println("Success");
        }
    }
    
    private static int calcID3Size(byte bytes[]) {
        /*
            ID3 size information is represented across 4 bytes where the first 
            bit of each is ignored, so we need to build the representation as an 
            integer ourselves, it will be between 0 and 256MB as 7 bits is used 
            across 4 bytes:

            7*4 = 28 and 2^28 = 268435456b so 262144kb or 256mb.
            
            So we can represent 0 - 268435455b
        */
        
        int total = 0;
        int size[][] = {dec2bin(bytes[0]),
                        dec2bin(bytes[1]),
                        dec2bin(bytes[2]),
                        dec2bin(bytes[3])};
        
        for (int j = 0; j < 4; j++)
            for (int i = 1; i < 8; i++)
                if (size[j][i] == 1)
                    total += Math.pow(2, (28 - (j * 7) - i));
        
        return total;
    }
    
    private static int[] dec2bin(byte dec) {
        //Since we ignore the 8th bit we can 0 it to ensure two's complement 
        //doesn't mess things up in the case that there might be a 1 there.
        //We do this by using a logical AND with 127.
        dec &= 127;
        int bin[]  = new int[8];
        for (int i = 0; i < 8; i++)
            bin[i] = 0;
        int count = 7;
        while (dec > 0) {
            bin[count] = dec % 2;
            dec /= 2;
            count--;
        }
        return bin;
    }

    private static String processTextFrame(String frameId) {
        System.out.println("Found " + frameId);
        int size = buffer.getInt();
        int flags[] = {buffer.get(), buffer.get()};
        int encoding = buffer.get();
        String text = "";
        for (int i = 0; i < (size - 1); i++)
            text += (char)buffer.get();
        if (text.length() > 255)
            text = text.substring(0, 250) + "...";
        return text;
    }
    
    private static String processGenre() {
        String genre = processTextFrame("TCON");
        if (!genre.isEmpty()) {
            //An opening bracket tells us that an ID3V1 genre ID is used
            if (genre.charAt(0) == '(') {
                String genreID = "";
                if (genre.charAt(2) == ')')
                    genreID += genre.charAt(1);
                else if (genre.charAt(3) == ')')
                    genreID += "" + genre.charAt(1) + genre.charAt(2);
                genre = ID3V1.lookupGenre(Byte.parseByte(genreID));
            }
        }
        return genre;
    }

    private static byte[] processArtwork() {
        /*
            There may be many APIC frames in the tag but we are just going to 
            take the last one and hope it's pretty. The ID3V2.3 says it could 
            be a "A bright coloured fish" so fingers crossed.
        */
        System.out.println("Found APIC");
        int size     = buffer.getInt();
        int flags[]  = {buffer.get(), buffer.get()};
        int encoding = buffer.get();
        
        int headerStart = buffer.position();
        buffer.get(); //skip 1 byte of encoding information
        
        //null-terminated mime-type string
        char mimeChar = (char)buffer.get();
        String mime = "";
        
        while (mimeChar != '\0') {
            mime += mimeChar;
            mimeChar = (char)buffer.get();
        }
        
        byte artType = buffer.get();
        
        //Another null-terminated string gives us the description
        char descChar = (char)buffer.get();
        String desc = "";
        
        while (descChar != '\0') {
            desc += descChar;
            descChar = (char)buffer.get();
        }
        
        byte artwork[] = new byte[size - (buffer.position() - headerStart)];
        buffer.get(artwork);

        return artwork;
    }
}
