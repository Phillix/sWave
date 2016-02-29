package Daos;

import Dtos.Ticket;
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

public class TicketDaoTest {
    
    static TicketDao instance;
    
    public TicketDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        instance = new TicketDao();
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of createTicket method, of class TicketDao.
     */
    @Test
    public void testCreateTicket() {
        Ticket t = new Ticket(-1,"test 1",false);
        int expResult = 0;
        int result = instance.createTicket(t);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getCurrTickets method, of class TicketDao.
     */
    @Test
    public void testGetCurrTickets() {
        ArrayList<Ticket> result = instance.getCurrTickets();
        assertNotNull(result);
    }
    
    /**
     * Testing the view ticket method
     */
    @Test
    public void testViewTicket() {
        Ticket expResult = new Ticket(-1, "my pc is on fire", false);
        expResult.setDateRaised("2009-12-03");
        expResult.setTicketId(-1);
        Ticket result = instance.viewTicket(-1);
        assertEquals("The two tickets do not equal each other in testViewTicket", expResult, result);
    }
    
    /**
     * Testing the closing ticket method
     */
    @Test
    public void testCloseTicket() {
        instance.changeTicketStatus(-1, true);
        Ticket t = instance.viewTicket(-1);
        boolean expResult = true;
        boolean result = t.isResolved();
        assertEquals("The close ticket method does not work correctly in testCloseTicket", expResult, result);
        instance.changeTicketStatus(-1, false);
    }
}
