package Daos;

import Dtos.Ticket;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;

/**
 * This class is used for communicating with the Tickets table in the database
 * @author Phillix
 * @author Austin
 */
public class TicketDao extends Dao implements TicketDaoInterface {
    private final boolean DEBUG = sWave.Server.DEBUGGING;

    private final String TABLE_NAME = "TICKETS";
    private final String ID         = "TICKETID";
    private final String USERID     = "USERID";
    private final String ISSUE      = "ISSUE";
    private final String DATE       = "DATERAISED";
    private final String RESOLVED   = "RESOLVED";
    
    /**
     * Default Constructor for TicketDao
     */
    public TicketDao() {
        super();
    }
    
    /**
     * Parameterized Constructor for TicketDao
     * @param ds The DataSource to use for connections
     */
    public TicketDao(DataSource ds) {
        super(ds);
    }
    
    /**
     * Creating a ticket for the database
     * @param t the Ticket to create
     * @return int to indicate result (see Dao class)
     */
    @Override
    public int createTicket(Ticket t) {
        Connection con       = null;
        PreparedStatement ps = null;
        try {
            con = getConnection();
            ps  = con.prepareStatement("INSERT INTO " + TABLE_NAME + " VALUES(?,?,?,?,?)");
            //create date object and get current time
            java.util.Date utilDate = new java.util.Date();
            //pass into sql date object
            Date current = new Date(utilDate.getTime());

            ps.setInt(1, 0);
            ps.setInt(2, t.getUserId());
            ps.setString(3, t.getIssue());
            ps.setDate(4, current);
            ps.setBoolean(5, t.isResolved());

            ps.executeUpdate();
            return SUCCESS;
        }
        catch(SQLException e) {
            if(DEBUG)
                e.printStackTrace();
            return SQLEX;
        }
        finally {
            try {
                if(ps  != null)
                    ps.close();
                if(con != null)
                    freeConnection(con);
            }
            catch(SQLException e) {
                if (DEBUG)
                    e.printStackTrace();
                return CONNCLOSEFAIL;
            }
        }
    }

    /**
     * Getting all open tickets
     * @return arrayList of all currently unresolved tickets 
     */
    @Override
    public ArrayList<Ticket> getCurrTickets() {
        Connection con            = null;
        PreparedStatement ps      = null;
        ResultSet rs              = null;
        ArrayList<Ticket> tickets = null;
        Ticket t                  = null;

        try {
            con     = getConnection();
            ps      = con.prepareStatement("SELECT * FROM " + TABLE_NAME +
                                           " WHERE " + RESOLVED + 
                                           " = FALSE ORDER BY " + DATE + 
                                           " DESC");
            rs      = ps.executeQuery();
            tickets = new ArrayList<>();

            while(rs.next()) {

                t = new Ticket();
                t.setTicketId(rs.getInt(ID));
                t.setUserId(rs.getInt(USERID));
                t.setIssue(rs.getString(ISSUE));
                t.setDateRaised(rs.getDate(DATE).toString());
                t.setResolved(rs.getBoolean(RESOLVED));

                tickets.add(t);
            }
        }
        catch(Exception e) {
            if(DEBUG)
                e.printStackTrace();
            return null;
        }
        finally {
            try {
                if(rs  != null)
                    rs.close();
                if(ps  != null)
                    ps.close();
                if(con != null)
                    freeConnection(con);
            }
            catch(SQLException e) {
                if(DEBUG)
                    e.printStackTrace();
               return null;
            }
        }
        return tickets;
    }
    
    /**
     * Getting all tickets regardless of open or closed
     * @return arraylist of all tickets
     */
    @Override
    public ArrayList<Ticket> getAllTickets() {
        Connection con            = null;
        PreparedStatement ps      = null;
        ResultSet rs              = null;
        ArrayList<Ticket> tickets = null;
        Ticket t                  = null;

        try {
            con     = getConnection();
            ps      = con.prepareStatement("SELECT * FROM " + TABLE_NAME + " ORDER BY " + DATE + " DESC");
            rs      = ps.executeQuery();
            tickets = new ArrayList<>();

            while(rs.next()) {

                t = new Ticket();
                t.setTicketId(rs.getInt(ID));
                t.setUserId(rs.getInt(USERID));
                t.setIssue(rs.getString(ISSUE));
                t.setDateRaised(rs.getDate(DATE).toString());
                t.setResolved(rs.getBoolean(RESOLVED));

                tickets.add(t);
            }
        }
        catch(Exception e) {
            if(DEBUG)
                e.printStackTrace();
            return null;
        }
        finally {
            try {
                if(rs  != null)
                    rs.close();
                if(ps  != null)
                    ps.close();
                if(con != null)
                    freeConnection(con);
            }
            catch(SQLException e) {
                if(DEBUG)
                    e.printStackTrace();
               return null;
            }
        }
        return tickets;
    }
    
