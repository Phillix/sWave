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
public class Playlist {
    
    private int playlistId;
    private int userId;
    private String title;
    
    public Playlist() {
        playlistId = -1;
        userId = -1;
        title = "title";
    }

    public Playlist( int userId, String title) {
        this.playlistId = 0;
        this.userId = userId;
        this.title = title;
    }

    public int getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(int playlistId) {
        this.playlistId = playlistId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Playlist{" + "playlistId=" + playlistId + ", userId=" + userId + ", title=" + title + '}';
    }
    
    
    
}
