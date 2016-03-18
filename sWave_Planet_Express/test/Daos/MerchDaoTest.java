package Daos;

import Dtos.Merch;
import Dtos.OrderMerch;
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
 * @author Austin
 */
public class MerchDaoTest {
    
    Merch m;
    MerchDao instance = new MerchDao();;
    public MerchDaoTest() {
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
     * Test of createMerch method, of class MerchDao.
     */
//    @Test
//    public void testCreateMerch() {
//        
//        m = new Merch();
//        int expResult = 0;
//        int result = instance.createMerch(m);
//        assertEquals(expResult, result);
//    }
    
    /**
     * Test of viewMerchAlpha method, of class MerchDao.
     */
    @Test
    public void testViewMerchAlpha() {
        ArrayList<Merch> merch = instance.viewMerchAlpha();
        Merch m = new Merch("Mug", 9.99);
        m.setMerchId(-1);
        assertEquals(m, merch.get(0));
    }
    
    /**
     * Test of getMerchInOrder method, of class MerchDao.
     */
    @Test
    public void testGetMerchInOrder() {
        boolean expResult = true;
        ArrayList<OrderMerch> orderMerch = new ArrayList();
        OrderMerch om = new OrderMerch(-1,-1,1,9.99,null);
        orderMerch.add(om);
        ArrayList<Merch> merchList = instance.getMerchInOrder(orderMerch);
        boolean result = merchList.size() > 0;
        assertEquals(expResult, result);
    }
    
    @Test
    public void testSearchMerch() {
        boolean expResult = true;
        ArrayList<Merch> merch = instance.searchMerch("Mug");
        boolean result = merch.size() > 0;
        assertEquals(expResult, result);
    }
}
