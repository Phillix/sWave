/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Daos;

import Dtos.Playlist;
import java.util.ArrayList;

/**
 *
 * @author Phillix
 */
public interface PlaylistDaoInterface {
    
    public int createPlayList(Playlist p);
    
    public int deletePlaylist(int id);
    
    public ArrayList<Playlist> getUserPlaylists(int userId);
}
