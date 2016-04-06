package Command;

import Daos.SongDao;
import Dtos.Song;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.catalina.tribes.util.Arrays;

/**
 *
 * @author Austin
 */
public class EditSongCommand implements Command {

    @Override
    public String executeCommand(HttpServletRequest request, HttpServletResponse response) {
        SongDao sd = new SongDao();
        Song song  = null;
        
        String songId   = request.getParameter("songId");
        String filename = request.getParameter("filename");
        String title    = request.getParameter("title");
        String artist   = request.getParameter("artist");
        String album    = request.getParameter("album");
        String genre    = request.getParameter("genre");
        String year     = request.getParameter("year"); //int
        String price    = request.getParameter("price"); //double
        String license  = request.getParameter("license");
        String artwork  = request.getParameter("artwork"); //byte array
        
        if (songId != null && !songId.isEmpty()) {
            song = sd.getSongById(Integer.parseInt(songId));
            if (filename != null && !filename.isEmpty()) song.setFilename(filename);
            if (title != null    && !title.isEmpty())    song.setTitle(title);
            if (artist != null   && !artist.isEmpty())   song.setArtist(artist);
            if (album != null    && !album.isEmpty())    song.setAlbum(album);
            if (genre != null    && !genre.isEmpty())    song.setGenre(genre);
            if (year != null     && !year.isEmpty())     song.setYear(Integer.parseInt(year));
            if (price != null    && !price.isEmpty())    song.setPrice(Double.parseDouble(price));
            if (license != null  && !license.isEmpty())  song.setLicense(license);
            if (artwork != null  && !artwork.isEmpty())  song.setArtwork(artwork.getBytes());
        }
        
        if (sd.editDetails(song) == 0) return "/music.jsp";
        return "/error.jsp?msg=Song%20details%20not%20saved%20correctly.";
    }
    
}
