package Command;

import Daos.SongDao;
import Dtos.Song;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Brian Millar
 */
public class StreamCommand implements Command {

    @Override
    public String executeCommand(HttpServletRequest request, HttpServletResponse response) {
        SongDao songs = new SongDao();
        byte songdata[] = (((Song)songs.getSongById(Integer.parseInt(request.getParameter("songid")))).getSongdata());
        String filename =  System.currentTimeMillis() + ".mp3";
        try {
            FileOutputStream output = new FileOutputStream(new File("../webapps/ROOT/" + filename));
            output.write(songdata);
            output.close();
        } catch (IOException ex) {
            Logger.getLogger(StreamCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "/stream_test.jsp?file=" + filename;
    }
}