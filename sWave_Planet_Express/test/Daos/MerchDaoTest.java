package Daos;

import Dtos.Merch;
import Dtos.OrderMerch;
import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Class for testing MerchDao methods, for valid and invalid
 * @author Phillix
 * @author Austin
 */
public class MerchDaoTest {
    static MyDataSource ds = new MyDataSource();
    static Merch m;
    static MerchDao instance;
    
    @BeforeClass
    public static void setUpClass() {
        instance = new MerchDao(ds);
        
    }
    
    @Before
    public void setUp() {
        m = new Merch("Jar of Beans", 10.0, null);
        m.setMerchId(10);
        instance.createMerch(m);
    }
    
    @After
    public void tearDown() {
        instance.removeMerch(10);
    }

    /**
     * Test of createMerch method being valid, of class MerchDao.
     */
    @Test
    public void testCreateMerchValid() {
        int result = instance.searchMerch("Jar of Beans").size();
        int expResult = 1;
        assertEquals(expResult, result);
    }
    
    /**
     * Test of createMerch method being invalid, of class MerchDao.
     */
    @Test
    public void testCreateMerchInvalid() {
        int result = instance.searchMerch("Jar of Beans2").size();
        int expResult = 0;
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getMerchById method being valid, of class MerchDao.
     */
    @Test
    public void testGetMerchByIdValid() {
        instance.createMerch(m);
        Merch m1 = instance.getMerchById(10);
        assertEquals(m, m1);
    }
    
    /**
     * Test of getMerchById method being invalid, of class MerchDao.
     */
    @Test
    public void testGetMerchByIdInvalid() {
        instance.createMerch(m);
        Merch m1 = instance.getMerchById(6);
        assertNotEquals(m, m1);
    }
    
    /**
     * Test of getMerchInOrder method being valid, of class MerchDao.
     */
    @Test
    public void testGetMerchInOrder() {
        boolean expResult = true;
        ArrayList<OrderMerch> orderMerch = new ArrayList();
        OrderMerch om = new OrderMerch(-1,10,1,9.99,null);
        orderMerch.add(om);
        ArrayList<Merch> merchList = instance.getMerchInOrder(orderMerch);
        boolean result = merchList.size() > 0;
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getMerchInOrder method being invalid, of class MerchDao.
     */
    @Test
    public void testGetMerchInOrderInvalid() {
        boolean expResult = false;
        ArrayList<OrderMerch> orderMerch = new ArrayList();
        OrderMerch om = new OrderMerch(-1,11,1,9.99,null);
        orderMerch.add(om);
        ArrayList<Merch> merchList = instance.getMerchInOrder(orderMerch);
        boolean result = merchList.size() > 0;
        assertEquals(expResult, result);
    }
    
    /**
     * Test of removeMerch method being invalid, of class MerchDao.
     */
    @Test
    public void testRemoveMerchValid() {
        boolean expResult = true;
        instance.removeMerch(10);
        boolean result = instance.getMerchById(10) == null;
        assertEquals(expResult, result);
    }
    
    /**
     * Test of removeMerch method being invalid, of class MerchDao.
     */
    @Test
    public void testRemoveMerchInValid() {
        boolean expResult = false;
        instance.removeMerch(10);
        boolean result = instance.getMerchById(10) != null;
        assertEquals(expResult, result);
    }
    
    /**
     * Test of searchMerch method being valid, of class MerchDao.
     */
    @Test
    public void testSearchMerchValid() {
        boolean expResult = true;
        ArrayList<Merch> merch = instance.searchMerch("Mug");
        boolean result = merch.size() > 0;
        assertEquals(expResult, result);
    }
    
    /**
     * Test of searchMerch method being valid, of class MerchDao.
     */
    @Test
    public void testSearchMerchInvalid() {
        boolean expResult = false;
        ArrayList<Merch> merch = instance.searchMerch("beans of jar");
        boolean result = merch.size() > 0;
        assertEquals(expResult, result);
    }
    
    /**
     * Test of viewMerchAlpha method being valid, of class MerchDao.
     */
    @Test
    public void testViewMerchAlphaValid() {
        boolean expResult = true;
        ArrayList<Merch> merch = instance.viewMerchAlpha();
        assertEquals(expResult, merch.contains(m));
    }
    
    /**
     * Test of viewMerchAlpha method being invalid, of class MerchDao.
     */
    @Test
    public void testViewMerchAlphaInvalid() {
        ArrayList<Merch> merch = instance.viewMerchAlpha();
        m.setMerchId(11);
        assertEquals(false, merch.contains(m));
    }
}
