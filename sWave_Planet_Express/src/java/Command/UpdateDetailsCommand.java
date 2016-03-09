package Command;

import Daos.UsersDao;
import Dtos.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Austin
 */
public class UpdateDetailsCommand implements Command {

    @Override
    public String executeCommand(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        User u              = (User)session.getAttribute("user");
        UsersDao ud         = new UsersDao();
        
        if (u != null) {
            String email    = request.getParameter("email");
            String username = request.getParameter("username");
            String fname    = request.getParameter("fname");
            String lname    = request.getParameter("lname");
            String add1     = request.getParameter("add1");
            String add2     = request.getParameter("add2");
            String city     = request.getParameter("city");
            String county   = request.getParameter("county");
            String skin     = request.getParameter("skin");
            
            if (email != null && !email.isEmpty()) {
                u.setEmail(email);
            }

            if (username != null && !username.isEmpty()) {
                u.setUsername(username);
            }
            
            if (fname != null && !fname.isEmpty()) {
                u.setFname(fname);
            }
            
            if (lname != null && !lname.isEmpty()) {
                u.setLname(lname);
            }
            
            if (add1 != null && !add1.isEmpty() && add2 != null && !add2.isEmpty() && city != null && !city.isEmpty() && county != null && !county.isEmpty()) {
                u.setAdd1(add1);
                u.setAdd2(add2);
                u.setCity(city);
                u.setCounty(county);
            }
            
            if (skin != null && !skin.isEmpty()) {
                u.setSkin(skin);
            }
            int result = ud.updateUser(u);
            if (result == 0) return "/account.jsp";
        }
        
        return "/error.jsp?msg=An%20error%20occured%20updating%20details";
    }
    
}
