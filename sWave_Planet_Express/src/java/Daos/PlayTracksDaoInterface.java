package Daos;

import Dtos.PlayTrack;
import java.util.ArrayList;

/**
 *
 * @author Phillix
 */
public interface PlayTracksDaoInterface {
    public int createPlayTrack(PlayTrack pt);
    public int deletePlayTrack(PlayTrack pt);
    public int deletePlayTracksInPlaylist(int playlistId);
    public ArrayList<PlayTrack> getPlayTracksInPlaylist(int playlistId);
    public int moveOrderUp(PlayTrack pt);
    public int moveOrderDown(PlayTrack pt);
    public int cascadeOrderOnDelete(PlayTrack pt);
}
