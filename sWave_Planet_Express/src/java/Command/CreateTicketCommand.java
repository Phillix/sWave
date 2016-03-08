package Command;


import Daos.TicketDao;
import Dtos.Ticket;
import Dtos.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Phillix
 */
public class CreateTicketCommand implements Command {

    @Override
    public String executeCommand(HttpServletRequest request, HttpServletResponse response) {

        String forwardToJsp = null;
        TicketDao td        = new TicketDao();
        HttpSession session    = request.getSession();
        User u              = (User) session.getAttribute("user");
        String issue        = request.getParameter("issue");

        if (u != null && issue != null && !issue.isEmpty()) {
            int userId       = u.getUserId();
            Ticket ticket = new Ticket(userId, issue, false);
            td.createTicket(ticket);

            forwardToJsp = "/index.jsp";
        } else {
            //set to log_in fail as no appropriate page yet
            forwardToJsp = "/login_failed.jsp";
        }
        return forwardToJsp;
    }
}
