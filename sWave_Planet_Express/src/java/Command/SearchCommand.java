package Command;

import Daos.MerchDao;
import Daos.SongDao;
import Dtos.Merch;
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
        SongDao sd    = new SongDao();
        MerchDao md   = new MerchDao();
        String search = request.getParameter("searchterm");
        ArrayList<Song> songs  = sd.search(search);
        ArrayList<Merch> merch = md.searchMerch(search);
        HttpSession session    = request.getSession();
        session.setAttribute("searchResults", songs);
        session.setAttribute("searchMerchResults", merch);
        session.setAttribute("searchTerm", search);
        return "/search.jsp";
    }
}
