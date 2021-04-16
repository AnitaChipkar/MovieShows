package com.anitachipkar.movieshows.ui.list;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.LinearLayout;

import com.anitachipkar.movieshows.IMovieClickListner;
import com.anitachipkar.movieshows.R;
import com.anitachipkar.movieshows.entities.Search;
import com.anitachipkar.movieshows.AppConstants;
import com.anitachipkar.movieshows.ui.detail.MovieDetailsActivity;

import java.util.concurrent.Executor;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView mRecyclerView;
    private RecyclerView mBookmarkView;
  private MovieListAdapter movieListAdapter;
    private LinearLayout mBookMarkLinearLayout;

    String mSearchKey = AppConstants.DEFAULT_SEARCH;
    private MovieViewModel movieViewModel;
    private Executor mExecutor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initResultRecylerView();
        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
        observe();
        loadDefaultSearch();
    }

    private void observe()
    {
        movieViewModel.getmShowSearchLiveData().observe(this, new Observer<PagedList<Search>>()
        {
            @Override
            public void onChanged(PagedList<Search> showSearchDetails)
            {
                Log.i(TAG, "On Changed  list size is " + (showSearchDetails!=null?showSearchDetails.size():0));
                movieListAdapter.submitList(showSearchDetails);
            }
        });
    }



    private void loadDefaultSearch()
    {
        refreshData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {

        getMenuInflater().inflate(R.menu.menu_main, menu);

        SearchManager manager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        SearchView search = (SearchView) menu.findItem(R.id.action_search).getActionView();

        search.setSearchableInfo(manager.getSearchableInfo(getComponentName()));

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener()
        {

            @Override
            public boolean onQueryTextSubmit(String query)
            {
                Log.i(TAG, "Text Submitted " + query);
                mSearchKey = query;
                refreshData();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query)
            {

                // DO Nothing
                return true;

            }

        });

        return true;

    }


    private void initResultRecylerView()
    {
        mRecyclerView = findViewById(R.id.rv_movie);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        movieListAdapter = new MovieListAdapter(this, showClickListener);
        mRecyclerView.setAdapter(movieListAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }


    private IMovieClickListner showClickListener = new IMovieClickListner()
    {
        @Override
        public void onShowClick(Search search)
        {

            Log.i(TAG, "Show clicked " + search.getTitle());
            startDetailActivity(search.getImdbID());

        }

    };

    /**
     * Start Detail Activity
     *
     * @param imdbID
     */
    private void startDetailActivity(String imdbID)
    {
        Intent intent = new Intent(this, MovieDetailsActivity.class);
        intent.putExtra(AppConstants.IMDB_ID, imdbID);
        startActivity(intent);
    }

    public void refreshData()
    {
        Log.i(TAG, "Search key is "+mSearchKey);
        movieViewModel.searchShow(mSearchKey, mExecutor);

    }


}
