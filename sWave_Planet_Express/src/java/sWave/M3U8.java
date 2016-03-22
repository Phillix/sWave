package sWave;
import Dtos.Playlist;
import Dtos.Song;

/**
 *
 * @author Brian Millar
 */
public class M3U8 {
    private String filename;
    private String fileContent;
    
    public M3U8(Playlist playlist) {
        filename    = playlist.getTitle() + ".m3u8";
        fileContent = "#EXTM3U\n";
        playlist.getPlaylistContents()
                .stream()
                .forEach((Song s) -> {
                    fileContent += "#EXTINF:"      +
                                   s.getDuration() + ","   +
                                   s.getArtist()   + " - " +
                                   s.getTitle()    + '\n'  +
                                   s.getFilename() + '\n';
                });
    }
    
    public String getFileContent() {
        return fileContent;
    }
    
    public String getFileName() {
        return filename;
    }
}
