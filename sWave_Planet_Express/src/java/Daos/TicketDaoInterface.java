package Daos;

import Dtos.Ticket;
import java.util.ArrayList;

/**
 *
 * @author Phillix
 */
public interface TicketDaoInterface {
    public int createTicket(Ticket t);
    public ArrayList<Ticket> getCurrTickets();
}
