package sWave;

import Dtos.Song;
import java.util.ArrayList;

/**
 *
 * @author Brian Millar
 */
public class M3U8 {
    private String playlistFile;
    private ArrayList<Song> playlist;
    
    public M3U8(ArrayList<Song> playlist) {
        playlistFile = "#EXTM3U\n";
        this.playlist = playlist;
    }
}
