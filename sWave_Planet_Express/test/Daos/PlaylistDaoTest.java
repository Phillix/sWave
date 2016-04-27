package Daos;

import Dtos.Playlist;
import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * This class is used for testing PlaylistDao's methods
 * @author Austin
 * @author Phillix
 */
public class PlaylistDaoTest {
    static MyDataSource ds = new MyDataSource();
    static PlaylistDao instance;
    static Playlist pl;
    static int res;
    
    @BeforeClass
    public static void setUpClass() {
        instance = new PlaylistDao(ds);
    }
    
    @Before
    public void setUp() {
        pl = new Playlist(-1, "Good Playlist");
        pl.setPlaylistId(3);
        res = instance.createPlayList(pl);
    }
    
    @After
    public void tearDown() {
        instance.deletePlaylist(3);
    }

    /**
     * Test of createPlayList method being valid, of class PlaylistDao.
     */
    @Test
    public void testCreatePlayListValid() {
        int expResult = 0;
        assertEquals(expResult, res);
    }
    
    /**
     * Test of createPlayList method being invalid, of class PlaylistDao.
     */
    @Test
    public void testCreatePlayListInvalid() {
        boolean expResult = false;
        assertEquals(expResult, res == -5);
    }
    
    /**
     * Test of getPlaylistById method being valid, of class PlaylistDao.
     */
    @Test
    public void testGetPlaylistByIdValid() {
        Playlist p2 = instance.getPlaylistById(pl.getPlaylistId());
        boolean result = pl.equals(p2);
        boolean expResult = true;
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getPlaylistById method being invalid, of class PlaylistDao.
     */
    @Test
    public void testGetPlaylistByIdInvalid() {
        Playlist p2 = instance.getPlaylistById(5);
        assertNull(p2);
    }
    
    /**
     * Test of deletePlaylist method being valid, of class PlaylistDao.
     */
    @Test
    public void testDeletePlaylistValid() {
        int expResult = 0;
        int result = instance.deletePlaylist(3);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of deletePlaylist method being invalid, of class PlaylistDao.
     */
    @Test
    public void testDeletePlaylistInvalid() {
        int expResult = -5;
        int result = instance.deletePlaylist(5);
        assertEquals(expResult, result);
    }

    /**
     * Test of getUserPlaylists method being valid, of class PlaylistDao.
     */
    @Test
    public void testGetUserPlaylistsValid() {
        int result = instance.getUserPlaylists(-1).size();
        int expResult = 2;
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getUserPlaylists method being invalid, of class PlaylistDao.
     */
    @Test
    public void testGetUserPlaylistsInvalid() {
        ArrayList<Playlist> result = instance.getUserPlaylists(5);
        boolean expResult = true;
        assertEquals(expResult, result.isEmpty());
    }
}
