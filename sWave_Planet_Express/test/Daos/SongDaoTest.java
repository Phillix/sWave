/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Daos;

import Dtos.Song;
import java.util.ArrayList;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import sWaveEngine.ID3v2;

/**
 *
 * @author Brian Millar
 */
public class SongDaoTest {

    public SongDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    /**
     * Test of getAllSongs method, of class SongDao.
     */
    @Test
    public void testGetAllSongs() {
        System.out.println("getAllSongs");
        SongDao instance = new SongDao();
        ArrayList<Song> expResult = null;
        ArrayList<Song> result = instance.getAllSongs();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addNewSong method, of class SongDao.
     */
    @Test
    public void testAddNewSong() {
        System.out.println("addNewSong");
        ID3v2 metadata   = null;
        byte[] buffer    = null;
        SongDao instance = new SongDao();
        int expResult = 0;
        int result = instance.addNewSong(metadata, buffer);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSongById method, of class SongDao.
     */
    @Test
    public void testGetSongById() {
        System.out.println("getSongById");
        SongDao instance = new SongDao();
        Song expResult   = new Song("Title", "Artist", "Genre", 1970, 2.99, "Public Domain", null);
        Song result      = instance.getSongById(-1);
        assertEquals("Result did not match expected dummy data", expResult, result);
    }
}
