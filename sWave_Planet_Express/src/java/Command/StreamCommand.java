package Command;

import Daos.SongDao;
import Dtos.Song;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Brian Millar
 */
public class StreamCommand implements Command {

    private static final boolean DEBUG = Debugging.Debug.debug;

    @Override
    public String executeCommand(HttpServletRequest request, HttpServletResponse response) {
        String filename = request.getParameter("songid") + ".mp3";
        File testfile = new File(filename);
        if (!testfile.exists()) {
            //SongLocking.clean(); //Cleanup unused files before opening another
            SongDao songs = new SongDao();
            byte songdata[] = (((Song)songs.getSongById(Integer.parseInt(request.getParameter("songid")))).getSongdata());
            try {
                try (FileOutputStream output = new FileOutputStream(new File("../webapps/ROOT/" + filename))) {
                    output.write(songdata);
                }
            } catch (IOException ex) {
                if (DEBUG)
                    ex.printStackTrace();
                return "/error.jsp";
            }
        }
        //SongLocking.setLock(Integer.parseInt(request.getParameter("songid")), SongLocking.getLocks(Integer.parseInt(request.getParameter("songid"))));
        System.out.println(request.getRequestURL());
        return "/" + request.getParameter("page") + ".jsp?filename=" + filename + "&playid=" + request.getParameter("songid");
    }
}