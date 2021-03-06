package Daos;

import Dtos.Song;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;

/**
 * This class is used for interacting with the Song table in the database
 * @author Austin
 * @author Brian Millar
 */
public class SongDao extends Dao implements SongDaoInterface {
    private final boolean DEBUG = sWave.Server.DEBUGGING;

    private final String TABLE_NAME = "SONGS";
    private final String SONGID     = "SONGID";
    /*
        Filename and title are not the same. Filename is the name on the file in 
        the filesystem when uploading, it usually includes artist name and the 
        song title, songs ripped from CDs usually have a filename of 'Track 1' 
        or something similar. Title is the actual name of the song extracted 
        from the ID3 tag data or entered manually by an admin.
    */
    private final String FILENAME   = "FILENAME";
    private final String TITLE      = "TITLE";
    private final String ARTIST     = "ARTIST";
    private final String ALBUM      = "ALBUM";
    private final String GENRE      = "GENRE";
    private final String YEAR       = "RELYEAR"; //YEAR is a reserved keyword in SQL
    private final String DURATION   = "DURATION";
    private final String PRICE      = "PRICE";
    private final String LICENSE    = "LICENSE";
    private final String PLAYCOUNT  = "PLAYCOUNT";
    private final String UPLOADDATE = "UPLOADED";
    private final String ARTWORK    = "ARTWORK";
    private final String SONGDATA   = "SONGDATA";
    
    /**
     * Default Constructor for SongDao
     */
    public SongDao() {
        super();
    }
    
    /**
     * Parameterized Constructor for SongDao
     * @param ds The DataSource to use for connections
     */
    public SongDao(DataSource ds) {
        super(ds);
    }
    
