package Daos;

import Dtos.Song;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import sWaveEngine.ID3v2;

/**
 *
 * @author Austin
 * @author Brian Millar
 */
public class SongDao extends Dao implements SongDaoInterface {
    
    private final boolean DEBUG = false;
    
    private final String TABLE_NAME = "SONGS";
    private final String SONGID     = "SONGID";
    private final String TITLE      = "TITLE";
    private final String ARTIST     = "ARTIST";
    private final String GENRE      = "GENRE";
    private final String RELYEAR    = "RELYEAR";
    private final String PRICE      = "PRICE";
    private final String LICENCE    = "LICENCE";
    
    
    @Override
    public ArrayList<Song> getAllSongs() {
        Connection con        = null;
        PreparedStatement ps  = null;
        ResultSet rs          = null;
        
        ArrayList<Song> songs = null;

        try {

            con = getConnection();
            String query = "SELECT * FROM " + TABLE_NAME;
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            

        }
        catch (ClassNotFoundException ex1) {
            if (DEBUG)
                ex1.printStackTrace();
            return null;
        }
        catch (SQLException ex2) {
            if (DEBUG)
                ex2.printStackTrace();
            return null;
        }
        finally {
            try {
                if(rs != null) {
                    rs.close();
                }
                if(ps != null) {
                    ps.close();
                }
                if(con != null) {
                    freeConnection(con);
                }

            }
            catch(SQLException e) {
                if (DEBUG)
                    e.printStackTrace();
               return null;
            }
        }
        return null;
    }

    public void addNewSong(ID3v2 metadata, byte[] buffer) {
        Connection con       = null;
        PreparedStatement ps = null;
        ResultSet rs         = null;
        Song s               = null;
        
        try {
            con = getConnection();
            
        }
        catch(ClassNotFoundException | SQLException e) {
            if (DEBUG)
                e.printStackTrace();
        }
        finally {
            try {
                if(rs  != null) rs.close();
                if(ps  != null) ps.close();
                if(con != null) freeConnection(con);
            }
            catch(SQLException e) {
                if (DEBUG)
                    e.printStackTrace();
            }
        }
    }

    public Song getSongById(int songid) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}