package Daos;

import Dtos.Order;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Austin
 * @author Phillix
 */
public class OrderDaoTest {
    static MyDataSource ds = new MyDataSource();
    static OrderDao instance;
    static int orderId;
    static Order o;
    static final int USERID = 1;
    public OrderDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        instance = new OrderDao(ds);
    }
    
    @Before
    public void setUp() {
        o = new Order(USERID, 10);
        instance.createOrder(o);
        orderId = instance.getCurrentOrder(USERID).getOrderId();
    }
    
    @After
    public void tearDown() {
        instance.deleteOrder(orderId);
        
    }
    
    /**
     * Test for createOrder method being valid, of class OrderDao.
     */
    @Test
    public void testCreateOrderValid() {
        Order o1 = instance.getCurrentOrder(USERID);
        boolean result = o1 != null;
        boolean expResult = true;
        assertEquals(expResult, result);
    }
    
    /**
     * Test for createOrder method being invalid, of class OrderDao.
     */
    @Test
    public void testCreateOrderInvalid() {
        Order o1 = instance.getCurrentOrder(USERID);
        int result = orderId;
        int expResult = orderId + 1;
        assertNotEquals(expResult, result);
    }
    
    /**
     * Test for getCurrentOrder method being valid, of class OrderDao.
     */
    @Test
    public void testGetCurrentOrderValid() {
        Order o1 = instance.getCurrentOrder(USERID);
        o.setOrderId(o1.getOrderId());
        boolean result = o.equals(o1);
        boolean expResult = true;
        assertEquals(expResult, result);
    }
    
    /**
     * Test for getCurrentOrder method being invalid, of class OrderDao.
     */
    @Test
    public void testGetCurrentOrderInvalid() {
        Order o1 = instance.getCurrentOrder(USERID);
        boolean result = o.equals(o1);
        boolean expResult = false;
        assertEquals(expResult, result);
    }
    
    /**
     * Test for getUserOrders method being valid, of class OrderDao.
     */
    @Test
    public void testGetUserOrdersValid() {
        boolean result = instance.getUserOrders(USERID).size() == 1;
        boolean expResult = true;
        assertEquals(expResult, result);
    }
    
    /**
     * Test for getUserOrders method being invalid, of class OrderDao.
     */
    @Test
    public void testGetUserOrdersInvalid() {
        boolean result = instance.getUserOrders(3).size() > 0;
        boolean expResult = false;
        assertEquals(expResult, result);
    }
}
