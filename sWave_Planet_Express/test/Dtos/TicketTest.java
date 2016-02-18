/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dtos;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author d00159732
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
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getTicketId method, of class Ticket.
     */
    @Test
    public void testGetTicketId() {
        
        instance = new Ticket();
        int expResult = 0;
        int result = instance.getTicketId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setTicketId method, of class Ticket.
     */
    @Test
    public void testSetTicketId() {
 
        int ticketId = 1;
        instance = new Ticket();
        instance.setTicketId(ticketId);
        assertEquals(instance.getTicketId(), ticketId);
    }

    /**
     * Test of getUserId method, of class Ticket.
     */
    @Test
    public void testGetUserId() {

        instance = new Ticket();
        int expResult = 0;
        int result = instance.getUserId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setUserId method, of class Ticket.
     */
    @Test
    public void testSetUserId() {
        
        int userId = 1;
        instance = new Ticket();
        instance.setUserId(userId);
        assertEquals(instance.getUserId(),userId);
    }

    /**
     * Test of getIssue method, of class Ticket.
     */
    @Test
    public void testGetIssue() {
       
        instance = new Ticket();
        String expResult = "Insert issue";
        String result = instance.getIssue();
        assertEquals(expResult, result);
    }

    /**
     * Test of setIssue method, of class Ticket.
     */
    @Test
    public void testSetIssue() {
        
        String issue = "new issue";
        instance = new Ticket();
        instance.setIssue(issue);
        assertEquals(instance.getIssue(),issue);
    }

    /**
     * Test of getDateRaised method, of class Ticket.
     */
    @Test
    public void testGetDateRaised() {
        
        instance = new Ticket();
        String expResult = "Insert date";
        String result = instance.getDateRaised();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDateRaised method, of class Ticket.
     */
    @Test
    public void testSetDateRaised() {
        
        String dateRaised = "New date";
        Ticket instance = new Ticket();
        instance.setDateRaised(dateRaised);
        assertEquals(instance.getDateRaised(),dateRaised);
    }

    /**
     * Test of isResolved method, of class Ticket.
     */
    @Test
    public void testIsResolved() {
        
        instance = new Ticket();
        boolean expResult = false;
        boolean result = instance.isResolved();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setResolved method, of class Ticket.
     */
    @Test
    public void testSetResolved() {
        
        boolean resolved = true;
        instance = new Ticket();
        instance.setResolved(resolved);
        assertEquals(instance.isResolved(),resolved);
    }

    /**
     * Test of toString method, of class Ticket.
     */
    @Test
    public void testToString() {
        
        Ticket instance = new Ticket();
        String expResult = "Ticket{ticketId=0, userId=0, issue=Insert issue, dateRaised=Insert date, resolved=false}";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}
