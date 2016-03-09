package Dtos;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Austin
 */
public class CartItemTest {
    CartItem instance;
    public CartItemTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        instance = new CartItem();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getType method being valid, of class CartItem.
     */
    @Test
    public void testGetTypeValid() {
        boolean expResult = false;
        boolean result = instance.getType();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getType method being invalid, of class CartItem.
     */
    @Test
    public void testGetTypeInvalid() {
        boolean expResult = false;
        boolean result = instance.getType() == true;
        assertEquals(expResult, result);
    }

    /**
     * Test of setType method being valid, of class CartItem.
     */
    @Test
    public void testSetTypeValid() {
        boolean expResult = true;
        instance.setType(true);
        boolean result = instance.getType() == true;
        assertEquals(expResult, result);
    }
    
    /**
     * Test of setType method being invalid, of class CartItem.
     */
    @Test
    public void testSetTypeInvalid() {
        boolean expResult = false;
        instance.setType(true);
        boolean result = instance.getType() == false;
        assertEquals(expResult, result);
    }

    /**
     * Test of getProdId method being valid, of class CartItem.
     */
    @Test
    public void testGetProdIdValid() {
        boolean expResult = true;
        boolean result = instance.getProdId() == 0;
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getProdId method being invalid, of class CartItem.
     */
    @Test
    public void testGetProdIdInvalid() {
        boolean expResult = false;
        boolean result = instance.getProdId() == 1;
        assertEquals(expResult, result);
    }

    /**
     * Test of setProdId method being valid, of class CartItem.
     */
    @Test
    public void testSetProdIdValid() {
        boolean expResult = true;
        instance.setProdId(0);
        boolean result = instance.getProdId() == 0;
        assertEquals(expResult, result);
    }
    
    /**
     * Test of setProdId method being invalid, of class CartItem.
     */
    @Test
    public void testSetProdIdInvalid() {
        boolean expResult = false;
        instance.setProdId(1);
        boolean result = instance.getProdId() == 0;
        assertEquals(expResult, result);
    }

    /**
     * Test of getQty method being valid, of class CartItem.
     */
    @Test
    public void testGetQtyValid() {
        int expResult = 0;
        int result = instance.getQty();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getQty method being invalid, of class CartItem.
     */
    @Test
    public void testGetQtyInvalid() {
        boolean expResult = false;
        boolean result = instance.getQty() == 1;
        assertEquals(expResult, result);
    }

    /**
     * Test of setQty method being valid, of class CartItem.
     */
    @Test
    public void testSetQtyValid() {
        boolean expResult = true;
        instance.setQty(0);
        boolean result = instance.getQty() == 0;
        assertEquals(expResult, result);
    }
    
    /**
     * Test of setQty method being invalid, of class CartItem.
     */
    @Test
    public void testSetQtyInvalid() {
        boolean expResult = false;
        instance.setQty(1);
        boolean result = instance.getQty() == 0;
        assertEquals(expResult, result);
    }

    /**
     * Test of getPrice method being valid, of class CartItem.
     */
    @Test
    public void testGetPriceValid() {
        boolean expResult = true;
        boolean result = instance.getPrice() == 10.0;
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getPrice method being invalid, of class CartItem.
     */
    @Test
    public void testGetPriceInvalid() {
        boolean expResult = false;
        boolean result = instance.getPrice() == 10.01;
        assertEquals(expResult, result);
    }

    /**
     * Test of setPrice method being valid, of class CartItem.
     */
    @Test
    public void testSetPriceValid() {
        boolean expResult = true;
        instance.setPrice(0.0);
        boolean result = instance.getPrice() == 0.0;
        assertEquals(expResult, result);
    }
    
    /**
     * Test of setPrice method being valid, of class CartItem.
     */
    @Test
    public void testSetPriceInvalid() {
        boolean expResult = false;
        instance.setPrice(0.0);
        boolean result = instance.getPrice() == 10.0;
        assertEquals(expResult, result);
    }
}
