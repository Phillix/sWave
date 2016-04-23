package Command;

import Daos.PlaylistDao;
import Dtos.Playlist;
import Dtos.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Austin
 */
public class CreatePlaylistCommand implements Command {

    @Override
    public String executeCommand(HttpServletRequest request, HttpServletResponse response) {
        PlaylistDao pd = new PlaylistDao();
        HttpSession session = request.getSession();
        String title = request.getParameter("playlistTitle");
        User u = (User)(session.getAttribute("user"));
        
        if (title != null && !title.isEmpty() && u != null) {
            Playlist p = new Playlist(u.getUserId(), title);
            pd.createPlayList(p);
            return "/playlists.jsp";
        }
        return "/error.jsp?msg=Playlist%20could%20not%20be%20created";
    }
    
}
