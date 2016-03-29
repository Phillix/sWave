package sWave;

import Dtos.Song;

/**
 *
 * @author Brian Millar
 */
public class ID3V2 {

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

        byte buffer[] = song.getSongdata();
        
        //==HEADER==FIRST====10=BYTES===========================================
        
        //First 3 bytes should be 'ID3' if the file contains ID3 data
        String id3Check = "" + (char)buffer[0] + (char)buffer[1] + (char)buffer[2];
        
        //Next 2 bytes gives the ID3 Version being used
        int majorVersion = buffer[3];
        int minorVersion = buffer[4];
        
        //Only continue if the needed conditions for extraction are met
        if (buffer != null && id3Check.equals("ID3") && majorVersion == 3) {
            //The flags byte contains bits that flag certain features as 'on' or 'off
            int flags[]      = dec2bin(buffer[5]);
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
            
            int totalSize  = 0;
            int tagSize[][] = {dec2bin(buffer[6]), dec2bin(buffer[7]), dec2bin(buffer[8]), dec2bin(buffer[9])};
            
            /*
                The size is represented across 4 bytes where the first bit of 
                each is ignored, so we need to build the representation as an 
                integer ourselves, it will be between 0 and 256MB as 7 bits is 
                used across 4 bytes:

                7*4 = 28 and 2^28 = 268435456b so 262144kb or 256mb.
            
                So we can represent 0 - 268435455b
            */

            //Calculate the total tag size
            for (int j = 0; j < 4; j++)
                for (int i = 1; i <= 7; i++)
                    if (tagSize[j][i] == 1)
                        totalSize += Math.pow(2, (28 - (j * 7) - i));
            
            System.out.println("A " + (totalSize / 1024) + "kb ID3 Tag was found, attempting to extract data...");
            
        //==END=OF=HEADER=======================================================
        
        //==EXTENDED=HEADER=====================================================
        
            //Check that there is an extended header (there usually isn't)
            if (extHeader == 1) {
                
            }
        
        //==END=OF=EXTENDED=HEADER==============================================
        
        //==DECLARATION=OF=DATA=WE=WANT=========================================
        /*
            String tags[][] = new String[7][2];
            tags[0][0] = "TIT2"; //Title
            tags[0][1] = "TPE1"; //Artist
            tags[0][2] = ""; //Album
            tags[0][3] = "TCON"; //First Byte should be Genre
            tags[0][4] = "TLEN"; //Duration
            tags[0][5] = "TYER"; //Year
            tags[0][6] = "APIC"; //Artwork
        */
        
        //==END=OF=DECLARATIONS=================================================
        
        //==ID3=FRAMES==========================================================
        
        //==END=OF=ID3=FRAMES===================================================
        
        //==SET=THE=DATA========================================================
            
            //Set the data we found
            /*
            if (tags[1][0] != null)
                song.setTitle(tags[1][0]);
            
            if (tags[1][1] != null)
                song.setArtist(tags[1][1]);

            if (tags[1][2] != null)
                song.setAlbum(tags[1][2]);
            
            if (tags[1][3] != null)
                song.setGenre(tags[1][3]);
            
            if (tags[1][4] != null)
                song.setDuration(tags[4]);
            
            if (tags[1][5] != null)
                song.setYear(tags[1][5]);
            
            if (tags[1][6] != null)
                song.setArtwork(tags[1][6]);
            */
        //==END=OF=DATA=SETTING=================================================
              
        }
    }
    
    private static int[] dec2bin(byte dec) {
        int bin[] = new int[8];
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
}
