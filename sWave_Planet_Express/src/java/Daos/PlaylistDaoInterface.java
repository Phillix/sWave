package Daos;

import Dtos.Playlist;
import java.util.ArrayList;

/**
 *
 * @author Phillix
 * @author Brian Millar
 */
public interface PlaylistDaoInterface {
    public int createPlayList(Playlist p);
    public int deletePlaylist(int id);
    public ArrayList<Playlist> getUserPlaylists(int userId);
    public void addSongToPlaylist(int s, int p);
}
