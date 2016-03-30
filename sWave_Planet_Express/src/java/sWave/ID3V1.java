package sWave;

import Dtos.Song;

/**
 *
 * @author Brian Millar
 */
public class ID3V1 {
    
    private static final boolean DEBUG = sWave.Server.DEBUGGING;
    
    /*
        This will run first as a fallback if ID3V2 cannot find data, we will 
        load data from here first if we can find it then any ID3V2 data found 
        will overwrite what was found here.
    */
    
    public static void extractTags(Song song) {
        //The ID3V1 Spec is so beautiful and simple, it was perfection, sadly 
        //most files now use the monstrosity that is ID3V2.x however I've found 
        //a lot of files actually have both as V2 resides at the start of a file 
        //and V1 at the end.
        
        byte buffer[]   = song.getSongdata();
        int currentByte = buffer.length - 128;
        String hasTag   = "";
        
        for (int i = 0; i < 3; i++) {
            hasTag += (char)buffer[currentByte];
            currentByte++;
        }
        
        if (hasTag.equals("TAG")) {
            System.out.println("ID3V1 Tag Found, Extracting Data...");
            song.setTitle(extract30ByteText(currentByte, buffer));
            currentByte += 30;
            song.setArtist(extract30ByteText(currentByte, buffer));
            currentByte += 30;
            song.setAlbum(extract30ByteText(currentByte, buffer));
            currentByte += 30;
            String year = "" + (char)buffer[currentByte]     + 
                               (char)buffer[currentByte + 1] +
                               (char)buffer[currentByte + 2] +
                               (char)buffer[currentByte + 3];
            try {
                song.setYear(Integer.parseInt(year));
            } catch (NumberFormatException e) {
                System.out.println("Not a Year");
                if (DEBUG)
                    e.printStackTrace();
            }
            currentByte += 4;
            //We ignore the comment, we don't need it
            currentByte += 30;
            byte genre = buffer[currentByte]; //Need the lookup table for this
            if (currentByte == buffer.length - 1)
                System.out.println("Success");
        }
    }
    
    private static String extract30ByteText(int currentByte, byte buffer[]) {
        String text = "";
        for (int i = 0; i < 30; i++)
            text += (char)buffer[currentByte + i];
        return text;
    }
    
}
