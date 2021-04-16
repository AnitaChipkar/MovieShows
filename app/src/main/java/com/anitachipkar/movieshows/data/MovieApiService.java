package com.anitachipkar.movieshows.data;


import com.anitachipkar.movieshows.AppConstants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieApiService
{
    private Retrofit retrofit;
    private volatile static MovieApiService instance;

    private MovieApiService()
    {
        initializeRetrofit();
    }

    public static MovieApiService getInstance()
    {
        if (instance == null) {
            synchronized (MovieApiService.class) {
                if (instance == null) {
                    instance = new MovieApiService();
                }
            }

        }
        return instance;
    }

    /**
     * return retrofit instance
     *
     * @return
     */
    private void initializeRetrofit()
    {

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                .baseUrl(AppConstants.DATA_REQUEST_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        }

    }


    /**
     * return api reference
     * @return
     */
    public MovieApi getApi(){
        return  retrofit.create(MovieApi.class);
    }
}
