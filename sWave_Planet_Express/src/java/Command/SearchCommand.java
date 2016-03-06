package Command;

import Daos.SongDao;
import Dtos.Song;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Austin
 */
public class SearchCommand implements Command {
    
    @Override
    public String executeCommand(HttpServletRequest request, HttpServletResponse response) {
        SongDao sd = new SongDao();
        ArrayList<Song> songs = sd.search(request.getParameter("searchterm"));
        HttpSession session = request.getSession();
        session.setAttribute("searchResults", songs);
        session.setAttribute("searchTerm", request.getParameter("searchterm"));
        return "/search.jsp";
    }
}
