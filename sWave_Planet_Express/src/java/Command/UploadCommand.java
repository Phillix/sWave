package Command;

import Daos.SongDao;
import java.io.IOException;
import java.io.InputStream;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import sWaveEngine.ID3v2;

/**
 *
 * @author Brian Millar
 */
public class UploadCommand implements Command {
    
    private static final boolean DEBUG = Debugging.Debug.debug;

    @Override
    public String executeCommand(HttpServletRequest request, HttpServletResponse response) {
        Part songData;
        try {
            songData = request.getPart("songBlob");
        } catch (IOException | ServletException ex) {
            if (DEBUG)
                ex.printStackTrace();
            return "/uploadFailed.jsp";
        }
        InputStream fileStream;
        try {
            fileStream = songData.getInputStream();
        } catch (IOException ex) {
            if (DEBUG)
                ex.printStackTrace();
            return "/uploadFailed.jsp";
        }
        byte buffer[] = new byte[(int)songData.getSize()];
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
        SongDao dao = new SongDao();
        ID3v2 metadata = null;
        dao.addNewSong(metadata, buffer);
        return "/uploadComplete.jsp";
    }
}
