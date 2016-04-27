package Daos;

import Dtos.OrderMerch;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Class for testing OrderMerchDao methods, for valid and invalid
 * @author Austin
 * @author Phillix
 */
public class OrderMerchDaoTest {
    static MyDataSource ds = new MyDataSource();
    OrderMerch om;
    OrderMerch omg;
    static OrderMerchDao instance;
    
    @BeforeClass
    public static void setUpClass() {
        instance = new OrderMerchDao(ds);
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        om = new OrderMerch();
        omg = new OrderMerch();
        omg.setMerchId(2);
        instance.createOrder(om);
        instance.createOrder(omg);
    }
    
    @After
    public void tearDown() {
        instance.deleteOrderMerch(om.getOrderId());
        instance.deleteOrderMerch(omg.getOrderId());
    }
    
    /**
     * Test of createOrder method being valid, of class OrderMerchDao.
     */
    @Test
    public void testCreateOrderValid() {
        boolean result = instance.getOrderMerchInOrder(om.getOrderId()).size() == 0;
        boolean expResult = false;
        assertEquals(expResult, result);
    }
    
    /**
     * Test of createOrder method being invalid, of class OrderMerchDao.
     */
    @Test
    public void testCreateOrderInvalid() {
        boolean result = instance.getOrderMerchInOrder(3).size() == 0;
        boolean expResult = true;
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getOrderMerchInOrderValid method being valid, of class OrderMerchDao.
     */
    @Test
    public void testGetOrderMerchInOrderValid() {
        boolean result = instance.getOrderMerchInOrder(om.getOrderId()).size() == 2;
        boolean expResult = true;
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getOrderMerchInOrderValid method being invalid, of class OrderMerchDao.
     */
    @Test
    public void testGetOrderMerchInOrderInvalid() {
        boolean result = instance.getOrderMerchInOrder(om.getOrderId()).size() == 1;
        boolean expResult = false;
        assertEquals(expResult, result);
    }
    
    /**
     * Test of deleteOrderMerch method being valid, of class OrderMerchDao.
     */
    @Test
    public void testDeleteOrderMerchValid() {
        int result = instance.deleteOrderMerch(om.getOrderId());
        int expResult = 0;
        assertEquals(expResult, result);
    }
    
    /**
     * Test of deleteOrderMerch method being invalid, of class OrderMerchDao.
     */
    @Test
    public void testDeleteOrderMerchInvalid() {
        int result = instance.deleteOrderMerch(4);
        int expResult = -5;
        assertEquals(expResult, result);
    }
    
}
