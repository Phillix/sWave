package Daos;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Phillix
 * @author Austin
 */
public class AdDaoTest {
    
    AdDao instance = new AdDao();
    public AdDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getAd method, of class AdDao.
     */
    @Test
    public void testGetAd() {
        int id = 1;
        String result = instance.getAd(id).toString();
        String expResult = "Ad{adId=1, adUrl=ads/test.html}";
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getMaxAdId method, of class AdDao.
     */
    @Test
    public void testGetMaxAdId() {
        int max = 2;
        int result = instance.getMaxAdId();
        assertEquals(max, result);
    }
}
