package Dtos;

import Daos.PlayTracksDao;
import Daos.SongDao;
import java.util.ArrayList;
import java.util.Objects;

/**
 * This class is used for creating and managing Playlist objects
 * @author Phillix
 */
public class Playlist {
    
    private int playlistId;
    private int userId;
    private String title;
    
    /**
     * Default constructor for Playlists
     */
    public Playlist() {
        playlistId = -1;
        userId = -1;
        title = "title";
    }

    /**
     * Overloaded constructor for Playlists
     * @param userId the id of the user making the Playlist
     * @param title the title of the Playlist
     */
    public Playlist( int userId, String title) {
        this.playlistId = 0;
        this.userId = userId;
        this.title = title;
    }

    /**
     * Getting the id of the Playlist
     * @return the id of the Playlist
     */
    public int getPlaylistId() {
        return playlistId;
    }

    /**
     * Setting the id of the Playlist
     * @param playlistId the new id of the Playlist
     */
    public void setPlaylistId(int playlistId) {
        this.playlistId = playlistId;
    }

    /**
     * Getting the id of the user making the Playlist
     * @return the id of the user making the Playlist
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Setting the user id of the Playlist
     * @param userId the new user id of the Playlist
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Getting the title of the Playlist
     * @return the title of the Playlist
     */
    public String getTitle() {
        return title;
    }

    /**
     * Setting the title of the Playlist
     * @param title the new title of the Playlist
     */
    public void setTitle(String title) {
        this.title = title;
    }
    
    /**
     * Getting the contents of the Playlist
     * @return an ArrayList of songs from this Playlist
     */
    public ArrayList<Song> getPlaylistContents() {
        ArrayList<Song> songs  = new ArrayList<>();
        PlayTracksDao trackDao = new PlayTracksDao();
        SongDao songDao = new SongDao();
        ArrayList<PlayTrack> playTracks = trackDao.getPlayTracksInPlaylist(playlistId);
        for(PlayTrack pt : playTracks)
            songs.add(songDao.getSongById(pt.getSongId()));
        return songs;
    }
    
    /**
     * Getting the size of the Playlist
     * @return the size of the Playlist
     */
    public int getSize() {
        PlayTracksDao trackDao = new PlayTracksDao();
        return trackDao.getPlayTracksInPlaylist(playlistId).size();
    }

    /**
     * A toString method for easily displaying the contents of this Playlist
     * @return a String containing the details of this Playlist
     */
    @Override
    public String toString() {
        return "Playlist{" + "playlistId=" + playlistId + ", userId=" + userId + ", title=" + title + '}';
    }

    /**
     * A hashCode method for hashing this Object
     * @return an int to represent the hash of this Object
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.playlistId;
        hash = 71 * hash + this.userId;
        hash = 71 * hash + Objects.hashCode(this.title);
        return hash;
    }

    /**
     * An equals method for comparing this Playlist to other Objects
     * @param obj the Object you are comparing this Playlist to
     * @return a boolean value representing if this Playlist is equal to the argument
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Playlist other = (Playlist) obj;
        if (this.playlistId != other.playlistId) {
            return false;
        }
        if (this.userId != other.userId) {
            return false;
        }
        return true;
    }
    
    
    
}
