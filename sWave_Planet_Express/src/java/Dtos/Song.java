package Dtos;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

/**
 * The dto for creating a song
 * @author Austin
 * @author Brian Millar
 */
public class Song implements Serializable {
    private int    songId;
    private String filename;
    private String title;
    private String artist;
    private String album;
    private String genre;
    private int    year;
    private int    duration;
    private double price;
    private String license;
    private int    playCount;
    private Date   uploaded;
    private byte[] artwork;
    private byte[] songdata;

    /**
     * Default constructor to initialize a default song
     */
    public Song() {
        filename  = "untitled";
        title     = "Unknown";
        artist    = "Unknown";
        album     = "Unknown";
        genre     = "Unknown";
        price     = 2.99; //Our Default Price
        license   = "Unknown";
        playCount = 0;
        uploaded  = new Date(System.currentTimeMillis()); 
        //Capture Moment of Object Creation as Upload Time
    }

    /**
     * Overloaded constructor for creating a song
     * @param title The name of the song
     * @param artist The person who made the song
     * @param genre The genre of the song
     * @param year The year the song was released
     * @param price How much the song costs
     * @param license The license for the song
     */
    public Song(int songId, String filename, String title, String artist, String album, String genre, int year, int duration, double price, String license, int playCount, Date uploaded, byte artwork[], byte songdata[]) {
        this.songId    = songId;
        this.filename  = filename;
        this.title     = title;
        this.artist    = artist;
        this.album     = album;
        this.genre     = genre;
        this.year      = year;
        this.duration  = duration;
        this.price     = price;
        this.license   = license;
        this.playCount = playCount;
        this.uploaded  = uploaded;
        this.artwork   = artwork;
        this.songdata  = songdata;
    }

    /**
     * Overloaded constructor for Songs
     * @param songId the id of the song
     * @param filename the name of file uploaded
     * @param title the title of the song
     * @param artist the artist name
     * @param album the album name
     * @param genre the genre of the song
     * @param year the year the song was released
     * @param duration the duration of the song
     * @param price the price of the song
     * @param license the license on the song
     * @param playCount the play count of the song
     * @param uploaded the date the song was uploaded
     * @param artwork the album or single artwork
     */
    public Song(int songId, String filename, String title, String artist, String album, String genre, int year, int duration, double price, String license, int playCount, Date uploaded, byte artwork[]) {
        this.songId    = songId;
        this.filename  = filename;
        this.title     = title;
        this.artist    = artist;
        this.album     = album;
        this.genre     = genre;
        this.year      = year;
        this.duration  = duration;
        this.price     = price;
        this.license   = license;
        this.playCount = playCount;
        this.uploaded  = uploaded;
        this.artwork   = artwork; //We will want to display the artwork
        //Don't set songdata as used for listing
    }

    /**
     * Getting the id of this song
     * @return the id of this song 
     */
    public int getSongId() {
        return songId;
    }

    /**
     * Setting the id of this song
     * @param songId the new id of this song
     */
    public void setSongId(int songId) {
        this.songId = songId;
    }

    /**
     * Getting the name of the file
     * @return the name of the file
     */
    public String getFilename() {
        return filename;
    }
    
    /**
     * Setting the name of the file
     * @param filename the new name of the file
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }

    /**
     * Getting the title of the song
     * @return the title of the song
     */
    public String getTitle() {
        return title;
    }

    /**
     * Setting the title of the song
     * @param title the new title of the song
     */
    public void setTitle(String title) {
        if (title != null && !title.isEmpty()) {
            if (title.length() > 50)
                title = title.substring(0, 47) + "...";
            this.title = title;
        }
    }

    /**
     * Getting the artist who made the song
     * @return the artist who made the song
     */
    public String getArtist() {
        return artist;
    }

    /**
     * Setting the artist who made the song
     * @param artist the new name of the artist
     */
    public void setArtist(String artist) {
        if (artist != null && !artist.isEmpty()) {
            if (artist.length() > 50)
                artist = artist.substring(0, 47) + "...";
            this.artist = artist;
        }
    }

    /**
     * Getting the name of the album
     * @return the name of the album
     */
    public String getAlbum() {
        return album;
    }

