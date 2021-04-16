package com.anitachipkar.movieshows.data;

import com.anitachipkar.movieshows.entities.MovieDetails;
import com.anitachipkar.movieshows.entities.MovieListResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApi
{
    @GET("?")
    Call<MovieListResponse> getMovieList(@Query("s") String title, @Query("page") int pages, @Query("apikey") String apikey);
    @GET("?")
    Call<MovieDetails> getMovieDetails(@Query("i") String imdbId, @Query("apikey") String apiKey);
}
