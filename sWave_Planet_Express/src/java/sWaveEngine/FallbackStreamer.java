package sWaveEngine;

import Daos.SongDao;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author Brian Millar
 */
public class FallbackStreamer {
    /*
        In the case that the custom streaming solution is not completed on time 
        cannot be completed, or simply fails upon use we fall back to this.
        This solution grabs the song data blob from the database and writes it 
        out to a temporary file which we point the player to and allow HTML5 to 
        handle the streaming.
    */
    
    private static final boolean DEBUG = false;
    
    public static void start(int songid) {
        try {
            FileOutputStream output = new FileOutputStream("/temp/file");
            SongDao songs = new SongDao();
            byte buffer[] = (songs.getSongById(songid)).getSongdata();
            output.write(buffer);
        } catch (IOException ex) {
            if (DEBUG)
                ex.printStackTrace();
        }
    }
}