    /**
     * This method returns an ArrayList of all of the songs in the songs table
     * @return Return an ArrayList of all of the songs, or else null if the table is empty
     */
    @Override
    public ArrayList<Song> getAllSongs() {
        Connection con        = null;
        PreparedStatement ps  = null;
        ResultSet rs          = null;
        ArrayList<Song> songs = new ArrayList<>();

        try {
            con          = getConnection();
            String query = "SELECT * FROM " + TABLE_NAME;
            ps           = con.prepareStatement(query);
            rs           = ps.executeQuery();

            while (rs.next()) {
                Song s = new Song(rs.getInt(SONGID),
                                  rs.getString(FILENAME),
                                  rs.getString(TITLE),
                                  rs.getString(ARTIST),
                                  rs.getString(ALBUM),
                                  rs.getString(GENRE),
                                  rs.getInt(YEAR),
                                  rs.getInt(DURATION),
                                  rs.getDouble(PRICE),
                                  rs.getString(LICENSE),
                                  rs.getInt(PLAYCOUNT),
                                  rs.getDate(UPLOADDATE),
                                  null); //We don't want the songdata or artwork here
                songs.add(s);
            }
            return songs;
        }
        catch (SQLException ex) {
            if (DEBUG)
                ex.printStackTrace();
        }
        finally {
            try {
                if(rs  != null)
                    rs.close();
                if(ps  != null)
                    ps.close();
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
     * This method is used for adding a new song to the database
     * @param s The song you wish to add to the database
     * @return SUCCESS if it successfully inserted, otherwise OTHER
     */
    @Override
    public int addNewSong(Song s) {
        Connection con       = null;
        PreparedStatement ps = null;
        ResultSet rs         = null;

        try {
            con       = getConnection();
            Blob data = con.createBlob();
            Blob art  = con.createBlob();
            try {
                if (s.getSongdata() != null)
                    data.setBytes(1, s.getSongdata());
                if (s.getArtwork() != null)
                    art.setBytes(1, s.getArtwork());
            }
            catch (Exception e) {
                if (DEBUG)
                    e.printStackTrace();
            }
            String query = "INSERT INTO " +
                           TABLE_NAME     + " (" +
                           FILENAME       + ", " +
                           TITLE          + ", " +
                           ARTIST         + ", " +
                           ALBUM          + ", " +
                           GENRE          + ", " +
                           YEAR           + ", " +
                           DURATION       + ", " +
                           PRICE          + ", " +
                           LICENSE        + ", " +
                           PLAYCOUNT      + ", " +
                           UPLOADDATE     + ", " +
                           ARTWORK        + ", " +
                           SONGDATA       + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            ps = con.prepareStatement(query);
            ps.setString(1, s.getFilename());
            ps.setString(2, s.getTitle());
            ps.setString(3, s.getArtist());
            ps.setString(4, s.getAlbum());
            ps.setString(5, s.getGenre());
            ps.setInt(6, s.getYear());
            ps.setInt(7, s.getDuration());
            ps.setDouble(8, s.getPrice());
            ps.setString(9, s.getLicense());
            ps.setInt(10, s.getPlayCount());
            ps.setDate(11, s.getUploaded());
            ps.setBlob(12, art);
            ps.setBlob(13, data);
            if (ps.executeUpdate() > 0)
                return SUCCESS; //It successfully inserted into the database
        }
        catch(SQLException e) {
            if (DEBUG)
                e.printStackTrace();
        }
        finally {
            try {
                if(rs != null)
                    rs.close();
                if(ps != null)
                    ps.close();
                if(con != null)
                    freeConnection(con);
            }
            catch(SQLException e) {
                if (DEBUG)
                    e.printStackTrace();
                return SQLEX;
            }
        }
        return OTHER;
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
                byte songdata[]   = null;
                if (songDataBlob != null)
                    songdata = songDataBlob.getBytes(1, (int)songDataBlob.length());
                
                Blob artworkBlob = rs.getBlob(ARTWORK);
                byte art[]       = null;
                if (artworkBlob != null)
                    art = artworkBlob.getBytes(1, (int)artworkBlob.length());
                
                return new Song(rs.getInt(SONGID),
                                rs.getString(FILENAME),
                                rs.getString(TITLE),
                                rs.getString(ARTIST),
                                rs.getString(ALBUM),
                                rs.getString(GENRE),
                                rs.getInt(YEAR),
                                rs.getInt(DURATION),
                                rs.getDouble(PRICE),
                                rs.getString(LICENSE),
                                rs.getInt(PLAYCOUNT),
                                rs.getDate(UPLOADDATE),
                                art,
                                songdata);
            }
        }
        catch (SQLException ex1) {
            if (DEBUG)
                ex1.printStackTrace();
        }
        finally {
            try {
                if(rs  != null)
                    rs.close();
                if(ps  != null)
                    ps.close();
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
     * A search method for searching the songs in the database
     * @param term The search term to be searched
     * @return An ArrayList of all of the songs that matched the search term
     */
    @Override
    public ArrayList<Song> search(String term) {
        Connection con        = null;
        PreparedStatement ps  = null;
        ResultSet rs          = null;
        ArrayList<Song> songs = new ArrayList();

        try {
            con          = getConnection();
            String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + TITLE + " LIKE ? OR " + ARTIST + " LIKE ? OR " + ALBUM + " LIKE ? OR " + GENRE + " LIKE ?";
            ps           = con.prepareStatement(query);
            ps.setString(1, "%" + term + "%");
            ps.setString(2, "%" + term + "%");
            ps.setString(3, "%" + term + "%");
            ps.setString(4, "%" + term + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                Blob songDataBlob = rs.getBlob(SONGDATA);
                byte songdata[]   = null;
                if (songDataBlob != null)
                    songdata = songDataBlob.getBytes(1, (int)songDataBlob.length());
                
                Blob artworkBlob = rs.getBlob(SONGDATA);
                byte art[]       = null;
                if (artworkBlob != null)
                    art = artworkBlob.getBytes(1, (int)artworkBlob.length());
                
                Song s = new Song(rs.getInt(SONGID),
                                  rs.getString(FILENAME),
                                  rs.getString(TITLE),
                                  rs.getString(ARTIST),
                                  rs.getString(ALBUM),
                                  rs.getString(GENRE),
                                  rs.getInt(YEAR),
                                  rs.getInt(DURATION),
                                  rs.getDouble(PRICE),
                                  rs.getString(LICENSE),
                                  rs.getInt(PLAYCOUNT),
                                  rs.getDate(UPLOADDATE),
                                  art,
                                  songdata);
                songs.add(s);
            }
        }
        catch (SQLException ex1) {
            if (DEBUG)
                ex1.printStackTrace();
        }
        finally {
            try {
                if(rs  != null)
                    rs.close();
                if(ps  != null)
                    ps.close();
                if(con != null)
                    freeConnection(con);
            }
            catch(SQLException e) {
                if (DEBUG)
                    e.printStackTrace();
            }
        }
        return songs;
    }
    
    /**
     * This method is used for deleting songs from the database
     * @param songId The id of the song we want to delete
     * @return Success(0) if the song was deleted or OTHER(-5) if not
     */
    @Override
    public int deleteSong(int songId) {
        Connection con        = null;
        PreparedStatement ps  = null;

        try {
            con          = getConnection();
            String query = "DELETE FROM " + TABLE_NAME + " WHERE " + SONGID + " = ?";
            ps           = con.prepareStatement(query);
            ps.setInt(1, songId);
            int result = ps.executeUpdate();

            if (result > 0) return SUCCESS;
        }
        catch (SQLException ex2) {
            if (DEBUG)
                ex2.printStackTrace();
        }
        finally {
            try {
                if(ps  != null)
                    ps.close();
                if(con != null)
                    freeConnection(con);
            }
            catch(SQLException e) {
                if (DEBUG)
                    e.printStackTrace();
            }
        }
        return OTHER;
    }

    /**
     * This method is used for editing the details on songs
     * @param s The song that is being changed, the details are changed on the command and updated here
     * @return An integer value indicating errors or success
     */
    @Override
    public int editDetails(Song s) {
        if (s != null) {
            Connection con        = null;
            PreparedStatement ps  = null;

            try {
                con          = getConnection();
                Blob art  = con.createBlob();
                art.setBytes(1, s.getArtwork());
                String query = "UPDATE " + TABLE_NAME + 
                               "SET " + FILENAME + " = ?," +
                                        TITLE + " = ?," +
                                        ARTIST + " = ?," +
                                        ALBUM + " = ?," +
                                        GENRE + " = ?," +
                                        YEAR + " = ?," +
                                        PRICE + " = ?," +
                                        LICENSE + " = ?," +
                                        ARTWORK + " = ?" +
                               " WHERE " + SONGID + " = ?";
                ps           = con.prepareStatement(query);
                ps.setString(1, s.getFilename());
                ps.setString(2, s.getTitle());
                ps.setString(3, s.getArtist());
                ps.setString(4, s.getAlbum());
                ps.setString(5, s.getGenre());
                ps.setInt(6, s.getYear());
                ps.setDouble(7, s.getPrice());
                ps.setString(8, s.getLicense());
                ps.setBlob(9, art);
                ps.setInt(10, s.getSongId());
                int result = ps.executeUpdate();

                if (result > 0) return SUCCESS;
            }
            catch (SQLException ex2) {
                if (DEBUG)
                    ex2.printStackTrace();
            }
            finally {
                try {
                    if(ps  != null)
                        ps.close();
                    if(con != null)
                        freeConnection(con);
                }
                catch(SQLException e) {
                    if (DEBUG)
                        e.printStackTrace();
                }
            }
        }
        return OTHER;
    }
}
