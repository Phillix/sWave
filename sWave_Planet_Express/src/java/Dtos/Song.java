package Dtos;

import java.util.Objects;

/**
 * The dto for creating a song
 * @author Austin
 */
public class Song {
    private int    songId;
    private String title;
    private String artist;
    private String genre;
    private int    relYear;
    private double price;
    private String licence;
    private byte[] songdata;

    /**
     * Default constructor to initialize a default song
     */
    public Song() {
        title    = "title";
        artist   = "artist";
        genre    = "genre";
        relYear  = 2000;
        price    = 0.00;
        licence  = "licence";
    }

    /**
     * Overloaded constructor for creating a song
     * @param title The name of the song
     * @param artist The person who made the song
     * @param genre The genre of the song
     * @param relYear The year the song was released
     * @param price How much the song costs
     * @param licence The license for the song
     */
    public Song(int songId, String title, String artist, String genre, int relYear, double price, String licence, byte[] songdata) {
        this.songId   = songId;
        this.title    = title;
        this.artist   = artist;
        this.genre    = genre;
        this.relYear  = relYear;
        this.price    = price;
        this.licence  = licence;
        this.songdata = songdata;
    }

    //A version without the songdata for listings etc.
    public Song(int songId, String title, String artist, String genre, int relYear, double price, String licence) {
        this.songId   = songId;
        this.title    = title;
        this.artist   = artist;
        this.genre    = genre;
        this.relYear  = relYear;
        this.price    = price;
        this.licence  = licence;
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

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getRelYear() {
        return relYear;
    }

    public void setRelYear(int relYear) {
        this.relYear = relYear;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getLicence() {
        return licence;
    }

    public void setLicence(String licence) {
        this.licence = licence;
    }

    public byte[] getSongdata() {
        return songdata;
    }

    public void setSongdata(byte[] songdata) {
        this.songdata = songdata;
    }

    @Override
    public String toString() {
        return "Song{" + "songId=" + songId + ", title=" + title + ", artist=" + artist + ", genre=" + genre + ", relYear=" + relYear + ", price=" + price + ", licence=" + licence + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.songId;
        hash = 53 * hash + Objects.hashCode(this.title);
        hash = 53 * hash + Objects.hashCode(this.artist);
        hash = 53 * hash + Objects.hashCode(this.genre);
        hash = 53 * hash + this.relYear;
        hash = 53 * hash + (int) (Double.doubleToLongBits(this.price) ^ (Double.doubleToLongBits(this.price) >>> 32));
        hash = 53 * hash + Objects.hashCode(this.licence);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        final Song other = (Song) obj;
        if (getClass() != obj.getClass())
            return false;
        if (this.songId != other.songId)
            return false;
        if (this.relYear != other.relYear)
            return false;
        if (Double.doubleToLongBits(this.price) != Double.doubleToLongBits(other.price))
            return false;
        if (!Objects.equals(this.title, other.title))
            return false;
        if (!Objects.equals(this.artist, other.artist))
            return false;
        if (!Objects.equals(this.genre, other.genre))
            return false;
        return Objects.equals(this.licence, other.licence);
    }


}
