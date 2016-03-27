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
        try {
            ArrayList<Part> songs = (ArrayList<Part>)request.getParts();
            long uploadSize = 0;
            int  count      = 0;

            //Starting at 1 as Part 0 is the 'action'
            for (int j = 1; j < songs.size(); j++) {
                InputStream fileStream = null;
                fileStream    = songs.get(j).getInputStream();
                byte buffer[] = new byte[(int)songs.get(j).getSize()];
                int data = fileStream.read();
                for (int i = 0; i < buffer.length; i++) {
                    buffer[i] = ((byte)data);
                    data = fileStream.read();
                }
                SongDao dao     = new SongDao();
                String fileName = songs.get(j).getSubmittedFileName();
                Song track = new Song();
                track.setSongdata(buffer);
                track.setFilename(fileName.substring(0, fileName.length() - 4));
                track.setTitle(fileName.substring(0, fileName.length() - 4));
                track.setUploaded(new Date(System.currentTimeMillis()));
                //The ID3V2 extractor will set what data it can find
                ID3V2.extractTags(track);
                dao.addNewSong(track);
            }
        }
        catch (IOException | ServletException e) {
            if (DEBUG)
                e.printStackTrace();
        }
        return null; //We don't want to redirect
    }
}
