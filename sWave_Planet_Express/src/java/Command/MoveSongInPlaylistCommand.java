package Command;

import Daos.PlayTracksDao;
import Dtos.PlayTrack;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Brian Millar
 */
public class MoveSongInPlaylistCommand implements Command {

    @Override
    public String executeCommand(HttpServletRequest request, HttpServletResponse response) {
        PlayTracksDao pdao = new PlayTracksDao();
        try {
            PlayTrack pt   = new PlayTrack(Integer.parseInt(request.getParameter("songid")),
                                           Integer.parseInt(request.getParameter("playlistid")), 
                                           0);
            if (request.getParameter("direction") != null && request.getParameter("direction").equals("up"))
                pdao.moveOrderUp(pt);
            else if (request.getParameter("direction") != null && request.getParameter("direction").equals("down"))
                pdao.moveOrderDown(pt);
            return "/playlist.jsp?playlist=" + Integer.parseInt(request.getParameter("playlistid"));
        } catch (NumberFormatException e) {
            return "/error.jsp?msg=The%20song%20was%20not%20deleted%20from%20the%20playlist%20successfully.";
        }
    }
    
}
