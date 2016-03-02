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
    private final boolean DEBUG = Debugging.Debug.debug;

    private final String TABLE_NAME = "SONGS";
    private final String SONGID     = "SONGID";
    private final String TITLE      = "TITLE";
    private final String ARTIST     = "ARTIST";
    private final String GENRE      = "GENRE";
    private final String RELYEAR    = "RELYEAR";
    private final String PRICE      = "PRICE";
    private final String LICENCE    = "LICENCE";
    private final String SONGDATA   = "DATA";

    /**
     * This method returns an ArrayList of all of the songs in the songs table
     * @return Return an ArrayList of all of the songs, or else null if the table is empty
     */
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

            while (rs.next()) {
                Blob songDataBlob = rs.getBlob(SONGDATA);
                byte songdata[]   = new byte[(int)songDataBlob.length()];
                songdata = songDataBlob.getBytes(0, (int)songDataBlob.length());
                Song s   = new Song(rs.getString(TITLE), rs.getString(ARTIST), rs.getString(GENRE), rs.getInt(RELYEAR), rs.getDouble(PRICE), rs.getString(LICENCE), songdata);
                songs.add(s);
            }
            return songs;
        }
        catch (ClassNotFoundException ex1) {
            if (DEBUG)
                ex1.printStackTrace();
        }
        catch (SQLException ex2) {
            if (DEBUG)
                ex2.printStackTrace();
        }
        finally {
            try {
                if(rs  != null) rs.close();
                if(ps  != null) ps.close();
                if(con != null)
                    freeConnection(con);
            }
            catch(SQLException e) {
                if (DEBUG)
                    e.printStackTrace();
            }
        }
        return null;
    }

    /**
     *
     * @param metadata
     * @param buffer
     */
    @Override
    public int addNewSong(ID3v2 metadata, byte[] buffer) {
        Connection con       = null;
        PreparedStatement ps = null;
        ResultSet rs         = null;
        Song s               = new Song();
        s.setSongdata(buffer);

        try {
            con    = getConnection();
            Blob b = con.createBlob();
            try {
                b.setBytes(1, buffer);
            }
            catch (Exception e) {
                if (DEBUG)
                    e.printStackTrace();
            }
            String query = "INSERT INTO " +
                           TABLE_NAME     + " (" +
                           TITLE          + ", " +
                           ARTIST         + ", " +
                           GENRE          + ", " +
                           RELYEAR        + ", " +
                           PRICE          + ", " +
                           LICENCE        + ", " +
                           SONGDATA       + ") VALUES (?, ?, ?, ?, ?, ?, ?)";
            ps = con.prepareStatement(query);
            ps.setString(1, s.getTitle());
            ps.setString(2, s.getArtist());
            ps.setString(3, s.getGenre());
            ps.setInt(4, s.getRelYear());
            ps.setDouble(5, s.getPrice());
            ps.setString(6, s.getLicence());
            ps.setBlob(7, b);
            if (ps.executeUpdate() > 0)
                return SUCCESS; //It successfully inserted into the database
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
        //to be removed
        return 0;
    }

    /**
     * This method is used to get a song by its id
     * @param songid The id of the song we want to return
     * @return Returns a song if the id exists, or else null if it doesn't exist
     */
    @Override
    public Song getSongById(int songid) {
        Connection con        = null;
        PreparedStatement ps  = null;
        ResultSet rs          = null;

        try {
            con          = getConnection();
            String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + SONGID + " = ?";
            ps           = con.prepareStatement(query);
            ps.setInt(1, songid);
            rs = ps.executeQuery();

            if (rs.next()) {
                Blob songDataBlob = rs.getBlob(SONGDATA);
                byte songdata[]   = new byte[(int)songDataBlob.length()];
                songdata          = songDataBlob.getBytes(1, (int)songDataBlob.length());
                return new Song(rs.getString(TITLE), rs.getString(ARTIST), rs.getString(GENRE), rs.getInt(RELYEAR), rs.getDouble(PRICE), rs.getString(LICENCE), songdata);
            }
        }
        catch (ClassNotFoundException ex1) {
            if (DEBUG)
                ex1.printStackTrace();
        }
        catch (SQLException ex2) {
            if (DEBUG)
                ex2.printStackTrace();
        }
        finally {
            try {
                if(rs  != null) rs.close();
                if(ps  != null) ps.close();
                if(con != null)
                    freeConnection(con);
            }
            catch(SQLException e) {
                if (DEBUG)
                    e.printStackTrace();
            }
        }
        return null;
    }
}
