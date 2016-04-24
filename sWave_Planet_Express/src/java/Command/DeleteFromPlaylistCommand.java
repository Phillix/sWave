package Command;

import Daos.PlayTracksDao;
import Dtos.PlayTrack;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Austin
 */
public class DeleteFromPlaylistCommand implements Command {

    @Override
    public String executeCommand(HttpServletRequest request, HttpServletResponse response) {
        PlayTracksDao ptf = new PlayTracksDao();
        String song       = request.getParameter("songId");
        String playlist   = request.getParameter("playlistId");
        
        if (song != null && !song.isEmpty() && playlist != null && !playlist.isEmpty()) {
            int songId     = Integer.parseInt(song);
            int playlistId = Integer.parseInt(playlist);
            PlayTrack pt   = new PlayTrack(songId, playlistId, 0);
            int result     = ptf.deletePlayTrack(pt);
            
            if (result == 0) return "/playlist.jsp?playlist=" + playlist;
        }
        return "/error.jsp?msg=The%20song%20was%20not%20deleted%20from%20the%20playlist%20successfully.";
    }
    
}
