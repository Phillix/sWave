package Command;

import Daos.SongDao;
import Dtos.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Austin
 */
public class DeleteSongCommand implements Command {

    @Override
    public String executeCommand(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        User u = (User)session.getAttribute("user");
        SongDao sd = new SongDao();
        
        try {
            int songId = Integer.valueOf(request.getParameter("songid"));
            int result;
            if (u != null && u.isIsAdmin()) {
                result = sd.deleteSong(songId);
                if (result > 0) {
                    return "/admin_panel.jsp";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/error.jsp";
    }
}
