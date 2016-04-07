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
import sWave.ID3V1;
import sWave.ID3V2;

/**
 *
 * @author Brian Millar
 */
public class UploadCommand implements Command {

    private static final boolean DEBUG = sWave.Server.DEBUGGING;

    @Override
    public String executeCommand(HttpServletRequest request, HttpServletResponse response) {
        ArrayList<Part> songs = null;
        long uploadSize = 0;
        int  count      = 0;

        try {
            songs = (ArrayList<Part>)request.getParts();
        } catch (IOException | ServletException ex) {
            if (DEBUG)
                ex.printStackTrace();
        }

        if (songs != null) {
            //Remove Part 0 as it is the 'action'
            songs.remove(0);
            
            songs.stream().distinct().filter(
                (Part p) -> p.getSize() < (1024*1024*16)
            ).forEach((Part p) -> {
                try {
                    InputStream fileStream;
                    fileStream = p.getInputStream();
                    byte buffer[] = new byte[(int)p.getSize()];
                    int data = fileStream.read();
                    for (int i = 0; i < buffer.length; i++) {
                        buffer[i] = ((byte)data);
                        data = fileStream.read();
                    }
                    SongDao dao = new SongDao();
                    String fileName = p.getSubmittedFileName();
                    Song track = new Song();
                    track.setSongdata(buffer);
                    track.setFilename(fileName.substring(0, fileName.length() - 4));
                    track.setTitle(fileName.substring(0, fileName.length() - 4));
                    track.setUploaded(new Date(System.currentTimeMillis()));
                    //The ID3 extractors will set whatever data they can find
                    //ID3V1.extractTags(track);
                    ID3V2.extractTags(track);
                    dao.addNewSong(track);
                }
                catch (IOException e) {
                    if (DEBUG)
                        e.printStackTrace();
                }
            });
        }
        return null; //We don't want to redirect
    }
}
