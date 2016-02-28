package Command;


import Daos.TicketDao;
import Dtos.Ticket;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Phillix
 */
public class CreateTicketCommand implements Command {

    @Override
    public String executeCommand(HttpServletRequest request, HttpServletResponse response) {
        
        String forwardToJsp = null;
        TicketDao td = new TicketDao();
        String userId = request.getParameter("userId");
        String issue = request.getParameter("issue");
        
        if (userId != null && issue != null && !userId.isEmpty() && !issue.isEmpty()) {
            
            Ticket ticket = new Ticket(Integer.valueOf(userId), issue, false);
            td.createTicket(ticket);

            forwardToJsp = "/index.jsp";				
            
        } else {
            //set to log_in fail as no appropriate page yet
            forwardToJsp = "/login_failed.jsp";
        }
        return forwardToJsp;
    }
    
}
