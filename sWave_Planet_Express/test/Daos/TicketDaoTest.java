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
    
    TicketDao instance;
    
    public TicketDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
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
        instance = new TicketDao();
        int expResult = 0;
        int result = instance.createTicket(t);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getCurrTickets method, of class TicketDao.
     */
    @Test
    public void testGetCurrTickets() {
        
        instance = new TicketDao();
        ArrayList<Ticket> result = instance.getCurrTickets();
        assertNotNull(result);
    }
}
