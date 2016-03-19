/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    
    public ArrayList<Integer> getAllSongIds(int playlistId);
}
