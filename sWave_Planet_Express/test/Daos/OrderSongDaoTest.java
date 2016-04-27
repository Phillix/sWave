package Daos;

import Dtos.OrderSong;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Class for testing OrderSongDao methods, for valid and invalid
 * @author Austin
 * @author Phillix
 */
public class OrderSongDaoTest {
    static MyDataSource ds = new MyDataSource();
    static OrderSongDao instance;
    static OrderSong os;
    static OrderSong os1;
    public OrderSongDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        instance = new OrderSongDao(ds);
    }
    
    @Before
    public void setUp() {
        os = new OrderSong();
        instance.createOrderSong(os);
    }
    
    @After
    public void tearDown() {
        instance.deleteOrderSong(os.getOrderId());
    }

    /**
     * Test of createOrder method being valid, of class OrderSongDao.
     */
    @Test
    public void testCreateOrderValid() {
        boolean result = instance.getOrderSongInOrder(os.getOrderId()).size() == 0;
        boolean expResult = false;
        assertEquals(expResult, result);
    }
    
    /**
     * Test of createOrder method being invalid, of class OrderSongDao.
     */
    @Test
    public void testCreateOrderInvalid() {
        boolean result = instance.getOrderSongInOrder(3).size() == 0;
        boolean expResult = true;
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getOrderSongInOrderValid method being valid, of class OrderSongDao.
     */
    @Test
    public void testGetOrderSongInOrderValid() {
        boolean result = instance.getOrderSongInOrder(os.getOrderId()).size() == 1;
        boolean expResult = true;
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getOrderSongInOrderValid method being invalid, of class OrderSongDao.
     */
    @Test
    public void testGetOrderSongInOrderInvalid() {
        boolean result = instance.getOrderSongInOrder(os.getOrderId()).size() == 0;
        boolean expResult = false;
        assertEquals(expResult, result);
    }
    
    /**
     * Test of deleteOrderSong method being valid, of class OrderSongDao.
     */
    @Test
    public void testDeleteOrderSongValid() {
        int result = instance.deleteOrderSong(os.getOrderId());
        int expResult = 0;
        assertEquals(expResult, result);
    }
    
    /**
     * Test of deleteOrderSong method being invalid, of class OrderSongDao.
     */
    @Test
    public void testDeleteOrderSongInvalid() {
        int result = instance.deleteOrderSong(4);
        int expResult = -5;
        assertEquals(expResult, result);
    }
}
