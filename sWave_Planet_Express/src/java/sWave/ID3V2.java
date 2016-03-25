package sWave;

import Dtos.Song;

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
    public static Song extractTags(Song song) {
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
            byte  flags = buffer[5];
            
            //TODO: extract individual bits from flags byte
            int unsync       = 0;
            int extHeader    = 0; //Is there extended header data after the header?
            int experimental = 0;
            //All other flags should be clear according to the spec.
            
            //The next 4 bytes describe the tag size
            byte tagSize[] = {buffer[6], buffer[7], buffer[8], buffer[9]};
            
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
        return new Song();
    }

}
