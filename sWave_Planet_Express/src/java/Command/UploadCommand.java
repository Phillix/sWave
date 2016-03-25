package Command;

import Daos.SongDao;
import Dtos.Song;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import sWave.ID3V2;

/**
 *
 * @author Brian Millar
 */
public class UploadCommand implements Command {

    private static final boolean DEBUG = sWave.Server.DEBUGGING;

    @Override
    public String executeCommand(HttpServletRequest request, HttpServletResponse response) {
        ArrayList<Part> songs;
        long uploadSize = 0;
        int count       = 0;

        try {
            songs = (ArrayList<Part>)request.getParts();
        } catch (IOException | ServletException ex) {
            if (DEBUG)
                ex.printStackTrace();
            return "/uploadFailed.jsp";
        }
        //Starting at 1 as Part 0 is the 'action'
        for (int j = 1; j < songs.size(); j++) {
            InputStream fileStream;
            try {
                fileStream = songs.get(j).getInputStream();
            } catch (IOException ex) {
                if (DEBUG)
                    ex.printStackTrace();
                return "/uploadFailed.jsp";
            }
            byte buffer[] = new byte[(int)songs.get(j).getSize()];
            int x;
            try {
                x = fileStream.read();
            } catch (IOException ex) {
                if (DEBUG)
                    ex.printStackTrace();
                return "/uploadFailed.jsp";
            }
            for (int i = 0; i < buffer.length; i++) {
                buffer[i] = ((byte)x);
                try {
                    x = fileStream.read();
                } catch (IOException ex) {
                    if (DEBUG)
                        ex.printStackTrace();
                    return "/uploadFailed.jsp";
                }
            }
            SongDao dao     = new SongDao();
            String fileName = songs.get(j).getSubmittedFileName();
            /*if (id3.getTitle() == null)
                id3.setTitle(fileName.substring(0, fileName.length() - 4));*/
            Song s          = new Song(0,
                                       fileName,
                                       fileName,
                                       "Unknown",
                                       "Unknown",
                                       "Unknown",
                                       2016,
                                       0,
                                       0.00,
                                       "CC",
                                       0,
                                       new Date(System.currentTimeMillis()),
                                       null,
                                       buffer
                               );
            
            ID3V2.extractTags(s);
            
            dao.addNewSong(s);
            uploadSize += buffer.length;
            count++;
            sWave.Server.log(count + " Files Uploaded using " + (double)uploadSize / 1024.0 / 1024.0 + "MB");
        }
        return "/uploadComplete.jsp";
    }
}
