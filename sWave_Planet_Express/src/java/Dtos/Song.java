package Dtos;

import java.sql.Date;
import java.util.Objects;

/**
 * The dto for creating a song
 * @author Austin
 * @author Brian Millar
 */
public class Song {
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
        filename  = "filename";
        title     = "title";
        artist    = "artist";
        album     = "album";
        genre     = "genre";
        year      = 0;
        duration  = 0;
        price     = 0.00;
        license   = "license";
        playCount = 0;
        uploaded  = null;
        artwork   = null;
        songdata  = null;
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

    //A version without the songdata for listings etc.
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

    public int getSongId() {
        return songId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
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
    
    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public int getPlayCount() {
        return playCount;
    }

    public void setPlayCount(int playCount) {
        this.playCount = playCount;
    }

    public Date getUploaded() {
        return uploaded;
    }

    public void setUploaded(Date uploaded) {
        this.uploaded = uploaded;
    }

    public byte[] getArtwork() {
        return artwork;
    }

    public void setArtwork(byte[] artwork) {
        this.artwork = artwork;
    }

    public byte[] getSongdata() {
        return songdata;
    }

    public void setSongdata(byte[] songdata) {
        this.songdata = songdata;
    }

    @Override
    public String toString() {
        return "Song{" + "songId=" + songId + ", filename=" + filename + ", title=" + title + ", artist=" + artist + ", album=" + album + ", genre=" + genre + ", year=" + year + ", duration=" + duration + ", price=" + price + ", license=" + license + ", playCount=" + playCount + ", uploaded=" + uploaded + '}';
    }

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
        if (!Objects.equals(this.genre, other.genre))
            return false;
        return true;
    }

}
