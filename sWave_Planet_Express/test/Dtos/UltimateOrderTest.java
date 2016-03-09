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
public class UltimateOrderTest {
    
    UltimateOrder instance;
    public UltimateOrderTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        instance = new UltimateOrder();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getMerchSize method being valid, of class UltimateOrder.
     */
    @Test
    public void testGetMerchSizeValid() {
        int expResult = 0;
        int result = instance.getMerchSize();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getMerchSize method being invalid, of class UltimateOrder.
     */
    @Test
    public void testGetMerchSizeInvalid() {
        boolean expResult = false;
        boolean result = instance.getMerchSize() == -1;
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getMerchSize method being valid, of class UltimateOrder.
     */
    @Test
    public void testGetSongSizeValid() {
        int expResult = 0;
        int result = instance.getSongSize();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getMerchSize method being invalid, of class UltimateOrder.
     */
    @Test
    public void testGetSongSizeInvalid() {
        boolean expResult = false;
        boolean result = instance.getSongSize() == 1;
        assertEquals(expResult, result);
    }

    /**
     * Test of getTotal method being valid, of class UltimateOrder.
     */
    @Test
    public void testGetTotalValid() {
        double expResult = 50.0;
        double result = instance.getTotal();
        assertEquals(expResult, result, 0.0);
    }
    
    /**
     * Test of getTotal method being invalid, of class UltimateOrder.
     */
    @Test
    public void testGetTotalInvalid() {
        boolean expResult = false;
        boolean result = instance.getTotal() == 50.1;
        assertEquals(expResult, result);
    }

    /**
     * Test of setTotal method being valid, of class UltimateOrder.
     */
    @Test
    public void testSetTotalValid() {
        double total = 20.0;
        instance.setTotal(total);
        assertEquals(total,instance.getTotal(),.01);
    }
    
    /**
     * Test of setTotal method being invalid, of class UltimateOrder.
     */
    @Test
    public void testSetTotalInvalid() {
        boolean expResult = false;
        instance.setTotal(20.5);
        boolean result = instance.getTotal() == 50.0;
        assertEquals(expResult, result);
    }

    /**
     * Test of getDateOrdered method being valid, of class UltimateOrder.
     */
    @Test
    public void testGetDateOrderedValid() {
        String expResult = "date";
        String result = instance.getDateOrdered();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getDateOrdered method being invalid, of class UltimateOrder.
     */
    @Test
    public void testGetDateOrderedInvalid() {
        boolean expResult = false;
        boolean result = instance.getDateOrdered().equals("dates");
        assertEquals(expResult, result);
    }

    /**
     * Test of setDateOrdered method being valid, of class UltimateOrder.
     */
    @Test
    public void testSetDateOrderedValid() {
        String dateOrdered = "new date";
        instance.setDateOrdered(dateOrdered);
        assertEquals(dateOrdered, instance.getDateOrdered());
    }
    
    /**
     * Test of setDateOrdered method being invalid, of class UltimateOrder.
     */
    @Test
    public void testSetDateOrderedInvalid() {
        boolean expResult = false;
        instance.setDateOrdered("new date");
        boolean result = instance.getDateOrdered().equals("date");
        assertEquals(expResult, result);
    }

    /**
     * Test of getQty method being valid, of class UltimateOrder.
     */
    @Test
    public void testGetQtyValid() {
        int expResult = 0;
        int result = instance.getQty(0);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getQty method being invalid, of class UltimateOrder.
     */
    @Test
    public void testGetQtyInvalid() {
        boolean expResult = false;
        boolean result = instance.getQty(0) == 1;
        assertEquals(expResult, result);
    }

    /**
     * Test of setQty method being valid, of class UltimateOrder.
     */
    @Test
    public void testSetQtyValid() {
        int[] qty = new int[]{-1};
        instance.setQty(qty);
        assertEquals(qty[0],instance.getSongId(0));
    }
    
    /**
     * Test of setQty method being invalid, of class UltimateOrder.
     */
    @Test
    public void testSetQtyInvalid() {
        boolean expResult = false;
        instance.setQty(new int[]{-1});
        boolean result = instance.getSongId(0) == 0;
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getSongId method being valid, of class UltimateOrder.
     */
    @Test
    public void testGetSongIdValid() {
        int expResult = -1;
        int result = instance.getSongId(0);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getSongId method being invalid, of class UltimateOrder.
     */
    @Test
    public void testGetSongIdInvalid() {
        boolean expResult = false;
        boolean result = instance.getSongId(0) == 0;
        assertEquals(expResult, result);
    }

    /**
     * Test of setSongId method being valid, of class UltimateOrder.
     */
    @Test
    public void testSetSongIdValid() {
        int[] id = new int[]{-7};
        instance.setSongId(id);
        assertEquals(id[0],instance.getSongId(0));
    }
    
    /**
     * Test of setSongId method being invalid, of class UltimateOrder.
     */
    @Test
    public void testSetSongIdInvalid() {
        boolean expResult = false;
        instance.setSongId(new int[]{-7});
        boolean result = instance.getSongId(0) == -1;
        assertEquals(expResult, result);
    }

    /**
     * Test of getMerchPrice method being valid, of class UltimateOrder.
     */
    @Test
    public void testGetMerchPriceValid() {
        int i = 0;
        double expResult = 20.0;
        double result = instance.getMerchPrice(i);
        assertEquals(expResult, result, 0.0);
    }
    
    /**
     * Test of getMerchPrice method being invalid, of class UltimateOrder.
     */
    @Test
    public void testGetMerchPriceInvalid() {
        boolean expResult = false;
        boolean result = instance.getMerchPrice(0) == 20.01;
        assertEquals(expResult, result);
    }

    /**
     * Test of setMerchPrice method being valid, of class UltimateOrder.
     */
    @Test
    public void testSetMerchPriceValid() {
        double[] price = new double[]{9.99};
        instance.setMerchPrice(price);
        assertEquals(price[0],instance.getMerchPrice(0),0.01);
    }
    
    /**
     * Test of setMerchPrice method being invalid, of class UltimateOrder.
     */
    @Test
    public void testSetMerchPriceInvalid() {
        boolean expResult = false;
        instance.setMerchPrice(new double[]{9.99});
        boolean result = instance.getMerchPrice(0) == 20.0;
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getSongPrice method being valid, of class UltimateOrder.
     */
    @Test
    public void testGetSongPriceValid() {
        int i = 0;
        double expResult = 15.50;
        double result = instance.getSongPrice(i);
        assertEquals(expResult, result, 0.0);
    }
    
    /**
     * Test of getSongPrice method being invalid, of class UltimateOrder.
     */
    @Test
    public void testGetSongPriceInvalid() {
        boolean expResult = false;
        boolean result = instance.getSongPrice(0) == 15.501;
        assertEquals(expResult, result);
    }

    /**
     * Test of setSongPrice method being valid, of class UltimateOrder.
     */
    @Test
    public void testSetSongPriceValid() {
        double[] price = new double[]{7.99};
        instance.setSongPrice(price);
        assertEquals(price[0],instance.getSongPrice(0),0.01);
    }
    
    /**
     * Test of setSongPrice method being invalid, of class UltimateOrder.
     */
    @Test
    public void testSetSongPriceInvalid() {
        boolean expResult = false;
        instance.setSongPrice(new double[]{7.99});
        boolean result = instance.getSongPrice(0) == 15.50;
        assertEquals(expResult, result);
    }

    /**
     * Test of getTitle method being valid, of class UltimateOrder.
     */
    @Test
    public void testGetTitleValid() {
        int i = 0;
        String expResult = "testcase";
        String result = instance.getTitle(i);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getTitle method being invalid, of class UltimateOrder.
     */
    @Test
    public void testGetTitleInvalid() {
        boolean expResult = false;
        boolean result = instance.getTitle(0).equals("testcases");
        assertEquals(expResult, result);
    }

    /**
     * Test of setTitle method being valid, of class UltimateOrder.
     */
    @Test
    public void testSetTitleValid() {
        String[] title = new String[]{"newtitle"};
        instance.setTitle(title);
        assertEquals(title[0],instance.getTitle(0));
    }
    
    /**
     * Test of setTitle method being invalid, of class UltimateOrder.
     */
    @Test
    public void testSetTitleInvalid() {
        boolean expResult = false;
        instance.setTitle(new String[]{"newtitle"});
        boolean result = instance.getTitle(0).equals("testcase");
        assertEquals(expResult, result);
    }

   
}
