package Command;

import Daos.UsersDao;
import Dtos.User;
import Security.UserSecurity;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author austin
 */
public class RegisterCommand implements Command {
    
    /**
     * The command used for registering users
     * @param request The request from the servlet
     * @param response The response from the servlet
     * @return The page to forward the user to after registering
     */
    @Override
    public String executeCommand(HttpServletRequest request, HttpServletResponse response) {
        String forwardToJsp = null;
        UsersDao ud = new UsersDao();
        
        String fname      = request.getParameter("fname");
        String lname      = request.getParameter("lname");       
        String username   = request.getParameter("username");
        String email      = request.getParameter("email");
        String password   = request.getParameter("password");
        String add1       = request.getParameter("address1");
        String add2       = request.getParameter("address2");
        String city       = request.getParameter("city");
        String county     = request.getParameter("county");
        String skin       = request.getParameter("skin");
        boolean isAdmin   = false;
        UserSecurity ms = new UserSecurity();
        
        //In case the textboxes were empty, should be checked by js but this is an extra check, check details returns -5 for other meaning it is ok to use email and username
        if (email != null && !email.isEmpty() && username != null && !username.isEmpty() && ud.checkDetails(email, username) == -5 && password != null && !password.isEmpty() && fname != null && !fname.isEmpty() && lname != null && !lname.isEmpty()) {
            //Make the user registering
            password = ms.hash(password.toCharArray());
            User userRegistering = new User(email, password, username, fname, lname, add1, add2, city, county, skin, false);
            int check = ud.register(userRegistering);
            
            //If registering was successful log the user in
            if (check == 0) {
                //Store the session id for this client...
                HttpSession session = request.getSession();
                String clientSessionId = session.getId();
                session.setAttribute("loggedSessionId", clientSessionId);
                
                //Store the user in the session
                session.setAttribute("user", userRegistering);
                
                //Forward them to the home page
                forwardToJsp = "/index.jsp";				
            } else {
                forwardToJsp = "/index.jsp?regFail=yes";	
            }
        } else {
            forwardToJsp = "/index.jsp?regFail=yes";
        }
        return forwardToJsp;
    }
}
