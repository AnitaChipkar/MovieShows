package com.anitachipkar.movieshows.repository;

import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.anitachipkar.movieshows.data.MovieApi;
import com.anitachipkar.movieshows.data.MovieApiService;
import com.anitachipkar.movieshows.entities.MovieDetails;
import com.anitachipkar.movieshows.entities.MovieListResponse;
import com.anitachipkar.movieshows.AppConstants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository
{
    private static final String TAG = MovieRepository.class.getSimpleName();
   // LiveData<List<Search>> searchDetailsList;
    private volatile static MovieRepository instance;
    private MovieApiService mService;


    private MovieRepository(Context application)
    {
        mService = MovieApiService.getInstance();
       // searchDetailsList = getAllBookMark();
    }

    public static MovieRepository getInstance(Context application)
    {
        if (instance == null) {
            synchronized (MovieRepository.class) {
                if ((instance == null)) {
                    instance = new MovieRepository(application);
                }
            }

        }
        return instance;
    }
    public Call<MovieListResponse> getSearchResult(String key, int page)
    {
        return mService.getApi().getMovieList(key, page, AppConstants.API_KEY);
    }

    public LiveData<MovieDetails> getShowDetails(String imdbId)
    {
        final MutableLiveData<MovieDetails> mutableLiveData = new MutableLiveData<>();
        MovieApi showApi = MovieApiService.getInstance().getApi();
        Call<MovieDetails> call = showApi.getMovieDetails(imdbId, AppConstants.API_KEY);
        call.enqueue(new Callback<MovieDetails>()
        {
            @Override
            public void onResponse(Call<MovieDetails> call, Response<MovieDetails> response)
            {
                mutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<MovieDetails> call, Throwable t)
            {
                mutableLiveData.setValue(null);

            }
        });
        return mutableLiveData;
    }


}
