package sWave;

import Dtos.Song;
import java.util.Arrays;

/**
 *
 * @author Brian Millar
 */
public class ID3V2 {

    /**
     * 
     * @param song
     * @return a song object with metadata set
     */
    public static void extractTags(Song song) {
        /*
            This method extracts ID3 metadata from the MP3 data in a Song object 
            and returns a Song object with the same MP3 data but with the other 
            fields filled with the information found in the ID3 metadata.
        
            I built this ID3 extractor from the documentaion of the ID3 spec 
            found at id3.org. It is a basic implementation of ID3V2.3 that only 
            extracts the information we need for our project, irrelevant data is 
            simply ignored.
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

                Anyway the next 4 bytes describe the tag size:
                We don't need this information yet so lets skip it.
            */
            
            //Lets do a quick and dirty search for tags we want
            //Replace buffer.length later with the actual tag length
            
            //Temp hacking
            
            for (int i = 11; i < buffer.length - 4; i += 4) {
                String frame = "" + (char)buffer[i] + (char)buffer[i + 1] + (char)buffer[i + 2] + (char)buffer[i + 3];
                if (frame.equals("TIT2"))
                    System.out.println("Title Found");
                else if (frame.equals("TPE1"))
                    System.out.println("Artist Found");
                else if (frame.equals("TCON"))
                    System.out.println("Genre Found");
                else if (frame.equals("TLEN"))
                    System.out.println("Duration Found");
                else if (frame.equals("TYER"))
                    System.out.println("Year Found");
                else if (frame.equals("APIC"))
                    System.out.println("Artwork Found");
            }

            //==================================================================
            
            /*
                If the extended Header is not used we can start processing ID3V2 
                frames from here. The frames we really want for sWave are:
                    TIT2 - Title
                    TPE1 - Artist
                    TCON - First byte should give us the genre
                    TLEN - Duration in millisecs
                    
                    TYER - Year
                    WCOP/TCOP - Copyright Info
            */
              
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
