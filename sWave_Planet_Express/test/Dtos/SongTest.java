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
        byte songdata[] = new byte[5];
        songdata[0] = 5;
        songdata[1] = 4;
        songdata[2] = 3;
        songdata[3] = 2;
        songdata[4] = 1;
        instance2 = new Song(-1, "Bohemian Rhapsody", "Queen", "Rock", 1975, 0.99, "GNU", songdata);
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
    public void testGetLicense() {
        String expResult = "GNU";
        String result = instance2.getLicense();
        assertEquals("The songs licences do not match after getting", expResult, result);
    }

    /**
     * Test of setLicence method, of class Song.
     */
    @Test
    public void testSetLicense() {
        String expResult = "MIT";
        instance2.setLicense("MIT");
        assertEquals("The songs licences do not match after setting", expResult, instance2.getLicense());
    }

    /**
     * Test of getSongdata method, of class Song.
     */
    @Test
    public void testGetSongdata() {
        byte expResult[] = new byte[5];
        expResult[0] = 5;
        expResult[1] = 4;
        expResult[2] = 3;
        expResult[3] = 2;
        expResult[4] = 1;
        byte result[] = instance2.getSongdata();
        assertArrayEquals("The songdata does not match after getting", expResult, result);
    }

    /**
     * Test of setSongdata method, of class Song.
     */
    @Test
    public void testSetSongdata() {
        byte expResult[] = new byte[5];
        expResult[0] = 1;
        expResult[1] = 2;
        expResult[2] = 3;
        expResult[3] = 4;
        expResult[4] = 5;
        instance2.setSongdata(expResult);
        assertArrayEquals("The songdata does not match after setting", expResult, instance2.getSongdata());
    }

    /**
     * Test of toString method, of class Song.
     */
    @Test
    public void testToString() {
        String expResult = "Song{songId=-1, title=Bohemian Rhapsody, artist=Queen, genre=Rock, relYear=1975, price=0.99, license=GNU}";
        String result = instance2.toString();
        assertEquals("The songs toStrings do not match", expResult, result);
    }
}
