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
public class TicketTest {
    
    private Ticket instance;
    public TicketTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        instance = new Ticket();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getTicketId method being valid, of class Ticket.
     */
    @Test
    public void testGetTicketIdValid() {
        int expResult = 0;
        int result = instance.getTicketId();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getTicketId method being invalid, of class Ticket.
     */
    @Test
    public void testGetTicketIdInvalid() {
        boolean expResult = false;
        boolean result = instance.getTicketId() == 1;
        assertEquals(expResult, result);
    }

    /**
     * Test of setTicketId method being valid, of class Ticket.
     */
    @Test
    public void testSetTicketIdValid() {
        int ticketId = 1;
        instance.setTicketId(ticketId);
        assertEquals(instance.getTicketId(), ticketId);
    }
    
    /**
     * Test of setTicketId method being invalid, of class Ticket.
     */
    @Test
    public void testSetTicketIdInvalid() {
        boolean expResult = false;
        instance.setTicketId(1);
        boolean result = instance.getTicketId() == 0;
        assertEquals(expResult, result);
    }

    /**
     * Test of getUserId method being valid, of class Ticket.
     */
    @Test
    public void testGetUserIdValid() {
        int expResult = 0;
        int result = instance.getUserId();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getUserId method being invalid, of class Ticket.
     */
    @Test
    public void testGetUserIdInvalid() {
        boolean expResult = false;
        boolean result = instance.getUserId() == 1;
        assertEquals(expResult, result);
    }

    /**
     * Test of setUserId method being valid, of class Ticket.
     */
    @Test
    public void testSetUserIdValid() {
        int userId = 1;
        instance.setUserId(userId);
        assertEquals(instance.getUserId(),userId);
    }
    
    /**
     * Test of setUserId method being invalid, of class Ticket.
     */
    @Test
    public void testSetUserIdInvalid() {
        boolean expResult = false;
        instance.setUserId(1);
        boolean result = instance.getUserId() == 0;
        assertEquals(expResult, result);
    }

    /**
     * Test of getIssue method being valid, of class Ticket.
     */
    @Test
    public void testGetIssueValid() {
        String expResult = "Insert issue";
        String result = instance.getIssue();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getIssue method being invalid, of class Ticket.
     */
    @Test
    public void testGetIssueInvalid() {
        boolean expResult = false;
        boolean result = instance.getIssue().equals("Insert issues");
        assertEquals(expResult, result);
    }

    /**
     * Test of setIssue method being valid, of class Ticket.
     */
    @Test
    public void testSetIssueValid() {
        String issue = "new issue";
        instance.setIssue(issue);
        assertEquals(instance.getIssue(),issue);
    }
    
    /**
     * Test of setIssue method being invalid, of class Ticket.
     */
    @Test
    public void testSetIssueInvalid() {
        boolean expResult = false;
        instance.setIssue("new issues");
        boolean result = instance.getIssue().equals("new issue");
        assertEquals(expResult, result);
    }

    /**
     * Test of getDateRaised method being valid, of class Ticket.
     */
    @Test
    public void testGetDateRaisedValid() {
        String expResult = "Insert date";
        String result = instance.getDateRaised();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getDateRaised method being invalid, of class Ticket.
     */
    @Test
    public void testGetDateRaisedInvalid() {
        boolean expResult = false;
        boolean result = instance.getDateRaised().equals("Insert dates");
        assertEquals(expResult, result);
    }

    /**
     * Test of setDateRaised method being valid, of class Ticket.
     */
    @Test
    public void testSetDateRaisedValid() {
        String dateRaised = "New date";
        instance.setDateRaised(dateRaised);
        assertEquals(instance.getDateRaised(),dateRaised);
    }
    
    /**
     * Test of setDateRaised method being invalid, of class Ticket.
     */
    @Test
    public void testSetDateRaisedInvalid() {
        boolean expResult = false;
        instance.setDateRaised("New dates");
        boolean result = instance.getDateRaised().equals("New date");
        assertEquals(expResult, result);
    }

    /**
     * Test of isResolved method being valid, of class Ticket.
     */
    @Test
    public void testIsResolvedValid() {
        boolean expResult = false;
        boolean result = instance.isResolved();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of isResolved method being invalid, of class Ticket.
     */
    @Test
    public void testIsResolvedInvalid() {
        boolean expResult = false;
        boolean result = instance.isResolved() == true;
        assertEquals(expResult, result);
    }

    /**
     * Test of setResolved method being valid, of class Ticket.
     */
    @Test
    public void testSetResolvedValid() {
        boolean resolved = true;
        instance.setResolved(resolved);
        assertEquals(instance.isResolved(),resolved);
    }
    
    /**
     * Test of setResolved method being invalid, of class Ticket.
     */
    @Test
    public void testSetResolvedInvalid() {
        boolean expResult = false;
        instance.setResolved(true);
        boolean result = instance.isResolved() == false;
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method being valid, of class Ticket.
     */
    @Test
    public void testToStringValid() {
        String expResult = "Ticket{ticketId=0, userId=0, issue=Insert issue, dateRaised=Insert date, resolved=false}";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of toString method being invalid, of class Ticket.
     */
    @Test
    public void testToStringInvalid() {
        boolean expResult = false;
        boolean result = instance.toString().equals("Ticket{ticketid=0, userId=1, issue=Insert isse, dateRaised=Insert Date, resolved=false}");
        assertEquals(expResult, result);
    }
    
}
