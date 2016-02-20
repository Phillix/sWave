/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Command;

import Daos.SongDataDao;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author Brian Millar
 */
public class UploadCommand implements Command {

    @Override
    public String executeCommand(HttpServletRequest request, HttpServletResponse response) {
        Part songData;
        try {
            songData = request.getPart("songBlob");
        } catch (IOException | ServletException ex) {
            return "uploadFailed.jsp";
        }
        InputStream fileStream;
        try {
            fileStream = songData.getInputStream();
        } catch (IOException ex) {
            return "uploadFailed.jsp";
        }
        byte buffer[] = new byte[(int)songData.getSize()];
        int x;
        try {
            x = fileStream.read();
        } catch (IOException ex) {
            return "uploadFailed.jsp";
        }
        for (int i = 0; i < buffer.length; i++) {
            buffer[i] = ((byte)x);
            try {
                x = fileStream.read();
            } catch (IOException ex) {
                return "uploadFailed.jsp";
            }
        }
        SongDataDao dao = new SongDataDao();
        dao.addNewSong(buffer);
        return "/uploadComplete.jsp";
    }
}
