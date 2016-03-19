/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dtos;

/**
 *
 * @author Phillix
 */
public class PlayTrack {
    
    private int songId;
    private int playlistId;
    
    public PlayTrack() {
        songId = -1;
        playlistId = -1;
    }

    public PlayTrack(int songId, int playlistId) {
        this.songId = songId;
        this.playlistId = playlistId;
    }

    public int getSongId() {
        return songId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
    }

    public int getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(int playlistId) {
        this.playlistId = playlistId;
    }

    @Override
    public String toString() {
        return "PlayTracks{" + "songId=" + songId + ", playlistId=" + playlistId + '}';
    }
    
    
}
