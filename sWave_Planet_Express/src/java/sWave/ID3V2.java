package sWave;

import Dtos.Song;
import java.util.Arrays;

/**
 *
 * @author Brian Millar
 */

//WARNING: Here be dragons, bright coloured fish and general insanity.

public class ID3V2 {
    
    //The data we'll be working with
    private static byte buffer[];
    
    //We'll want this to track where we are
    private static int currentByte;
    
    //The things we are looking for
    private static String frameIds;

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

        buffer = song.getSongdata();
        
        //==HEADER==FIRST====10=BYTES===========================================
        
        //First 3 bytes should be 'ID3' if the file contains ID3 data
        String id3Check = "" + (char)buffer[0] + (char)buffer[1] + (char)buffer[2];
        
        //Next 2 bytes gives the ID3 Version being used
        int majorVersion = buffer[3];
        int minorVersion = buffer[4];
        
        //Only continue if the needed conditions for extraction are met
        if (buffer != null && id3Check.equals("ID3") && majorVersion == 3) {
            //The flags byte contains bits that flag certain features as 'on' or 'off
            int flags[]      = dec2bin(buffer[5]); //posVal shouldn't matter here
            int unsync       = flags[7];
            int extHeader    = flags[6]; //Is there extended header data after the header?
            int experimental = flags[5];
            /*
                All other flags should be clear according to the spec.

                Most of this is useless anyway, I couldn't find a single MP3 
                that actually had any of these flags set...

                Anyway the next 4 bytes describe the tag size,
                the size is the total size of the ID3 tag excluding the header 
                so it should be the total - 10 bytes. The max is 256MB, however 
                we do not accept files greater than 16MB.
            */

            //Calculate the total tag size
            
            int totalSize  = calcID3TotalSize(buffer[6], buffer[7], buffer[8], buffer[9]);
            
            System.out.println("A " + (totalSize / 1024) + "kb ID3 Tag was found, attempting to extract data...");
            
        //==END=OF=HEADER=======================================================
        
        //From here we'll need to track where we are, the header was 10 bytes so 
        //lets continue from byte 11
        
            currentByte = 11;
        
        //==EXTENDED=HEADER=====================================================
        
            //Check that there is an extended header (there usually isn't)
            if (extHeader == 1) {
                //We don't need anything in the extHeader so ignoring for now
            }
        
        //==END=OF=EXTENDED=HEADER==============================================
        
            //Simply loop over the tag looking at every 4 byte group searching for
            //the frame ids we want.
            
            frameIds = "TIT2 TPE1 TCON TLEN TYER APIC";
            String currentSearch = "";
        
            while (currentByte < totalSize - 3) {

                currentSearch = "" + (char)buffer[currentByte]  + (char)buffer[currentByte + 1]  + (char)buffer[currentByte + 2] + (char)buffer[currentByte + 3];

                if (frameIds.contains(currentSearch)) {
                    switch (currentSearch) {
                        case "TIT2" :
                            song.setTitle(processTitle());
                            break;
                        case "TPE1" :
                            song.setArtist(processArtist());
                            break;
                        case "TCON" :
                            processGenre();
                            break;
                        case "TLEN" :
                            song.setDuration(processDuration());
                            break;
                        case "TYER" :
                            processYear();
                            break;
                        case "APIC" :
                            song.setArtwork(processArtwork());
                            break;
                    }
                }
                currentByte++;
            }
        }
    }
    
    private static int calcID3TotalSize(byte b, byte b0, byte b1, byte b2) {
        /*
            ID3 size information is represented across 4 bytes where the first 
            bit of each is ignored, so we need to build the representation as an 
            integer ourselves, it will be between 0 and 256MB as 7 bits is used 
            across 4 bytes:

            7*4 = 28 and 2^28 = 268435456b so 262144kb or 256mb.
            
            So we can represent 0 - 268435455b
        */
        
        int total = 0;
        int size[][] = {dec2bin(b), dec2bin(b0), dec2bin(b1), dec2bin(b2)};
        
        for (int j = 0; j < 4; j++)
            for (int i = 1; i < 8; i++)
                if (size[j][i] == 1)
                    total += Math.pow(2, (28 - (j * 7) - i));
        
        return total;
    }
    
    private static int calcID3Size(byte b, byte b0, byte b1, byte b2) {
        /*
            The same as above except we don't ignore the first bit of each.
            There is a special place in hell for whoever came up with this.
        */
        int total = 0;
        
        int size[][] = {dec2bin(b), dec2bin(b0), dec2bin(b1), dec2bin(b2)};
        
        for (int j = 0; j < 4; j++)
            for (int i = 0; i < 8; i++)
                if (size[j][i] == 1)
                    total += Math.pow(2, (31 - (j * 8) - i));
        
        return total;
    }
    
    private static int[] dec2bin(byte dec) {

        //If the MSB is 1 two's compliment will kick in and screw us up
        //I can't believe Java doesn't have unsigned types >.<
        
        boolean decwlt0 = false;
        
        if (dec < 0) {
            decwlt0  = true;
            byte mask = 1;
            mask <<= 7;
            dec = (byte)(dec ^ mask);
        }
        
        while(dec > 255)
            dec %= 2;
        
        int bin[] = new int[8];
        for (int i = 0; i < 8; i++)
            bin[i] = 0;
        int count = 7;
        while (dec > 0) {
            bin[count] = dec % 2;
            dec /= 2;
            count--;
        }

        if (decwlt0)
            bin[0] = 1;

        System.out.println(Arrays.toString(bin));
        
        return bin;
    }

    private static String processTitle() {
        System.out.println("Found Title");
        //Move past the frame ID
        currentByte += 4;
        int size = calcID3Size(buffer[currentByte], buffer[currentByte + 1], buffer[currentByte + 2], buffer[currentByte + 3]);
        //Move past the frame size & flags
        currentByte += 6;
        int encoding = (int)buffer[currentByte];
        currentByte++;
        String title = "";
        System.out.println(size - 1);
        //Using size - 1 to ignore the encoding byte
        for (int i = 0; i < (size - 1); i++) {
            title += (char)buffer[currentByte];
            currentByte++;
        }
        currentByte++;
        if (title.length() > 255)
            title = title.substring(0, 250) + "...";
        return title;
    }

    private static String processArtist() {
        System.out.println("Found Artist");
        //Move past the frame ID
        currentByte += 4;
        int size = calcID3Size(buffer[currentByte], buffer[currentByte + 1], buffer[currentByte + 2], buffer[currentByte + 3]);
        //Move past the frame size & flags
        currentByte += 6;
        int encoding = (int)buffer[currentByte];
        currentByte++;
        String artist = "";
        //Using size - 1 to ignore the encoding byte
        for (int i = 0; i < (size - 1); i++) {
            artist += (char)buffer[currentByte];
            currentByte++;
        }
        currentByte++;
        if (artist.length() > 255)
            artist = artist.substring(0, 250) + "...";
        return artist;
    }

    private static void processGenre() {
        System.out.println("Found Genre");
    }

    private static int processDuration() {
        System.out.println("Found Duration");
        //Move past the frame ID
        currentByte += 4;
        int size = calcID3Size(buffer[currentByte], buffer[currentByte + 1], buffer[currentByte + 2], buffer[currentByte + 3]);
        //Move past the frame size & flags
        currentByte += 6;
        int encoding = (int)buffer[currentByte];
        currentByte++;
        String duration = "";
        //Using size - 1 to ignore the encoding byte
        for (int i = 0; i < (size - 1); i++) {
            duration += (char)buffer[currentByte];
            currentByte++;
        }
        currentByte++;
        return Integer.parseInt(duration);
    }

    private static void processYear() {
        System.out.println("Found Year");
    }

    private static byte[] processArtwork() {
        /*
            There may be many APIC frames in the tag but we are just going to 
            take the first one and hope its pretty. The ID3V2.3 is really OTT 
            with the amount of crap you can have, it even specifies one type as 
            "A bright coloured fish". I can only assume the ID3 guys were high 
            when they came up with this spec.
        */
        System.out.println("Found Artwork");
        //Move past the frame ID
        currentByte += 4;
        int size = calcID3Size(buffer[currentByte], buffer[currentByte + 1], buffer[currentByte + 2], buffer[currentByte + 3]);
        //Move past the frame size & flags
        currentByte += 6;
        
        int headerStart = currentByte; //Keep track of the extra data
        //APICs have extra data to be skipped to get to the image
        currentByte += 1; //skip the text encoding
        
        //null-terminated mime-type string
        while (buffer[currentByte] != '\0') {
            System.out.print((char)buffer[currentByte]);
            currentByte++;
        }
        
        System.out.print('\n');

        currentByte++;
        System.out.println("Artwork Type: " + buffer[currentByte]);
        //Represents the type of artwork
        //Could it be a "A bright coloured fish"?
        currentByte++;
        //Another null-terminated string gives us the description
        System.out.print("Description: ");
        while (buffer[currentByte] != '\0') {
            System.out.print((char)buffer[currentByte]);
            currentByte++;
        }
        System.out.print('\n');
        currentByte++;
        //Finally!
        System.out.println("Size: " + size);
        System.out.println("Header: " + (currentByte - headerStart));
        
        byte artwork[] = new byte[size - (currentByte - headerStart)];
        for (int i = 0; i < artwork.length; i++) {
            artwork[i] = buffer[currentByte];
            currentByte++;
        }
        System.out.println(artwork.length / 1024);
        //Got it!
        //Ensure artwork is not processed again if there is more
        frameIds = "TIT2 TPE1 TCON TLEN TYER";
        return artwork;
    }
}
