package com.anitachipkar.movieshows.repository;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.anitachipkar.movieshows.entities.MovieListResponse;
import com.anitachipkar.movieshows.entities.Search;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Class Acts as data source
 */
public class MovieDataSource extends androidx.paging.PageKeyedDataSource<Integer, Search>
{
    private static final String TAG = MovieDataSource.class.getSimpleName();
    //we will start from the first page which is 1
    private static int FIRST_PAGE = 1;

    private String mSearchKey;
    private Context mContext;
    private MovieRepository mRepository;

    public MovieDataSource(String searchKey, Context context)
    {
        this.mSearchKey = searchKey;
        this.mContext = context;
        mRepository = MovieRepository.getInstance(mContext);
    }


    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params,
        @NonNull final LoadInitialCallback<Integer, Search> callback)
    {
        Log.i(TAG, "Load initial " +mSearchKey +" first page " + FIRST_PAGE);

        Call<MovieListResponse> call = mRepository.getSearchResult(mSearchKey, FIRST_PAGE);
        call.enqueue(new Callback<MovieListResponse>()
        {
            @Override
            public void onResponse(Call<MovieListResponse> call, Response<MovieListResponse> response)
            {
                Log.i(TAG, "Initial load completed");
                if(response == null || response.body()==null) return;
                //Log.i(TAG, "response size is " + response.body().getShowDetailsList());
                FIRST_PAGE++;
                callback.onResult(response.body().getSearch(), null, FIRST_PAGE);
            }

            @Override
            public void onFailure(Call<MovieListResponse> call, Throwable t)

            {
                Log.e(TAG, t.toString());
            }
        });


    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params,
        @NonNull LoadCallback<Integer, Search> callback)
    {
        Log.i(TAG, "Load before");

    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params,
        @NonNull final LoadCallback<Integer, Search> callback)
    {
        Log.i(TAG, "Load After " +mSearchKey +" page "+ params.key);
        Call<MovieListResponse> call = mRepository.getSearchResult(mSearchKey, params.key);
        call.enqueue(new Callback<MovieListResponse>()
        {
            @Override
            public void onResponse(Call<MovieListResponse> call, Response<MovieListResponse> response)
            {
                Log.i(TAG, "After load completed");
                if(response == null || response.body()==null) return;
                callback.onResult(response.body().getSearch(), params.key + 1);
            }

            @Override
            public void onFailure(Call<MovieListResponse> call, Throwable t)

            {
                Log.e(TAG, t.toString());

            }
        });

    }
}
