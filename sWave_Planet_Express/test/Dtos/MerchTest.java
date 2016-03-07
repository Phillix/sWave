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
public class MerchTest {
    Merch instance;
    public MerchTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        instance = new Merch();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getMerchId method being valid, of class Merch.
     */
    @Test
    public void testGetMerchIdValid() {
        int expResult = 0;
        int result = instance.getMerchId();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getMerchId method being invalid, of class Merch.
     */
    @Test
    public void testGetMerchIdInvalid() {
        boolean expResult = false;
        boolean result = instance.getMerchId() == 2;
        assertEquals(expResult, result);
    }
    

    /**
     * Test of setMerchId method being valid, of class Merch.
     */
    @Test
    public void testSetMerchIdValid() {
        int merchId = 1;
        instance.setMerchId(merchId);
        assertEquals(merchId, instance.getMerchId());
    }
    
    /**
     * Test of setMerchId method being invalid, of class Merch.
     */
    @Test
    public void testSetMerchIdInvalid() {
        boolean expResult = false;
        instance.setMerchId(1);
        boolean result = instance.getMerchId() == 2;
        assertEquals(expResult, result);
    }

    /**
     * Test of getTitle method being valid, of class Merch.
     */
    @Test
    public void testGetTitleValid() {
        String expResult = "default";
        String result = instance.getTitle();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getTitle method being invalid, of class Merch.
     */
    @Test
    public void testGetTitleInvalid() {
        boolean expResult = false;
        boolean result = instance.getTitle().equals("default1");
        assertEquals(expResult, result);
    }

    /**
     * Test of setTitle method being valid, of class Merch.
     */
    @Test
    public void testSetTitleValid() {
        String title = "new title";
        instance.setTitle(title);
        assertEquals(title, instance.getTitle());
    }
    
    /**
     * Test of setTitle method being invalid, of class Merch.
     */
    @Test
    public void testSetTitleInvalid() {
        boolean expResult = false;
        instance.setTitle("john");
        boolean result = instance.getTitle().equals("johnny");
        assertEquals(result, expResult);
    }

    /**
     * Test of getPrice method being valid, of class Merch.
     */
    @Test
    public void testGetPriceValid() {
        double expResult = 9.99;
        double result = instance.getPrice();
        assertEquals(expResult, result, 0.0);
    }
    
    /**
     * Test of getPrice method being invalid, of class Merch.
     */
    @Test
    public void testGetPriceInvalid() {
        boolean expResult = false;
        boolean result = instance.getPrice() == 10.0;
        assertEquals(expResult, result);
    }

    /**
     * Test of setPrice method being valid, of class Merch.
     */
    @Test
    public void testSetPriceValid() {
        double price = 7.0;
        instance.setPrice(price);
        assertEquals(price, instance.getPrice(),.2);
    }
    
    /**
     * Test of setPrice method being invalid, of class Merch.
     */
    @Test
    public void testSetPriceInValid() {
        boolean expResult = false;
        instance.setPrice(20);
        boolean result = instance.getPrice() == 20.001;
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method being valid, of class Merch.
     */
    @Test
    public void testToStringValid() {
        String expResult = "Merch{merchId=0, title=default, price=9.99}";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of toString method being invalid, of class Merch.
     */
    @Test
    public void testToStringInvalid() {
        boolean expResult = false;
        boolean result = instance.toString().equals("Merch{merchId=20, title=default4, pr1ce=93.99}");
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method being valid, of class Merch.
     */
    @Test
    public void testEqualsValid() {
        Merch instance2 = new Merch();
        boolean expResult = true;
        boolean result = instance.equals(instance2);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of equals method being invalid, of class Merch.
     */
    @Test
    public void testEqualsInValid() {
        Merch instance2 = new Merch();
        instance2.setMerchId(100);
        boolean expResult = false;
        boolean result = instance.equals(instance2);
        assertEquals(expResult, result);
    }

    
}
