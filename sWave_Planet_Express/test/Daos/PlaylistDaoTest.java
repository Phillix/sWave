/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Daos;

import Dtos.Playlist;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Phillix
 */
public class PlaylistDaoTest {
    
    static PlaylistDao instance;
    public PlaylistDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        instance = new PlaylistDao();
    }
    
    @After
    public void tearDown() {
        instance.deletePlaylist(-2);
    }

    /**
     * Test of createPlayList method, of class PlaylistDao.
     */
    @Test
    public void testCreatePlayList() {
        
        Playlist p = new Playlist(-2,"test2");
        p.setPlaylistId(-2);
        int expResult = 0;
        int result = instance.createPlayList(p);
        assertEquals(expResult, result);
    }

    /**
     * Test of deletePlaylist method, of class PlaylistDao.
     */
    @Test
    public void testDeletePlaylist() {
        
        Playlist p = new Playlist(-1,"test title");
        p.setPlaylistId(-3);
        instance.createPlayList(p);
        int expResult = 0;
        int result = instance.deletePlaylist(-3);
        assertEquals(expResult, result);
    }

    /**
     * Test of getUserPlaylists method, of class PlaylistDao.
     */
    @Test
    public void testGetUserPlaylists() {
        
        Playlist expResult = new Playlist(-1,"test1");
        expResult.setPlaylistId(-1);
        int userId = -1;
        ArrayList<Playlist> result = instance.getUserPlaylists(userId);
        assertEquals(expResult, result.get(0));
    }
    
}
