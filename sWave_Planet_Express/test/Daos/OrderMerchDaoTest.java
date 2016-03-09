package Daos;

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
 */
public class OrderMerchDaoTest {
    
    OrderMerch om;
    OrderMerchDao instance;
    
    public OrderMerchDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        instance = new OrderMerchDao();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of createOrder method, of class OrderMerchDao.
     */
    @Test
    public void testCreateOrder() {
        om = new OrderMerch();
        om.setOrderId(-2);
        int expResult = 0;
        int result = instance.createOrder(om);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getOrderMerchInOrder method, of class OrderMerchDao.
     */
    @Test
    public void testGetOrderMerchInOrder() {
        ArrayList<OrderMerch> om = instance.getOrderMerchInOrder(2);
        for(OrderMerch o : om) {
            System.out.println(o);
        }
    }
    
}
