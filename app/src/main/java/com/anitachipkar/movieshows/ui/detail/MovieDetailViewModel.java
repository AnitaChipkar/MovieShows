package com.anitachipkar.movieshows.ui.detail;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.anitachipkar.movieshows.entities.MovieDetails;
import com.anitachipkar.movieshows.repository.MovieRepository;

public class MovieDetailViewModel extends AndroidViewModel {
    private final MovieRepository movieRepository;

    public MovieDetailViewModel(@NonNull Application application) {
        super(application);
        movieRepository = MovieRepository.getInstance(application);
    }

    public LiveData<MovieDetails> getShowDetails(String imdbid) {
        return movieRepository.getShowDetails(imdbid);
    }

}
