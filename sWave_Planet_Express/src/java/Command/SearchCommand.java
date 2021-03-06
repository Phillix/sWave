package Command;

import Daos.MerchDao;
import Daos.SongDao;
import Daos.UsersDao;
import Dtos.Merch;
import Dtos.Song;
import Dtos.User;
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
        SongDao sd    = new SongDao();
        MerchDao md   = new MerchDao();
        UsersDao ud   = new UsersDao();
        String search = request.getParameter("searchterm");
        if(search != null && !search.isEmpty()) {
            ArrayList<Song>  songs = sd.search(search);
            ArrayList<Merch> merch = md.searchMerch(search);
            ArrayList<User>  users = ud.searchUsers(search);
            if (!(songs.size() == 0 && merch.size() == 0 && users.size() == 0)) {
                HttpSession session = request.getSession();
                session.setAttribute("searchResults", songs);
                session.setAttribute("searchMerchResults", merch);
                session.setAttribute("searchUserResults", users);
                session.setAttribute("searchTerm", search); 
                return "/search.jsp";
            }
        }

        return "/search.jsp?noResults=yes";
    }
}
