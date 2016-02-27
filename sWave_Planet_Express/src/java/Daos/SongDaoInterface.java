package Daos;

import Dtos.Song;
import java.util.ArrayList;
import sWaveEngine.ID3v2;

/**
 *
 * @author Austin
 * @author Brian Millar
 */
public interface SongDaoInterface {
    public ArrayList<Song> getAllSongs();
    public int addNewSong(ID3v2 metadata, byte[] buffer);
    public Song getSongById(int songid);
}
