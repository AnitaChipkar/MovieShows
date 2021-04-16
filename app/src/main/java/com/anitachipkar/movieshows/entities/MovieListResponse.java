package com.anitachipkar.movieshows.entities;

import java.io.Serializable;
import java.util.List;

public class MovieListResponse implements Serializable {
    List<com.anitachipkar.movieshows.entities.Search> Search;
    String totalResults;

    public MovieListResponse(List<com.anitachipkar.movieshows.entities.Search> searchList, String totalResult) {
        this.Search = searchList;
        this.totalResults = totalResult;
    }

    public List<com.anitachipkar.movieshows.entities.Search> getSearch() {
        return Search;
    }

    public void setSearch(List<com.anitachipkar.movieshows.entities.Search> search) {
        this.Search = search;
    }

    public String getTotalResult() {
        return totalResults;
    }

    public void setTotalResult(String totalResult) {
        this.totalResults = totalResult;
    }
}
