package Dtos;

import java.util.Objects;

/**
 * The dto for creating a song
 * @author Austin
 * @author Brian Millar
 */
public class Song {
    private int    songId;
    private String title;
    private String artist;
    private String album;
    private String genre;
    private int    year;
    private double price;
    private String license;
    private byte[] songdata;

    /**
     * Default constructor to initialize a default song
     */
    public Song() {
        title    = "title";
        artist   = "artist";
        album    = "album";
        genre    = "genre";
        year     = 2000;
        price    = 0.00;
        license  = "license";
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
    public Song(int songId, String title, String artist, String album, String genre, int year, double price, String license, byte[] songdata) {
        this.songId   = songId;
        this.title    = title;
        this.artist   = artist;
        this.album    = album;
        this.genre    = genre;
        this.year     = year;
        this.price    = price;
        this.license  = license;
        this.songdata = songdata;
    }

    //A version without the songdata for listings etc.
    public Song(int songId, String title, String artist, String album, String genre, int year, double price, String license) {
        this.songId   = songId;
        this.title    = title;
        this.artist   = artist;
        this.album    = album;
        this.genre    = genre;
        this.year     = year;
        this.price    = price;
        this.license  = license;
    }

    public int getSongId() {
        return songId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
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

    public byte[] getSongdata() {
        return songdata;
    }

    public void setSongdata(byte[] songdata) {
        this.songdata = songdata;
    }

    @Override
    public String toString() {
        return "Song{" + "songId=" + songId + ", title=" + title + ", artist=" + artist + ", album=" + album + ", genre=" + genre + ", year=" + year + ", price=" + price + ", license=" + license + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.title);
        hash = 29 * hash + Objects.hashCode(this.artist);
        hash = 29 * hash + Objects.hashCode(this.album);
        hash = 29 * hash + Objects.hashCode(this.genre);
        hash = 29 * hash + this.year;
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
        if (!Objects.equals(this.title, other.title))
            return false;
        if (!Objects.equals(this.artist, other.artist))
            return false;
        if (!Objects.equals(this.album, other.album))
            return false;
        return Objects.equals(this.genre, other.genre);
    }
}
