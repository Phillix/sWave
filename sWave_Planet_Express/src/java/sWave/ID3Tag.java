package sWave;

/**
 *
 * @author Brian Millar
 */
public class ID3Tag {

    private String title;
    private String artist;
    private String album;
    private int    year;
    private byte[] artwork;

    public ID3Tag(String title, String artist, String album, int year, byte[] artwork) {
        this.title   = title;
        this.artist  = artist;
        this.album   = album;
        this.year    = year;
        this.artwork = artwork;
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

    public static ID3Tag getTag(byte song[]) {
        String title  = null;
        String artist = null;
        String album  = null;
        int    year   = 1916;
        byte   art[]  = null;
        ID3Tag tag = new ID3Tag(title, artist, album, year, art);
        return tag;
    }

}
