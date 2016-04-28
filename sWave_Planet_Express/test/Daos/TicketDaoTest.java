package Daos;

import Dtos.Ticket;
import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * This test class tests the methods of the TicketDao class
 * @author Phillix
 * @author Austin
 */

public class TicketDaoTest {
    static MyDataSource ds = new MyDataSource();
    static TicketDao instance;
    static Ticket t;
    static int res;

    @BeforeClass
    public static void setUpClass() {
        instance = new TicketDao(ds);
    }

    @Before
    public void setUp() {
        t = new Ticket(-1,"test 1",false);
        instance.createTicket(t);
        t = instance.getCurrTickets().stream().filter(tick -> tick.getIssue().equals(t.getIssue())).findFirst().get();
    }

    @After
    public void tearDown() {
        instance.deleteTicket(t.getTicketId());
    }

    /**
     * Test of createTicket method being valid, of class TicketDao.
     */
    @Test
    public void testCreateTicketValid() {
        int expResult = 0;
        assertEquals(expResult, res);
    }

    /**
     * Test of getCurrTickets method being valid, of class TicketDao.
     */
    @Test
    public void testGetCurrTicketsValid() {
        ArrayList<Ticket> result = instance.getCurrTickets();
        assertNotNull(result);
    }
    
    /**
     * Test of getCurrTickets method being invalid, of class TicketDao.
     */
    @Test
    public void testGetCurrTicketsInvalid() {
        instance.changeTicketStatus(-1, true);
        instance.changeTicketStatus(t.getTicketId(), true);
        boolean result = instance.getCurrTickets().isEmpty();
        assertEquals(true, result);
    }

    /**
     * Testing the view ticket method being valid
     */
    @Test
    public void testViewTicketValid() {
        Ticket result = instance.viewTicket(t.getTicketId());
        assertEquals("The two tickets do not equal each other in testViewTicketValid", t, result);
    }
    
    /**
     * Testing the view ticket method being invalid
     */
    @Test
    public void testViewTicketInvalid() {
        Ticket result = instance.viewTicket(t.getTicketId());
        t.setIssue("oh is fixed");
        assertNotEquals("The two tickets do equal each other in testViewTicketInvalid", t, result);
    }

    /**
     * Testing the closing ticket method being valid
     */
    @Test
    public void testchangeTicketStatusValid() {
        instance.changeTicketStatus(t.getTicketId(), true);
        t = instance.viewTicket(t.getTicketId());
        boolean expResult = true;
        boolean result = t.isResolved();
        assertEquals("The close ticket method does not work correctly in testCloseTicket", expResult, result);
    }
    
    /**
     * Testing the closing ticket method being invalid
     */
    @Test
    public void testchangeTicketStatus() {
        instance.changeTicketStatus(t.getTicketId(), false);
        boolean result = instance.viewTicket(t.getTicketId()).isResolved();
        boolean expResult = false;
        assertEquals("The close ticket method does not work correctly in testCloseTicket", expResult, result);
    }

    /**
     * Test of getAllTickets method being valid, of class TicketDao.
     */
    @Test
    public void testGetAllTicketsValid() {
        boolean expResult = true;
        ArrayList<Ticket> tickets = instance.getAllTickets();
        boolean result = tickets.size() > 0;
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getAllTickets method being invalid, of class TicketDao.
     */
    @Test
    public void testGetAllTicketsInvalid() {
        boolean expResult = false;
        ArrayList<Ticket> tickets = instance.getAllTickets();
        boolean result = tickets.size() == 0;
        assertEquals(expResult, result);
    }
}
