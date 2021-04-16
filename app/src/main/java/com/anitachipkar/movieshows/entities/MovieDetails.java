package com.anitachipkar.movieshows.entities;

import java.io.Serializable;

public class MovieDetails implements Serializable {

    private String Title;
    private String Year;
    private String Genre;
    private String Director;
    private String imdbRating;
    private String Actors;
    private String Poster;

    public MovieDetails(String title, String year, String genre, String director, String imdbRating, String actors,
                        String poster) {
        this.Title = title;
        this.Year = year;
        this.Genre = genre;
        this.Director = director;
        this.imdbRating = imdbRating;
        this.Actors = actors;
        this.Poster = poster;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        this.Title = title;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String year) {
        this.Year = year;
    }

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String genre) {
        this.Genre = genre;
    }

    public String getDirector() {
        return Director;
    }

    public void setDirector(String director) {
        this.Director = director;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }

    public String getActors() {
        return Actors;
    }

    public void setActors(String actors) {
        this.Actors = actors;
    }

    public String getPoster() {
        return Poster;
    }

    public void setPoster(String poster) {
        this.Poster = poster;
    }
}
