package Daos;

import Dtos.Song;
import java.util.ArrayList;

/**
 *
 * @author Austin
 * @author Brian Millar
 */
public interface SongDaoInterface {
    public ArrayList<Song> getAllSongs();
    public void addNewSong(byte[] buffer);
}
