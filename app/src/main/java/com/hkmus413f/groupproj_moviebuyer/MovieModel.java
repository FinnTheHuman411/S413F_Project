package com.hkmus413f.groupproj_moviebuyer;

public class MovieModel {
    int index;
    int image;
    String movieName;
    String moviePrice;
    String movieGenre;

    public MovieModel(int image, String movieName, String moviePrice, String movieGenre) {
        this.image = image;
        this.movieName = movieName;
        this.moviePrice = moviePrice;
        this.movieGenre = movieGenre;
    }

    public int getImage(){
        return image;
    }

    public void setImage(int image){
        this.image = image;
    }

    public String getMovieName(){
        return movieName;
    }

    public void setMovieName(String movieName){
        this.movieName = movieName;
    }

    public String getMoviePrice(){
        return moviePrice;
    }

    public void setMoviePrice(String moviePrice){
        this.movieName = moviePrice;
    }

    public String getMovieGenre(){
        return movieGenre;
    }

    public void setMovieGenre(String movieGenre){
        this.movieGenre = movieGenre;
    }

}
