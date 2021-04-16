package com.anitachipkar.movieshows.ui.list;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.anitachipkar.movieshows.entities.Search;
import com.anitachipkar.movieshows.repository.MovieDataSourceFactory;
import com.anitachipkar.movieshows.repository.MovieRepository;

import java.util.concurrent.Executor;

public class MovieViewModel extends AndroidViewModel
{
    private MovieRepository mShowRepository;
    private MovieDataSourceFactory mShowDataSourceFactory;
    private LiveData<PagedList<Search>> mShowSearchLiveData;

    public MovieViewModel(@NonNull Application application)
    {
        super(application);
        mShowRepository = MovieRepository.getInstance(application);
        mShowDataSourceFactory = new MovieDataSourceFactory(mShowRepository, application);
        initializePaging();
    }

    private void initializePaging() {

        PagedList.Config pagedListConfig =
            new PagedList.Config.Builder()
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(10)
                .setPageSize(10).build();

        mShowSearchLiveData = new LivePagedListBuilder<>(mShowDataSourceFactory, pagedListConfig)
            .build();

    }


    public void searchShow(String mSearchKey, Executor executor)
    {
        PagedList.Config pagedListConfig =
            new PagedList.Config.Builder()
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(10)
                .setPageSize(10).build();
        mShowDataSourceFactory.setSearchKey(mSearchKey);

        mShowSearchLiveData = new LivePagedListBuilder<>(mShowDataSourceFactory, pagedListConfig)
            .build();
        // Build PagedList
    }

    public LiveData<PagedList<Search>> getmShowSearchLiveData()
    {
        return mShowSearchLiveData;
    }

}