    /**
     * This method allows for viewing a specific ticket
     * @param ticketId The id of the ticket to be viewed
     * @return Returns a ticket object based on the ticket id
     */
    @Override
    public Ticket viewTicket(int ticketId) {
        Connection con            = null;
        PreparedStatement ps      = null;
        ResultSet rs              = null;
        Ticket ticket             = null;

        try {
            con = getConnection();
            ps  = con.prepareStatement("SELECT * FROM " + TABLE_NAME +
                                       " WHERE " + ID + " = ?");
            ps.setInt(1, ticketId);
            rs  = ps.executeQuery();

            if (rs.next()) {
                ticket = new Ticket();
                ticket.setTicketId(rs.getInt(ID));
                ticket.setUserId(rs.getInt(USERID));
                ticket.setIssue(rs.getString(ISSUE));
                ticket.setDateRaised(rs.getDate(DATE).toString());
                ticket.setResolved(rs.getBoolean(RESOLVED));
            }
        }
        catch(Exception e) {
            if(DEBUG)
                e.printStackTrace();
            return null;
        }
        finally {
            try {
                if(rs  != null)
                    rs.close();
                if(ps  != null)
                    ps.close();
                if(con != null)
                    freeConnection(con);
            }
            catch(SQLException e) {
                if(DEBUG)
                    e.printStackTrace();
                return null;
            }
        }
        return ticket;
    }
    
    /**
     * This method closes a ticket by ticket id
     * @param ticketId The id of the ticket to be closed
     * @param isResolved If you want to set the ticket to open or closed
     * @return Returns SUCCESS(0) if it successfully closed and 
     */
    @Override
    public int changeTicketStatus(int ticketId, boolean isResolved) {
        Connection con       = null;
        PreparedStatement ps = null;
        try {
            con = getConnection();
            ps  = con.prepareStatement("UPDATE "  +
                                       TABLE_NAME + " SET "       +
                                       RESOLVED   + " = ? WHERE " +
                                       ID         + " = ?");
            ps.setBoolean(1, isResolved);
            ps.setInt(2, ticketId);

            int result = ps.executeUpdate();

            if (result > 0)
                return SUCCESS;
        }
        catch(SQLException e) {
            if(DEBUG)
                e.printStackTrace();
            return SQLEX;
        }
        finally {
            try {
                if(ps  != null)
                    ps.close();
                if(con != null)
                    freeConnection(con);
            }
            catch(SQLException e) {
                if (DEBUG)
                    e.printStackTrace();
                return CONNCLOSEFAIL;
            }
        }
        return OTHER;
    }
    
    /**
     * This method is used for deleting a ticket
     * @param ticketId the id of the ticket you want to delete
     * @return an int representing success, failure or exceptions
     */
    public int deleteTicket(int ticketId) {
        Connection con       = null;
        PreparedStatement ps = null;
        try {
            con = getConnection();
            ps  = con.prepareStatement("DELETE FROM "  +
                                       TABLE_NAME + " WHERE "       +
                                       ID  + " = ?");
            ps.setInt(1, ticketId);

            int result = ps.executeUpdate();

            if (result > 0)
                return SUCCESS;
        }
        catch(SQLException e) {
            if(DEBUG)
                e.printStackTrace();
            return SQLEX;
        }
        finally {
            try {
                if(ps  != null)
                    ps.close();
                if(con != null)
                    freeConnection(con);
            }
            catch(SQLException e) {
                if (DEBUG)
                    e.printStackTrace();
                return CONNCLOSEFAIL;
            }
        }
        return OTHER;
    }
}
