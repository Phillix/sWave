package Dtos;

import java.util.Arrays;
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
        instance2 = new Song(-1, "Track 2", "Bohemian Rhapsody", "Queen", "Gr8 Hits", "Rock", 1975, 5.09, 0.99, "GNU", 0, null, songdata);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getSongId method being valid, of class Song.
     */
    @Test
    public void testGetSongIdValid() {
        int expResult = 0;
        int result = instance1.getSongId();
        assertEquals("The song id's do not match after getting valid", expResult, result);
    }

    /**
     * Test of getSongId method being invalid, of class Song.
     */
    @Test
    public void testGetSongIdInvalid() {
        boolean expResult = false;
        boolean result = instance1.getSongId() == 1;
        assertEquals("The song id's match after getting invalid", expResult, result);
    }

    /**
     * Test of setSongId method being valid, of class Song.
     */
    @Test
    public void testSetSongIdValid() {
        int expResult = 4;
        instance2.setSongId(4);
        assertEquals("The song ids do not match after setting valid", expResult, instance2.getSongId());
    }

    /**
     * Test of setSongId method being invalid, of class Song.
     */
    @Test
    public void testSetSongIdInvalid() {
        boolean expResult = false;
        instance2.setSongId(4);
        boolean result = instance2.getSongId() == 0;
        assertEquals("The song ids match after setting invalid", expResult, result);
    }

    /**
     * Test of getTitle method being valid, of class Song.
     */
    @Test
    public void testGetTitleValid() {
        String expResult = "Bohemian Rhapsody";
        String result = instance2.getTitle();
        assertEquals("The song titles do not match after getting valid", expResult, result);
    }

    /**
     * Test of getTitle method being invalid, of class Song.
     */
    @Test
    public void testGetTitleInvalid() {
        boolean expResult = false;
        boolean result = instance2.getTitle().equals("Bohemian Rhapsodys");
        assertEquals("The song titles match after getting invalid", expResult, result);
    }

    /**
     * Test of setTitle method being valid, of class Song.
     */
    @Test
    public void testSetTitleValid() {
        String expResult = "Rhapsody Bohemian";
        instance2.setTitle("Rhapsody Bohemian");
        assertEquals("The song titles do not match after setting valid", expResult, instance2.getTitle());
    }

    /**
     * Test of setTitle method being invalid, of class Song.
     */
    @Test
    public void testSetTitleInvalid() {
        boolean expResult = false;
        instance2.setTitle("Rhapsody Bohemian");
        boolean result = instance2.getTitle().equals("Bohemian Rhapsody");
        assertEquals("The song titles match after setting invalid", expResult, result);
    }

    /**
     * Test of getArtist method being valid, of class Song.
     */
    @Test
    public void testGetArtistValid() {
        String expResult = "Queen";
        String result = instance2.getArtist();
        assertEquals("The song artists do not match after getting valid", expResult, result);
    }

    /**
     * Test of getArtist method being invalid, of class Song.
     */
    @Test
    public void testGetArtistInvalid() {
        boolean expResult = false;
        boolean result = instance2.getArtist().equals("Qween");
        assertEquals("The song artists match after getting invalid", expResult, result);
    }

    /**
     * Test of setArtist method being valid, of class Song.
     */
    @Test
    public void testSetArtistValid() {
        String expResult = "Queeen";
        instance2.setArtist("Queeen");
        assertEquals("The song artists do not match after setting valid", expResult, instance2.getArtist());
    }

    /**
     * Test of setArtist method being invalid, of class Song.
     */
    @Test
    public void testSetArtistInvalid() {
        boolean expResult = false;
        instance2.setArtist("Queeen");
        boolean result = instance2.getArtist().equals("Queen");
        assertEquals("The song artists match after setting invalid", expResult, result);
    }

    /**
     * Test of getGenre method being valid, of class Song.
     */
    @Test
    public void testGetGenreValid() {
        String expResult = "Rock";
        String result = instance2.getGenre();
        assertEquals("The song genres do not match after getting valid", expResult, result);
    }

    /**
     * Test of getGenre method being invalid, of class Song.
     */
    @Test
    public void testGetGenreInvalid() {
        boolean expResult = false;
        boolean result = instance2.getGenre().equals("Stone");
        assertEquals("The song genres match after getting invalid", expResult, result);
    }

    /**
     * Test of setGenre method being valid, of class Song.
     */
    @Test
    public void testSetGenreValid() {
        String expResult = "Hard Rock";
        instance2.setGenre("Hard Rock");
        assertEquals("The song genres do not match after setting valid", expResult, instance2.getGenre());
    }

    /**
     * Test of setGenre method being invalid, of class Song.
     */
    @Test
    public void testSetGenreInvalid() {
        boolean expResult = false;
        instance2.setGenre("Hard Rock");
        boolean result = instance2.getGenre().equals("Rock");
        assertEquals("The song genres match after setting invalid", expResult, result);
    }

    /**
     * Test of getRelYear method being valid, of class Song.
     */
    @Test
    public void testGetYearValid() {
        int expResult = 1975;
        int result = instance2.getYear();
        assertEquals("The song release years do not match after getting valid", expResult, result);
    }

    /**
     * Test of getRelYear method being invalid, of class Song.
     */
    @Test
    public void testGetYearInvalid() {
        boolean expResult = false;
        boolean result = instance2.getYear() == 1976;
        assertEquals("The song release years match after getting invalid", expResult, result);
    }

    /**
     * Test of setRelYear method being valid, of class Song.
     */
    @Test
    public void testSetYearValid() {
        int expResult = 1980;
        instance2.setYear(1980);
        assertEquals("The song release years do not match after setting valid", expResult, instance2.getYear());
    }

    /**
     * Test of setRelYear method being invalid, of class Song.
     */
    @Test
    public void testSetYearInvalid() {
        boolean expResult = false;
        instance2.setYear(1980);
        boolean result = instance2.getYear() == 1975;
        assertEquals("The song release years match after setting invalid", expResult, result);
    }

    /**
     * Test of getPrice method being valid, of class Song.
     */
    @Test
    public void testGetPriceValid() {
        double expResult = 0.99;
        double result = instance2.getPrice();
        assertEquals("The songs prices do not match after getting valid", expResult, result, 0.0);
    }

    /**
     * Test of getPrice method being invalid, of class Song.
     */
    @Test
    public void testGetPriceInvalid() {
        boolean expResult = false;
        boolean result = instance2.getPrice() == .98;
        assertEquals("The songs prices match after getting invalid", expResult, result);
    }

    /**
     * Test of setPrice method being valid, of class Song.
     */
    @Test
    public void testSetPriceValid() {
        double expResult = 10.0;
        instance2.setPrice(10.0);
        assertEquals("The songs prices do not match after setting valid", expResult, instance2.getPrice(), 0.0);
    }

    /**
     * Test of setPrice method being invalid, of class Song.
     */
    @Test
    public void testSetPriceInvalid() {
        boolean expResult = false;
        instance2.setPrice(10.0);
        boolean result = instance2.getPrice() == .99;
        assertEquals("The songs prices match after setting invalid", expResult, result);
    }

    /**
     * Test of getLicence method being valid, of class Song.
     */
    @Test
    public void testGetLicenseValid() {
        String expResult = "GNU";
        String result = instance2.getLicense();
        assertEquals("The songs licences do not match after getting valid", expResult, result);
    }

    /**
     * Test of getLicence method being invalid, of class Song.
     */
    @Test
    public void testGetLicenseInvalid() {
        boolean expResult = false;
        boolean result = instance2.getLicense().equals("GNU1");
        assertEquals("The songs licences match after getting invalid", expResult, result);
    }

    /**
     * Test of setLicence method being valid, of class Song.
     */
    @Test
    public void testSetLicenseValid() {
        String expResult = "MIT";
        instance2.setLicense("MIT");
        assertEquals("The songs licences do not match after setting valid", expResult, instance2.getLicense());
    }

    /**
     * Test of setLicence method being invalid, of class Song.
     */
    @Test
    public void testSetLicenseInvalid() {
        boolean expResult = false;
        instance2.setLicense("MIT");
        boolean result = instance2.getLicense().equals("GNU");
        assertEquals("The songs licences match after setting invalid", expResult, result);
    }

    /**
     * Test of getSongdata method being valid, of class Song.
     */
    @Test
    public void testGetSongdataValid() {
        byte expResult[] = new byte[5];
        expResult[0] = 5;
        expResult[1] = 4;
        expResult[2] = 3;
        expResult[3] = 2;
        expResult[4] = 1;
        byte result[] = instance2.getSongdata();
        assertArrayEquals("The songdata does not match after getting valid", expResult, result);
    }

    /**
     * Test of getSongdata method being invalid, of class Song.
     */
    @Test
    public void testGetSongdataInvalid() {
        byte instance3[] = new byte[5];
        instance3[0] = 1;
        instance3[1] = 2;
        instance3[2] = 3;
        instance3[3] = 4;
        instance3[4] = 5;
        boolean expResult = false;
        byte instance4[] = instance2.getSongdata();
        boolean result = Arrays.equals(instance3, instance4);
        assertEquals("The songdata matches after getting invalid", expResult, result);
    }

    /**
     * Test of setSongdata method being valid, of class Song.
     */
    @Test
    public void testSetSongdataValid() {
        byte expResult[] = new byte[5];
        expResult[0] = 1;
        expResult[1] = 2;
        expResult[2] = 3;
        expResult[3] = 4;
        expResult[4] = 5;
        instance2.setSongdata(expResult);
        assertArrayEquals("The songdata does not match after setting valid", expResult, instance2.getSongdata());
    }

    /**
     * Test of setSongdata method being invalid, of class Song.
     */
    @Test
    public void testSetSongdataInvalid() {
        byte instance3[] = instance2.getSongdata();
        byte instance4[] = new byte[5];
        instance4[0] = 1;
        instance4[1] = 2;
        instance4[2] = 3;
        instance4[3] = 4;
        instance4[4] = 5;
        instance2.setSongdata(instance4);
        boolean expResult = false;
        boolean result = Arrays.equals(instance3, instance4);
        assertEquals("The songdata matches after setting invalid", expResult, result);
    }

    /**
     * Test of toString method being valid, of class Song.
     */
    @Test
    public void testToStringValid() {
        String expResult = "Song{songId=-1, filename=Track 2, title=Bohemian Rhapsody, artist=Queen, album=Gr8 Hits, genre=Rock, year=1975, duration=5.09, price=0.99, license=GNU, playCount=0}";
        String result = instance2.toString();
        assertEquals("The songs toStrings do not match valid", expResult, result);
    }

    /**
     * Test of toString method being invalid, of class Song.
     */
    @Test
    public void testToStringInvalid() {
        boolean expResult = false;
        boolean result = instance2.toString().equals("SongsongId=-1, filename=Track 2, title=Bohemian Rhapsody, artist=Queen, album=Gr9 Hits, genre=mETAL, relYear=1975, duration = 5 price=1.99, license=MIT}");
        assertEquals("The songs toStrings matches invalid", expResult, result);
    }
}
