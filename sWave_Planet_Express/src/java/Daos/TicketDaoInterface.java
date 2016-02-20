/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
