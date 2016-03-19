/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import Dtos.PlayTrack;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Phillix
 */
public class PlayTracksDao extends Dao implements PlayTracksDaoInterface {
    
    private final boolean DEBUG = sWave.Server.DEBUGGING;

    private final String TABLE_NAME  = "PLAYTRACKS";
    private final String SONGID      = "SONGID";
    private final String PLAYLISTID  = "PLAYLISTID";
    
    /**
     * 
     * @param pt the data to write to database
     * @return int value indicating errors or success
     */
    public int createPlayTrack(PlayTrack pt) {

        Connection con       = null;
        PreparedStatement ps = null;

        try {
            con = getConnection();
            String query = "INSERT INTO " + TABLE_NAME + " VALUES(?,?)";

            ps = con.prepareStatement(query);

            ps.setInt(1, pt.getSongId());
            ps.setInt(2, pt.getPlaylistId());

            ps.executeUpdate();
            return SUCCESS;
        }
        catch (ClassNotFoundException e) {
            if(DEBUG)
                e.printStackTrace();
            return CLASSNOTFOUND;
        }
        catch (SQLException e) {
            if(DEBUG)
                e.printStackTrace();
            return SQLEX;
        }
        finally {
            try {
                if(ps != null)
                    ps.close();
                if(con != null)
                    freeConnection(con);
            }
            catch(SQLException e) {
                if(DEBUG)
                    e.printStackTrace();
                return CONNCLOSEFAIL;
            }
        }
    }

    /**
     * 
     * @param pt playtrack to delete
     * @return int value indicating errors or success
     */
    public int deletePlayTrack(PlayTrack pt) {

        Connection con       = null;
        PreparedStatement ps = null;

        try {
            con = getConnection();
            String query = "DELETE FROM " + TABLE_NAME + " WHERE " + SONGID + " = ? AND " + PLAYLISTID + " = ?";

            ps = con.prepareStatement(query);

            ps.setInt(1, pt.getSongId());
            ps.setInt(2, pt.getPlaylistId());

            ps.executeUpdate();
            return SUCCESS;
        }
        catch (ClassNotFoundException e) {
            if(DEBUG)
                e.printStackTrace();
            return CLASSNOTFOUND;
        }
        catch (SQLException e) {
            if(DEBUG)
                e.printStackTrace();
            return SQLEX;
        }
        finally {
            try {
                if(ps != null)
                    ps.close();
                if(con != null)
                    freeConnection(con);
            }
            catch(SQLException e) {
                if(DEBUG)
                    e.printStackTrace();
                return CONNCLOSEFAIL;
            }
        }
    }
    
    /**
     * 
     * @param playlistId the id of the playlist the songs belong to
     * @return arraylist of song ids
     */
    public ArrayList<Integer> getAllSongIds(int playlistId) {

        Connection con       = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Integer> songIds = null;

        try {
            con = getConnection();
            String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + PLAYLISTID + " = ?";

            ps = con.prepareStatement(query);
            ps.setInt(1, playlistId);

            rs = ps.executeQuery();
            songIds = new ArrayList<Integer>();
            
            while(rs.next()) {
                songIds.add(rs.getInt(SONGID));
            }
            return songIds;
        }
        catch (ClassNotFoundException e) {
            if(DEBUG)
                e.printStackTrace();
            return null;
        }
        catch (SQLException e) {
            if(DEBUG)
                e.printStackTrace();
            return null;
        }
        finally {
            try {
                if(ps != null)
                    ps.close();
                if(con != null)
                    freeConnection(con);
            }
            catch(SQLException e) {
                if(DEBUG)
                    e.printStackTrace();
                return null;
            }
        }
    }
}
