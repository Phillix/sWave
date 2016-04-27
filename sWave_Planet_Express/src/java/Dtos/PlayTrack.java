package Dtos;

/**
 * This class is used for modifying and creating PlayTrack objects
 * @author Phillix
 */
public class PlayTrack {
    
    private int songId;
    private int playlistId;
    private int playlistOrder;
    
    /**
     * Default constructor for creating PlayTracks
     */
    public PlayTrack() {
        songId = -1;
        playlistId = -1;
        playlistOrder = 0;
    }

    /**
     * Overloaded constructor for PlayTracks
     * @param songId the id of the song
     * @param playlistId the id of the playlist
     * @param playlistOrder the order the song is in within the playlist
     */
    public PlayTrack(int songId, int playlistId, int playlistOrder) {
        this.songId = songId;
        this.playlistId = playlistId;
        this.playlistOrder = playlistOrder;
    }

    /**
     * Getting the playlist order
     * @return the order this song is in within the playlist
     */
    public int getPlaylistOrder() {
        return playlistOrder;
    }

    /**
     * Setting the songs order within a playlist
     * @param playlistOrder the new order in the playlist
     */
    public void setPlaylistOrder(int playlistOrder) {
        this.playlistOrder = playlistOrder;
    }
    
    /**
     * Getting the id of the song in this PlayTrack
     * @return the id of the song in this PlayTrack
     */
    public int getSongId() {
        return songId;
    }

    /**
     * Setting the id of the song in this PlayTrack
     * @param songId the new id of the song in this PlayTrack
     */
    public void setSongId(int songId) {
        this.songId = songId;
    }

    /**
     * Getting the id of the Playlist this PlayTrack belongs to
     * @return the id of the Playlist this PlayTrack belongs to
     */
    public int getPlaylistId() {
        return playlistId;
    }

    /**
     * Setting the id of this Playlist
     * @param playlistId the new id of the playlist for this PlayTrack
     */
    public void setPlaylistId(int playlistId) {
        this.playlistId = playlistId;
    }

    /**
     * A toString method for returning the contents of the PlayTrack in a formatted way
     * @return a String containing all of the details of this PlayTrack
     */
    @Override
    public String toString() {
        return "PlayTrack{" + "songId=" + songId + ", playlistId=" + playlistId + ", playlistOrder=" + playlistOrder + '}';
    }

}
