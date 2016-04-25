/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Daos;

import Dtos.PlayTrack;
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
public class PlayTracksDaoTest {
    static MyDataSource ds = new MyDataSource();
    static PlayTracksDao instance;
    public PlayTracksDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        instance = new PlayTracksDao(ds);
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
    }
    
    @After
    public void tearDown() {
        instance.deletePlayTrack(new PlayTrack());
    }

    /**
     * Test of createPlayTrack method, of class PlayTracksDao.
     */
    @Test
    public void testCreatePlayTrack() {
        
        PlayTrack pt = new PlayTrack();
        int expResult = 0;
        int result = instance.createPlayTrack(pt);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getAllSongIds method, of class PlayTracksDao.
     */
    @Test
    public void testgetAllSongIds() {
        int expResult = -1;
        ArrayList<PlayTrack> songIds = instance.getPlayTracksInPlaylist(-1);
        int result = songIds.get(0).getSongId();
        assertEquals(expResult, result);
    }
}
