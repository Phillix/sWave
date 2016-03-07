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
public class OrderMerchTest {
    
    OrderMerch instance;
    public OrderMerchTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        instance = new OrderMerch();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getOrderId method being valid, of class OrderMerch.
     */
    @Test
    public void testGetOrderIdValid() {
        int expResult = -1;
        int result = instance.getOrderId();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getOrderId method being invalid, of class OrderMerch.
     */
    @Test
    public void testGetOrderIdInvalid() {
        boolean expResult = false;
        boolean result = instance.getOrderId() == -2;
        assertEquals(expResult, result);
    }

    /**
     * Test of setOrderId method being valid, of class OrderMerch.
     */
    @Test
    public void testSetOrderIdValid() {
        int orderId = 0;
        instance.setOrderId(orderId);
        assertEquals(orderId, instance.getOrderId());
    }
    
    /**
     * Test of setOrderId method being invalid, of class OrderMerch.
     */
    @Test
    public void testSetOrderIdInvalid() {
        boolean expResult = false;
        instance.setOrderId(0);
        boolean result = instance.getOrderId() == -1;
        assertEquals(expResult, result);
    }

    /**
     * Test of getMerchId method being valid, of class OrderMerch.
     */
    @Test
    public void testGetMerchIdValid() {
        int expResult = -1;
        int result = instance.getMerchId();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getMerchId method being invalid, of class OrderMerch.
     */
    @Test
    public void testGetMerchIdInvalid() {
        boolean expResult = false;
        boolean result = instance.getMerchId() == -2;
        assertEquals(expResult, result);
    }

    /**
     * Test of setMerchId method being valid, of class OrderMerch.
     */
    @Test
    public void testSetMerchIdValid() {
        int merchId = 0;
        instance.setMerchId(merchId);
        assertEquals(merchId, instance.getMerchId());
    }
    
    /**
     * Test of setMerchId method being invalid, of class OrderMerch.
     */
    @Test
    public void testSetMerchIdInvalid() {
        boolean expResult = false;
        instance.setMerchId(-4);
        boolean result = instance.getMerchId() == -1;
        assertEquals(expResult, result);
    }

    /**
     * Test of getQty method being valid, of class OrderMerch.
     */
    @Test
    public void testGetQtyValid() {
        int expResult = -1;
        int result = instance.getQty();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getQty method being invalid, of class OrderMerch.
     */
    @Test
    public void testGetQtyInvalid() {
        boolean expResult = false;
        boolean result = instance.getQty() == -2;
        assertEquals(expResult, result);
    }

    /**
     * Test of setQty method being valid, of class OrderMerch.
     */
    @Test
    public void testSetQtyValid() {
        int qty = 0;
        instance.setQty(qty);
        assertEquals(qty, instance.getQty());
    }

    /**
     * Test of setQty method being invalid, of class OrderMerch.
     */
    @Test
    public void testSetQtyInvalid() {
        boolean expResult = false;
        instance.setQty(2);
        boolean result = instance.getQty() == 3;
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getPricePaid method being valid, of class OrderMerch.
     */
    @Test
    public void testGetPricePaidValid() {
        double expResult = -9.99;
        double result = instance.getPricePaid();
        assertEquals(expResult, result, 0.0);
    }
    
    /**
     * Test of getPricePaid method being invalid, of class OrderMerch.
     */
    @Test
    public void testGetPricePaidInvalid() {
        boolean expResult = false;
        boolean result = instance.getPricePaid() == -9.98;
        assertEquals(expResult, result);
    }

    /**
     * Test of setPricePaid method being valid, of class OrderMerch.
     */
    @Test
    public void testSetPricePaidValid() {
        double pricePaid = 0.0;
        instance.setPricePaid(pricePaid);
        assertEquals(pricePaid, instance.getPricePaid(),.01);
    }
    
    /**
     * Test of setPricePaid method being invalid, of class OrderMerch.
     */
    @Test
    public void testSetPricePaidInvalid() {
        boolean expResult = false;
        instance.setPricePaid(20);
        boolean result = instance.getPricePaid() == 19.9999;
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method being valid, of class OrderMerch.
     */
    @Test
    public void testToStringValid() {
        String expResult = "OrderMerch{orderId=-1, merchId=-1, qty=-1, pricePaid=-9.99}";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of toString method being invalid, of class OrderMerch.
     */
    @Test
    public void testToStringInvalid() {
        boolean expResult = false;
        boolean result = instance.toString().equals("OrderMerch{orderId=-4, merchId=2, qtty=-1, ,pricePaid=-9.939");
        assertEquals(expResult, result);
    }
    
}
