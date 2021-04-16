package com.anitachipkar.movieshows.repository;

import android.content.Context;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import com.anitachipkar.movieshows.entities.Search;


public class MovieDataSourceFactory extends androidx.paging.DataSource.Factory<Integer, Search>
{
    private MutableLiveData<MovieDataSource> mMutableLiveData;
    private MovieRepository mRepository;
    private Context context;
    private String mSearchKey="";

    public MovieDataSourceFactory(MovieRepository mRepository, Context context)
    {
        this.mMutableLiveData = new MutableLiveData<>();
        this.mRepository = mRepository;
        this.context = context;
    }

    public MutableLiveData<MovieDataSource> getMutableLiveData()
    {
        return mMutableLiveData;
    }

    @Override
    public DataSource<Integer, Search> create()
    {
        MovieDataSource dataSourceClass = new MovieDataSource(getSearchKey(), context);
        mMutableLiveData.postValue(dataSourceClass);
        return dataSourceClass;
    }

    public String getSearchKey()
    {
        return mSearchKey;
    }

    public void setSearchKey(String mSearchKey)
    {
        this.mSearchKey = mSearchKey;
    }
}
