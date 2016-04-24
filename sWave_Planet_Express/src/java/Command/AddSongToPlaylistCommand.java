package Command;

import Daos.PlaylistDao;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Brian Millar
 */
public class AddSongToPlaylistCommand implements Command {
    @Override
    public String executeCommand(HttpServletRequest request, HttpServletResponse response) {
        PlaylistDao playDao = new PlaylistDao();
        try {
            int s = Integer.parseInt(request.getParameter("songId"));
            int p = Integer.parseInt(request.getParameter("playlistId"));
            playDao.addSongToPlaylist(s, p);
        } catch (NumberFormatException e) {
            return "/error.jsp?msg=Failed%20to%20Add%20Song%20to%20Playlist";
        }
        return "/music.jsp";
    }
}
