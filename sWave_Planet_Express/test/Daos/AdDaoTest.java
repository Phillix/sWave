package Daos;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Class for testing AdDao methods, for valid and invalid
 * @author Phillix
 * @author Austin
 */
public class AdDaoTest {
    static MyDataSource ds = new MyDataSource();
    static AdDao instance;
    
    /**
     * Setting up the class
     */
    @BeforeClass
    public static void setUpClass() {
        instance = new AdDao(ds);
    }

    /**
     * Test of getAd method being valid, of class AdDao.
     */
    @Test
    public void testGetAdValid() {
        int id = 1;
        String result = instance.getAd(id).toString();
        String expResult = "Ad{adId=1, adUrl=ads/test.html}";
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getAd method being invalid, of class AdDao.
     */
    @Test
    public void testGetAdInvalid() {
        assertNull(instance.getAd(0));
    }
    
    /**
     * Test of getMaxAdId method being valid, of class AdDao.
     */
    @Test
    public void testGetMaxAdIdValid() {
        int max = 2;
        int result = instance.getMaxAdId();
        assertEquals(max, result);
    }
    
    /**
     * Test of getMaxAdId method being invalid, of class AdDao.
     */
    @Test
    public void testGetMaxAdIdInvalid() {
        int expResult = 40;
        int result = instance.getMaxAdId();
        assertNotEquals(expResult, result);
    }
}
