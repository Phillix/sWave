package Daos;

import Dtos.Song;
import java.sql.Date;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * This test class tests all of the methods from the SongDao class
 * @author Austin
 */
public class SongDaoTest {
    static MyDataSource md = new MyDataSource();
    static SongDao instance;
    static Song s;
    static int res;
    public SongDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        instance = new SongDao(md);
    }
    
    @Before
    public void setUp() {
        s = new Song(1, "meh", "Gold digga", "kanye not", "only on tidal", "probably rap", 2013, 420, 1, "Open", 3, new Date(System.currentTimeMillis()), null, null);
        res = instance.addNewSong(s);
        s = instance.search("only on tidal").stream().findFirst().get();
    }
    
    @After
    public void tearDown() {
        instance.deleteSong(s.getSongId());
    }

    /**
     * Test of getAllSongs method being valid, of class SongDao.
     */
    @Test
    public void testGetAllSongsValid() {
        int result = instance.getAllSongs().size();
        int expResult = 3;
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getAllSongs method being invalid, of class SongDao.
     */
    @Test
    public void testGetAllSongsInvalid() {
        boolean result = instance.getAllSongs().isEmpty();
        boolean expResult = false;
        assertEquals(expResult, result);
    }

    /**
     * Test of addNewSong method being valid, of class SongDao.
     */
    @Test
    public void testAddNewSongValid() {
        int expResult = 0;
        assertEquals(expResult, res);
    }
    
    /**
     * Test of addNewSong method being invalid, of class SongDao.
     */
    @Test
    public void testAddNewSongInvalid() {
        int expResult = -5;
        assertNotEquals(expResult, res);
    }

    /**
     * Test of getSongById method being valid, of class SongDao.
     */
    @Test
    public void testGetSongByIdValid() {
        Song s1 = instance.getSongById(s.getSongId());
        assertEquals(s, s1);
    }
    
    /**
     * Test of getSongById method being invalid, of class SongDao.
     */
    @Test
    public void testGetSongByIdInvalid() {
        Song s1 = instance.getSongById(4);
        assertNull(s1);
    }
    
    /**
     * Test of search method being valid, of class SongDao.
     */
    @Test
    public void testSearchValid() {
        boolean result = instance.search("kanye").isEmpty();
        boolean expResult = false;
        assertEquals(expResult, result);
    }
    
    /**
     * Test of search method being invalid, of class SongDao.
     */
    @Test
    public void testSearchInvalid() {
        boolean result = instance.search("this is a random meaningless string if it gets matched ill cry").isEmpty();
        boolean expResult = true;
        assertEquals(expResult, result);
    }

    /**
     * Test of deleteSong method being valid, of class SongDao.
     */
    @Test
    public void testDeleteSongValid() {
        int result = instance.deleteSong(s.getSongId());
        int expResult = 0;
        assertEquals(expResult, result);
    }
    
    /**
     * Test of deleteSong method being invalid, of class SongDao.
     */
    @Test
    public void testDeleteSongInvalid() {
        int result = instance.deleteSong(5);
        int expResult = -5;
        assertEquals(expResult, result);
    }

    /**
     * Test of editDetails method being valid, of class SongDao.
     */
//    @Test
//    public void testEditDetailsValid() {
//        int result = instance.editDetails(s);
//        int expResult = 0;
//        assertEquals(expResult, result);
//    }
    
    /**
     * Test of editDetails method being invalid, of class SongDao.
     */
//    @Test
//    public void testEditDetailsInvalid() {
//        int result = instance.editDetails(s);
//        int expResult = -5;
//        assertEquals(expResult, result);
//    }
}
