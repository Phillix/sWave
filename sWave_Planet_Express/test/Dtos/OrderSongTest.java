package Dtos;

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
public class OrderSongTest {
    
    OrderSong instance;
    public OrderSongTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        instance = new OrderSong();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getOrderId method being valid, of class OrderSong.
     */
    @Test
    public void testGetOrderIdValid() {
        int expResult = -1;
        int result = instance.getOrderId();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getOrderId method being invalid, of class OrderSong.
     */
    @Test
    public void testGetOrderIdInvalid() {
        boolean expResult = false;
        boolean result = instance.getOrderId() == -2;
        assertEquals(expResult, result);
    }

    /**
     * Test of setOrderId method being valid, of class OrderSong.
     */
    @Test
    public void testSetOrderIdValid() {
        int orderId = 0;
        instance.setOrderId(orderId);
        assertEquals(orderId, instance.getOrderId());
    }
    
    /**
     * Test of setOrderId method being invalid, of class OrderSong.
     */
    @Test
    public void testSetOrderIdInValid() {
        boolean expResult = false;
        instance.setOrderId(-43);
        boolean result = instance.getOrderId() == -1;
        assertEquals(expResult, result);
    }

    /**
     * Test of getSongId method being valid, of class OrderSong.
     */
    @Test
    public void testGetSongIdValid() {
        int expResult = -1;
        int result = instance.getSongId();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getSongId method being invalid, of class OrderSong.
     */
    @Test
    public void testGetSongIdInvalid() {
        boolean expResult = false;
        boolean result = instance.getSongId() == -2;
        assertEquals(expResult, result);
    }

    /**
     * Test of setSongId method being valid, of class OrderSong.
     */
    @Test
    public void testSetSongIdValid() {
        int songId = 0;
        instance.setSongId(songId);
        assertEquals(songId, instance.getSongId());
    }
    
    /**
     * Test of setSongId method being invalid, of class OrderSong.
     */
    @Test
    public void testSetSongIdInvalid() {
        boolean expResult = false;
        instance.setSongId(-23);
        boolean result = instance.getSongId() == -1;
        assertEquals(expResult, result);
    }

    /**
     * Test of getPricePaid method being valid, of class OrderSong.
     */
    @Test
    public void testGetPricePaidValid() {
        double expResult = 20.0;
        double result = instance.getPricePaid();
        assertEquals(expResult, result, 0.0);
    }
    
    /**
     * Test of getPricePaid method being invalid, of class OrderSong.
     */
    @Test
    public void testGetPricePaidInvalid() {
        boolean expResult = false;
        boolean result = instance.getPricePaid() == 20.00001;
        assertEquals(expResult, result);
    }

    /**
     * Test of setPricePaid method being valid, of class OrderSong.
     */
    @Test
    public void testSetPricePaidValid() {
        double pricePaid = 0.0;
        instance.setPricePaid(pricePaid);
        assertEquals(pricePaid, instance.getPricePaid(),0.01);
    }
    
    /**
     * Test of setPricePaid method being invalid, of class OrderSong.
     */
    @Test
    public void testSetPricePaidInvalid() {
        boolean expResult = false;
        instance.setPricePaid(120.1);
        boolean result = instance.getPricePaid() == 0.0;
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method being valid, of class OrderSong.
     */
    @Test
    public void testToStringValid() {
        String expResult = "OrderSong{orderId=-1, songId=-1, pricePaid=20.0}";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of toString method being invalid, of class OrderSong.
     */
    @Test
    public void testToStringInvalid() {
        boolean expResult = false;
        boolean result = instance.toString().equals("Ordersong{orderId=-3, songId=1, priceP4id=20,0}");
        assertEquals(expResult, result);
    }
    
}
