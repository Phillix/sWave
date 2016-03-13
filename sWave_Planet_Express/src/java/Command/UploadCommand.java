package Command;

import Daos.SongDao;
import Dtos.Song;
import java.io.IOException;
import java.io.InputStream;
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
            ID3V2 id3      = new ID3V2(buffer);
            Song s          = new Song(0,
                                       fileName.substring(0, fileName.length() - 4),
                                       "Unknown",
                                       "Unknown",
                                       "Unknown",
                                       2016,
                                       0.00,
                                       "CC",
                                       buffer
                               );
            
            if (id3.getTitle() != null)
                s.setTitle(id3.getTitle());
            if (id3.getArtist() != null)
                s.setArtist(id3.getArtist());
            if (id3.getAlbum() != null)
                s.setAlbum(id3.getAlbum());
            if (id3.getGenre() != null)
                s.setGenre(id3.getGenre());
            if (id3.getYear() != 0)
                s.setYear(id3.getYear());
            
            dao.addNewSong(s);
            uploadSize += buffer.length;
            count++;
            sWave.Logger.writeLine(count + " Files Uploaded using " + (double)uploadSize / 1024.0 / 1024.0 + "MB");
        }
        return "/uploadComplete.jsp";
    }
}