package sWave;

/**
 *
 * @author Brian Millar
 */
public class ID3V2 {

    private String title;
    private String artist;
    private String album;
    private String genre;
    private int    year;
    private byte[] artwork;

    public ID3V2(String title, String artist, String album, String genre, int year, byte[] artwork) {
        this.title   = title;
        this.artist  = artist;
        this.album   = album;
        this.genre   = genre;
        this.year    = year;
        this.artwork = artwork;
    }
    
    public ID3V2(byte songdata[]) {
        this.title   = null;
        this.artist  = null;
        this.album   = null;
        this.genre   = null;
        this.year    = 0;
        this.artwork = null;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public byte[] getArtwork() {
        return artwork;
    }

    public void setArtwork(byte[] artwork) {
        this.artwork = artwork;
    }
}
