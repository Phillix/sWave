package Daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import Dtos.PlayTrack;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.sql.DataSource;

/**
 * The PlayTracksDao class is used for communicating with PlayTracks table in the database
 * @author Phillix
 */
public class PlayTracksDao extends Dao implements PlayTracksDaoInterface {
    
    private final boolean DEBUG = sWave.Server.DEBUGGING;

    private final String TABLE_NAME  = "PLAYTRACKS";
    private final String SONGID      = "SONGID";
    private final String PLAYLISTID  = "PLAYLISTID";
    private final String ORDER       = "PLAYLISTORDER";
    
    /**
     * Default Constructor for PlayTracksDao
     */
    public PlayTracksDao() {
        super();
    }
    
    /**
     * Parameterized Constructor for PlayTracksDao
     * @param ds The DataSource to use for connections
     */
    public PlayTracksDao(DataSource ds) {
        super(ds);
    }
    
    /**
     * This method is used for creating a new PlayTrack
     * @param pt the data to write to database
     * @return int value indicating errors or success
     */
    public int createPlayTrack(PlayTrack pt) {

        Connection con       = null;
        PreparedStatement ps = null;

        try {
            con = getConnection();
            String query = "INSERT INTO " + TABLE_NAME + " VALUES(?,?,?)";

            ps = con.prepareStatement(query);

            ps.setInt(1, pt.getSongId());
            ps.setInt(2, pt.getPlaylistId());
            ps.setInt(3, pt.getPlaylistOrder());

            ps.executeUpdate();
            return SUCCESS;
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
     * This method is used for deleting a PlayTrack
     * @param pt PlayTrack to delete
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

            int result = ps.executeUpdate();
            cascadeOrderOnDelete(pt);
            if (result > 0) return SUCCESS;
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
        return OTHER;
    }
    
    /**
     * This method is used for deleting all songs within a particular Playlist
     * @param playlistId the playlist the tracks belong to
     * @return int value indicating errors or success
     */
    public int deletePlayTracksInPlaylist(int playlistId) {

        Connection con       = null;
        PreparedStatement ps = null;

        try {
            con = getConnection();
            String query = "DELETE FROM " + TABLE_NAME + " WHERE " + PLAYLISTID + " = ?";

            ps = con.prepareStatement(query);
            ps.setInt(1, playlistId);

            int result = ps.executeUpdate();
            if (result > 0) return SUCCESS;
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
        return OTHER;
    }
    
    /**
     * This method returns all tracks within a single Playlist
     * @param playlistId the id of the Playlist the songs belong to
     * @return an ArrayList of PlayTracks belonging to Playlist
     */
    public ArrayList<PlayTrack> getPlayTracksInPlaylist(int playlistId) {

        Connection con       = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<PlayTrack> playtracks = null;
        PlayTrack pt = null;

        try {
            con = getConnection();
            String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + PLAYLISTID + " = ? ORDER BY " + ORDER;

            ps = con.prepareStatement(query);
            ps.setInt(1, playlistId);

            rs = ps.executeQuery();
            playtracks = new ArrayList<PlayTrack>();
            
            while(rs.next()) {
                pt = new PlayTrack();
                pt.setSongId(rs.getInt(SONGID));
                pt.setPlaylistId(playlistId);
                pt.setPlaylistOrder(rs.getInt(ORDER));
                
                playtracks.add(pt);
            }
            return playtracks;
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
    
    /**
     * This method is used for moving the track down in the Playlist
     * @param pt the track to move down
     * @return int value indicating errors or success
     */
    public int moveOrderDown(PlayTrack pt) {
        
        Connection con       = null;
        PreparedStatement ps = null;

        try {
            if(pt.getPlaylistOrder() != 0 ) {
                con = getConnection();
            ps = con.prepareStatement("UPDATE " + TABLE_NAME + " SET " + ORDER + " = " + ORDER + " - 1 "
                    + "WHERE " + PLAYLISTID + " = ? AND " + ORDER + " = ?");
            ps.setInt(1, pt.getPlaylistId());
            ps.setInt(2, pt.getPlaylistOrder() + 1);
            ps.executeUpdate();

            ps = con.prepareStatement("UPDATE " + TABLE_NAME + " SET " + ORDER + " = " + ORDER + " + 1 "
                    + "WHERE " + PLAYLISTID + " = ? AND " + SONGID + " != ?");
            ps.setInt(1, pt.getPlaylistId());
            ps.setInt(2, pt.getSongId());
            ps.executeUpdate();
            
            return SUCCESS;
            }

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
        return OTHER;
    }
    
    /**
     * This method is used for moving a track up in a Playlist
     * @param pt the track to move up
     * @return int value indicating errors or success
     */
    public int moveOrderUp(PlayTrack pt) {
        
        Connection con       = null;
        PreparedStatement ps = null;
        
        try {
            if(pt.getPlaylistOrder() != getMaxPlaylistOrder(pt.getPlaylistId())) {
                con = getConnection();
                ps = con.prepareStatement("UPDATE " + TABLE_NAME + " SET " + ORDER + " = " + ORDER + " + 1 "
                        + "WHERE " + PLAYLISTID + " = ? AND " + ORDER + " = ?");
                ps.setInt(1, pt.getPlaylistId());
                ps.setInt(2, pt.getPlaylistOrder() - 1);
                ps.executeUpdate();

                ps = con.prepareStatement("UPDATE " + TABLE_NAME + " SET " + ORDER + " = " + ORDER + " - 1 "
                        + "WHERE " + PLAYLISTID + " = ? AND " + SONGID + " != ?");
                ps.setInt(1, pt.getPlaylistId());
                ps.setInt(2, pt.getSongId());

                return SUCCESS;
            }
            
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
        return OTHER;
    }
    
    /**
     * a method to adjust the order numbers of a playlists songs after one is removed
     * @param pt the playtrack that got deleted
     * @return int value indicating errors or success
     */
    public int cascadeOrderOnDelete(PlayTrack pt) {
        
        Connection con       = null;
        PreparedStatement ps = null;

        try {
            con = getConnection();
            ps = con.prepareStatement("UPDATE " + TABLE_NAME + " SET " + ORDER + " = " + ORDER + " - 1 "
                    + "WHERE " + PLAYLISTID + " = ? AND " + ORDER + " > ?");
            ps.setInt(1, pt.getPlaylistId());
            ps.setInt(2, pt.getPlaylistOrder());
            ps.executeUpdate();

            return SUCCESS;

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
     * This method is used for getting the max order in the Playlist
     * @param playlistId
     * @return 
     */
    public int getMaxPlaylistOrder(int playlistId) {
        
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            con = getConnection();
            ps = con.prepareStatement("SELECT MAX(" + ORDER + ") FROM " + TABLE_NAME + " WHERE " + PLAYLISTID + " = ?");
            ps.setInt(1, playlistId);
            rs = ps.executeQuery();
            
            if(rs.next()) {
                return rs.getInt("MAX("+ORDER+")");
            }
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
        return OTHER;
    }    
}
