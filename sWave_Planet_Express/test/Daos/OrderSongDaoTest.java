package Daos;

import Dtos.OrderSong;
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
 */
public class OrderSongDaoTest {
    
    OrderSongDao instance;
    public OrderSongDaoTest() {
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
     * Test of createOrderSong method, of class OrderSongDao.
     */
//    @Test
//    public void testCreateOrderSong() {
//        
//        OrderSong os = new OrderSong(-1,-1, 7.00);
//        instance = new OrderSongDao();
//        int expResult = 0;
//        int result = instance.createOrderSong(os);
//        assertEquals(expResult, result);
//    }
    
    /**
    * Test of getOrderSongInOrder method, of class OrderSongDao.
    */
    @Test
    public void testGetOrderSongInOrder() {
        
        instance = new OrderSongDao();
        ArrayList<OrderSong> os = instance.getOrderSongInOrder(-1);
        for(OrderSong o : os) {
            System.out.println(o);
        }
    }
}
