package sWave;

import Dtos.Song;
import java.util.ArrayList;

/**
 *
 * @author Brian Millar
 */
public class M3U8 {
    private String fileContents;
    private ArrayList<Song> playlist;
    
    public M3U8(ArrayList<Song> playlist) {
        fileContents = "#EXTM3U\n";
        this.playlist = playlist;
        playlist.stream().forEach((Song s) -> {
            fileContents += "#EXTINF:" +
                            s.getDuration() + ","   +
                            s.getArtist()   + " - " +
                            s.getTitle()    + '\n'  +
                            s.getFilename() + '\n';
        });
    }
}
