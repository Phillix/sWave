package Daos;

import Dtos.Ticket;
import java.util.ArrayList;

/**
 *
 * @author Phillix
 * @author Austin
 */
public interface TicketDaoInterface {
    public int createTicket(Ticket t);
    public ArrayList<Ticket> getCurrTickets();
    public ArrayList<Ticket> getAllTickets();
    public Ticket viewTicket(int ticketId);
    public int changeTicketStatus(int ticketId, boolean isResolved);
    public int deleteTicket(int ticketId);
}
