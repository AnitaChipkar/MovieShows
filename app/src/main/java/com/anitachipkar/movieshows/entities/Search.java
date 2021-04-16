package com.anitachipkar.movieshows.entities;


import java.io.Serializable;

public class Search implements Serializable {

    private String Title;
    private String Year;
    private String imdbID;
    private String Type;
    private String Poster;
    private String totalResults;

    public Search(String title, String year, String imdbID, String poster)
    {
        this.Title = title;
        this.Year = year;
        this.imdbID = imdbID;
        this.Poster = poster;
    }

    public Search(String title, String year, String imdbID, String type, String poster, String totalResults)
    {
        this.Title = title;
        this.Year = year;
        this.imdbID = imdbID;
        this.Type = type;
        this.Poster = poster;
        this.totalResults = totalResults;
    }

    public String getTitle()
    {
        return Title;
    }

    public void setTitle(String title)
    {
        this.Title = title;
    }

    public String getYear()
    {
        return Year;
    }

    public void setYear(String year)
    {
        this.Year = year;
    }

    public String getImdbID()
    {
        return imdbID;
    }

    public void setImdbID(String imdbID)
    {
        this.imdbID = imdbID;
    }

    public String getType()
    {
        return Type;
    }

    public void setType(String type)
    {
        this.Type = type;
    }

    public String getPoster()
    {
        return Poster;
    }

    public void setPoster(String poster)
    {
        this.Poster = poster;
    }

    public String getTotalResults()
    {
        return totalResults;
    }

    public void setTotalResults(String totalResults)
    {
        this.totalResults = totalResults;
    }


}
