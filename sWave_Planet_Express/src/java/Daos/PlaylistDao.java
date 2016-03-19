/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Daos;

import Dtos.Playlist;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Phillix
 */
public class PlaylistDao extends Dao implements PlaylistDaoInterface {
    
    private final boolean DEBUG = sWave.Server.DEBUGGING;

    private final String TABLE_NAME  = "PLAYLISTS";
    private final String ID          = "PLAYLISTID";
    private final String USERID      = "USERID";
    private final String TITLE       = "TITLE";
    
    /**
     * 
     * @param p the playlist to create
     * @return int value indicating errors or success
     */
    public int createPlayList(Playlist p) {
        
        Connection con       = null;
        PreparedStatement ps = null;

        try {
            con = getConnection();
            String query = "INSERT INTO " + TABLE_NAME + " VALUES(?,?,?)";

            ps = con.prepareStatement(query);

            ps.setInt(1, p.getPlaylistId());
            ps.setInt(2, p.getUserId());
            ps.setString(3, p.getTitle());

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
     * @param id id of the playlist to delete
     * @return int value indicating errors or success
     */
    public int deletePlaylist(int id) {
        
        Connection con       = null;
        PreparedStatement ps = null;

        try {
            con = getConnection();
            String query = "DELETE FROM " + TABLE_NAME + " WHERE " + ID + " = ? ";

            ps = con.prepareStatement(query);
            ps.setInt(1, id);

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
     * @param userId id of the user the playlists belong to 
     * @return Collection of playlists beloning to the user
     */
    public ArrayList<Playlist> getUserPlaylists(int userId) {
        
        Connection con       = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Playlist> playlists = null;

        try {
            con = getConnection();
            String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + USERID + " = ?";

            ps = con.prepareStatement(query);
            ps.setInt(1, userId);

            rs = ps.executeQuery();
            playlists = new ArrayList<Playlist>();
            
            while(rs.next()) {
                Playlist p = new Playlist();
                p.setPlaylistId(rs.getInt(ID));
                p.setUserId(userId);
                p.setTitle(rs.getString(TITLE));
                
                playlists.add(p);
            }
            return playlists;
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
