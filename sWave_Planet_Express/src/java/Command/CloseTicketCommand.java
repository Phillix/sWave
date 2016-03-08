package Command;

import Daos.TicketDao;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Austin
 */
public class CloseTicketCommand implements Command {

    @Override
    public String executeCommand(HttpServletRequest request, HttpServletResponse response) {
        String forwardToJsp = null;
        TicketDao td        = new TicketDao();
        String ticketId     = request.getParameter("ticketId");

        if (ticketId != null && !ticketId.isEmpty()) {
            td.changeTicketStatus(Integer.valueOf(ticketId), true);
            forwardToJsp = "/account.jsp?view=tickets";
        } else {
            //set to log_in fail as no appropriate page yet
            forwardToJsp = "/error.jsp";
        }
        return forwardToJsp;
    }
}