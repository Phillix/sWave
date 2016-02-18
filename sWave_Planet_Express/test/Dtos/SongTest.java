package Dtos;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Austin
 */
public class SongTest {
    Song instance1;
    Song instance2;
    public SongTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        instance1 = new Song();
        instance2 = new Song("Bohemian Rhapsody", "Queen", "Rock", 1975, 0.99, "GNU");
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getSongId method, of class Song.
     */
    @Test
    public void testGetSongId() {
        int expResult = 0;
        int result = instance1.getSongId();
        assertEquals("The song id's do not match after getting", expResult, result);
    }

    /**
     * Test of setSongId method, of class Song.
     */
    @Test
    public void testSetSongId() {
        int expResult = 4;
        instance2.setSongId(4);
        assertEquals("The song ids do not match after setting", expResult, instance2.getSongId());
    }

    /**
     * Test of getTitle method, of class Song.
     */
    @Test
    public void testGetTitle() {
        String expResult = "Bohemian Rhapsody";
        String result = instance2.getTitle();
        assertEquals("The song titles do not match after getting", expResult, result);
    }

    /**
     * Test of setTitle method, of class Song.
     */
    @Test
    public void testSetTitle() {
        String expResult = "Rhapsody Bohemian";
        instance2.setTitle("Rhapsody Bohemian");
        assertEquals("The song titles do not match after setting", expResult, instance2.getTitle());
    }

    /**
     * Test of getArtist method, of class Song.
     */
    @Test
    public void testGetArtist() {
        String expResult = "Queen";
        String result = instance2.getArtist();
        assertEquals("The song artists do not match after getting", expResult, result);
    }

    /**
     * Test of setArtist method, of class Song.
     */
    @Test
    public void testSetArtist() {
        String expResult = "Queeen";
        instance2.setArtist("Queeen");
        assertEquals("The song artists do not match after setting", expResult, instance2.getArtist());
    }

    /**
     * Test of getGenre method, of class Song.
     */
    @Test
    public void testGetGenre() {
        String expResult = "Rock";
        String result = instance2.getGenre();
        assertEquals("The song genres do not match after getting", expResult, result);
    }

    /**
     * Test of setGenre method, of class Song.
     */
    @Test
    public void testSetGenre() {
        String expResult = "Hard Rock";
        instance2.setGenre("Hard Rock");
        assertEquals("The song genres do not match after setting", expResult, instance2.getGenre());
    }

    /**
     * Test of getRelYear method, of class Song.
     */
    @Test
    public void testGetRelYear() {
        int expResult = 1975;
        int result = instance2.getRelYear();
        assertEquals("The song release years do not match after getting", expResult, result);
    }

    /**
     * Test of setRelYear method, of class Song.
     */
    @Test
    public void testSetRelYear() {
        int expResult = 1980;
        instance2.setRelYear(1980);
        assertEquals("The song release years do not match after setting", expResult, instance2.getRelYear());
    }

    /**
     * Test of getPrice method, of class Song.
     */
    @Test
    public void testGetPrice() {
        double expResult = 0.99;
        double result = instance2.getPrice();
        assertEquals("The songs prices do not match after getting", expResult, result, 0.0);
    }

    /**
     * Test of setPrice method, of class Song.
     */
    @Test
    public void testSetPrice() {
        double expResult = 10.0;
        instance2.setPrice(10.0);
        assertEquals("The songs prices do not match after setting", expResult, instance2.getPrice(), 0.0);
    }

    /**
     * Test of getLicence method, of class Song.
     */
    @Test
    public void testGetLicence() {
        String expResult = "GNU";
        String result = instance2.getLicence();
        assertEquals("The songs licences do not match after getting", expResult, result);
    }

    /**
     * Test of setLicence method, of class Song.
     */
    @Test
    public void testSetLicence() {
        String expResult = "MIT";
        instance2.setLicence("MIT");
        assertEquals("The songs licences do not match after setting", expResult, instance2.getLicence());
    }

    /**
     * Test of toString method, of class Song.
     */
    @Test
    public void testToString() {
        String expResult = "Song{songId=0, title=Bohemian Rhapsody, artist=Queen, genre=Rock, relYear=1975, price=0.99, licence=GNU}";
        String result = instance2.toString();
        assertEquals("The songs toStrings do not match", expResult, result);
    }
    
}
