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
public class OrderTest {
    Order instance;
    public OrderTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        instance = new Order();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getOrderId method being valid, of class Order.
     */
    @Test
    public void testGetOrderIdValid() {
        int expResult = 0;
        int result = instance.getOrderId();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getOrderId method being invalid, of class Order.
     */
    @Test
    public void testGetOrderIdInvalid() {
        boolean expResult = false;
        boolean result = instance.getOrderId() == -1;
        assertEquals(expResult, result);
    }

    /**
     * Test of setOrderId method being valid, of class Order.
     */
    @Test
    public void testSetOrderIdValid() {
        int orderId = 1;
        instance.setOrderId(orderId);
        assertEquals(orderId, instance.getOrderId());
    }
    
    /**
     * Test of setOrderId method being invalid, of class Order.
     */
    @Test
    public void testSetOrderIdInvalid() {
        boolean expResult = false;
        instance.setOrderId(1);
        boolean result = instance.getOrderId() == 0;
        assertEquals(expResult, result);
    }

    /**
     * Test of getUserId method being valid, of class Order.
     */
    @Test
    public void testGetUserIdValid() {
        int expResult = 0;
        int result = instance.getUserId();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getUserId method being invalid, of class Order.
     */
    @Test
    public void testGetUserIdInvalid() {
        boolean expResult = false;
        boolean result = instance.getUserId() == -1;
        assertEquals(expResult, result);
    }

    /**
     * Test of setUserId method being valid, of class Order.
     */
    @Test
    public void testSetUserIdValid() {
        int userId = 0;
        instance.setUserId(userId);
        assertEquals(userId, instance.getUserId());
    }
    
    /**
     * Test of setUserId method being invalid, of class Order.
     */
    @Test
    public void testSetUserIdInvalid() {
        boolean expResult = false;
        instance.setUserId(-1);
        boolean result = instance.getUserId() == 0;
        assertEquals(expResult, result);
    }

    /**
     * Test of getDateOrdered method being valid, of class Order.
     */
    @Test
    public void testGetDateOrderedValid() {
        String expResult = "enter date";
        String result = instance.getDateOrdered();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getDateOrdered method being invalid, of class Order.
     */
    @Test
    public void testGetDateOrderedInvalid() {
        boolean expResult = false;
        boolean result = instance.getDateOrdered().equals("enter datee");
        assertEquals(expResult, result);
    }
    
    /**
     * Test of setDateOrdered method being valid, of class Order.
     */
    @Test
    public void testSetDateOrderedValid() {
        String dateOrdered = "new date";
        instance.setDateOrdered(dateOrdered);
        assertEquals(dateOrdered, instance.getDateOrdered());
    }
    
    /**
     * Test of setDateOrdered method being invalid, of class Order.
     */
    @Test
    public void testSetDateOrderedInvalid() {
        boolean expResult = false;
        instance.setDateOrdered("new date");
        boolean result = instance.getDateOrdered().equals("n3w date");
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getOrderId method being valid, of class Order.
     */
    @Test
    public void testGetTotalValid() {
        double expResult = 0.0;
        double result = instance.getTotal();
        assertEquals(expResult, result,.2);
    }
    
    /**
     * Test of getOrderId method being invalid, of class Order.
     */
    @Test
    public void testGetTotalInvalid() {
        boolean expResult = false;
        boolean result = instance.getTotal() == 0.01;
        assertEquals(expResult, result);
    }

    /**
     * Test of setOrderId method being valid, of class Order.
     */
    @Test
    public void testSetTotalValid() {
        double total = 9.99;
        instance.setTotal(total);
        assertEquals(total, instance.getTotal(),.2);
    }

    /**
     * Test of setOrderId method being invalid, of class Order.
     */
    @Test
    public void testSetTotalInvalid() {
        boolean expResult = false;
        instance.setTotal(9.99);
        boolean result = instance.getTotal() == 0.0;
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method being valid, of class Order.
     */
    @Test
    public void testToStringValid() {
        String expResult = "Order{orderId=0, userId=0, dateOrdered=enter date, total=0.0}";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of toString method being invalid, of class Order.
     */
    @Test
    public void testToStringInvalid() {
        boolean expResult = false;
        boolean result = instance.toString().equals("Orders{orderId=2, userId=4, dateOrdered=enter date,  total=2.2}");
        assertEquals(expResult, result);
    }
    
}
