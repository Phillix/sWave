package Daos;

import Dtos.Order;
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
public class OrderDaoTest {
    
    OrderDao instance;
    public OrderDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        instance = new OrderDao();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of createOrder method, of class OrderDao.
     */
//    @Test
//    public void testCreateOrder() {
//        
//        int userId = 1;
//        Order o = new Order(userId);
//        int expResult = 0;
//        int result = instance.createOrder(o);
//        assertEquals(expResult, result);   
//    }
//    
    /**
     * Test of getUserOrders method, of class OrderDao.
     */
    @Test
    public void testGetUserOrders() {
        
        int userId = -1;
        Order o = new Order();
        o.setOrderId(-1);
        o.setDateOrdered("1970-1-1");
        o.setTotal(15.50);
        o.setUserId(-1);
        boolean expResult = true;
        ArrayList<Order> orders = instance.getUserOrders(userId);
        boolean result = orders.get(0).equals(o);
        assertEquals(expResult, result);
    }
    
}