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
    public int addNewSong(Song s);
    public Song getSongById(int songid);
    public ArrayList<Song> search(String term);
    public int deleteSong(int songId);
    public int editDetails(Song s);
}
