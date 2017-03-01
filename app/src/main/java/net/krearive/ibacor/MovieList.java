package net.krearive.ibacor;

/**
 * Created by Win8 on 10/01/2017.
 */

public class MovieList {
    //Data Variables
    private String movie, poster, genre, duration, harga, bioskop;

    public String getBioskop() {
        return bioskop;
    }

    public void setBioskop(String bioskop) {
        this.bioskop = bioskop;
    }

    public MovieList() {
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    //klik kanan - generate - constructor
    public MovieList(String movie, String poster, String genre) {
        this.movie = movie;
        this.poster = poster;
        this.genre = genre;
    }

    //klik kanan - generate - getter and setter

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }


    //    public MovieList(String judul, String poster, String genre) {
//        this.judul = judul;
//        this.poster = poster;
//        this.genre = genre;
//
//    }

}