    /**
     * Setting the name of the album
     * @param album the new name of the album
     */
    public void setAlbum(String album) {
        if (album != null && !album.isEmpty()) {
            if (album.length() > 50)
                album = album.substring(0, 47) + "...";
            this.album = album;
        }
    }

    /**
     * Getting the name of the genre
     * @return the name of the genre
     */
    public String getGenre() {
        return genre;
    }
    
    /**
     * Setting the name of the genre
     * @param genre the new name of the genre
     */
    public void setGenre(String genre) {
        if (genre != null && !genre.isEmpty()) {
            if (genre.length() > 50)
                genre = genre.substring(0, 47) + "...";
            this.genre = genre;
        }
    }

    /**
     * Getting the year the song was made in
     * @return the year the song was made in
     */
    public int getYear() {
        return year;
    }
    
    /**
     * Setting the year the song was made in
     * @param year the new year that the song is made in
     */
    public void setYear(int year) {
        this.year = year;
    }
    
    /**
     * Getting the duration of the song
     * @return the duration of the song
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Setting the duration of the song
     * @param duration the new duration of the song
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * Getting the price of the song
     * @return the price of the song
     */
    public double getPrice() {
        return price;
    }

    /**
     * Setting the price of the song
     * @param price the new price of the song
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Getting the license on the song
     * @return the license on the song
     */
    public String getLicense() {
        return license;
    }

    /**
     * Setting the license on the song
     * @param license the new license on the song
     */
    public void setLicense(String license) {
        this.license = license;
    }

    /**
     * Getting the amount of times the song has been played
     * @return the amount of times the song has been played
     */
    public int getPlayCount() {
        return playCount;
    }

    /**
     * Setting the amount of times the song has been played
     * @param playCount the new amount of times the song was played
     */
    public void setPlayCount(int playCount) {
        this.playCount = playCount;
    }

    /**
     * Getting the date the song was uploaded on
     * @return the date the song was uploaded on
     */
    public Date getUploaded() {
        return uploaded;
    }

    /**
     * Setting the date the song was uploaded on
     * @param uploaded the new date the song is uploaded on
     */
    public void setUploaded(Date uploaded) {
        this.uploaded = uploaded;
    }

    /**
     * Getting the artwork for the song
     * @return the artwork for the song
     */
    public byte[] getArtwork() {
        return artwork;
    }

    /**
     * Setting the artwork for the song
     * @param artwork the new artwork for the song
     */
    public void setArtwork(byte[] artwork) {
        this.artwork = artwork;
    }

    /**
     * Getting the song data
     * @return the song data
     */
    public byte[] getSongdata() {
        return songdata;
    }

    /**
     * Setting the song data
     * @param songdata the new song data for the song
     */
    public void setSongdata(byte[] songdata) {
        this.songdata = songdata;
    }
    
    /**
     * The toString method for easily representing the information of this class
     * @return a String containing all information from this Song
     */
    @Override
    public String toString() {
        return "Song{" + "songId=" + songId + ", filename=" + filename + ", title=" + title + ", artist=" + artist + ", album=" + album + ", genre=" + genre + ", year=" + year + ", duration=" + duration + ", price=" + price + ", license=" + license + ", playCount=" + playCount + ", uploaded=" + uploaded + '}';
    }

    /**
     * A hashCode method for hashing each song
     * @return an int for the hash of this song
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + Objects.hashCode(this.title);
        hash = 19 * hash + Objects.hashCode(this.artist);
        hash = 19 * hash + Objects.hashCode(this.album);
        hash = 19 * hash + Objects.hashCode(this.genre);
        hash = 19 * hash + this.year;
        hash = 19 * hash + this.duration;
        return hash;
    }

    /**
     * An equals method for comparing this song with another object
     * @param obj the object to compare this song to
     * @return a boolean value for if this song equals the argument
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Song other = (Song) obj;
        if (this.year != other.year)
            return false;
        if (this.duration != other.duration)
            return false;
        if (!Objects.equals(this.title, other.title))
            return false;
        if (!Objects.equals(this.artist, other.artist))
            return false;
        if (!Objects.equals(this.album, other.album))
            return false;
        return Objects.equals(this.genre, other.genre);
    }
}
