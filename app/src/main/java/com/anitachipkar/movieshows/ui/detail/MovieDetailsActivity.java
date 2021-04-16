package com.anitachipkar.movieshows.ui.detail;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.anitachipkar.movieshows.R;
import com.anitachipkar.movieshows.entities.MovieDetails;
import com.anitachipkar.movieshows.AppConstants;
import com.anitachipkar.movieshows.Utils;
import com.squareup.picasso.Picasso;

public class MovieDetailsActivity extends AppCompatActivity
{
    private static final String TAG = MovieDetailsActivity.class.getSimpleName();
    String imdbId;
    TextView director;
    TextView genre;
    TextView actors;
    TextView imdbRating;
    TextView name;
    TextView year;
    ImageView imageView;
    private ProgressBar progressBar;
    private MovieDetailViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_details_layout);
        initView();
        imdbId = getIntent().getExtras().getString(AppConstants.IMDB_ID);
        mViewModel = ViewModelProviders.of(this).get(MovieDetailViewModel.class);
        loadShowDetails(imdbId);


    }

    void loadShowDetails(String imdbId)
    {
        if(!Utils.checkInternetConnection(this)){
            showToastMessage("Check your connectivity");
            return;
        }
        showProgress();

        mViewModel.getShowDetails(imdbId).observe(this, new Observer<MovieDetails>()
        {
            @Override
            public void onChanged(MovieDetails movieDetails)
            {
                initUI(movieDetails);
                hideProgress();
            }
        });
    }

    private void initView()
    {

        progressBar = findViewById(R.id.progressBar);
        director = findViewById(R.id.director);
        actors = findViewById(R.id.actors);
        imdbRating = findViewById(R.id.imdbRating);
        genre = findViewById(R.id.genre);
        name = findViewById(R.id.tv_movie_name);
        year = findViewById(R.id.tv_movie_year);
        imageView = findViewById(R.id.iv_movie);

    }


    public void showProgress()
    {
        Log.i(TAG, "Showing progress");
        progressBar.setVisibility(View.VISIBLE);
    }

    public void hideProgress()
    {
        Log.i(TAG, "hiding progress");
        progressBar.setVisibility(View.GONE);
    }

    private void initUI(final MovieDetails showDetails)
    {
        name.setText(showDetails.getTitle());
        year.setText(showDetails.getYear());
        //TODO Remove hardcoded Strings
        director.setText("Director: " + showDetails.getDirector());
        actors.setText("Actors: " + showDetails.getActors());
        imdbRating.setText("IMDB Rating: " + showDetails.getImdbRating());
        genre.setText("Genre: " + showDetails.getGenre());

        Picasso.with(this)
            .load(showDetails.getPoster())
            .placeholder(R.drawable.placeholder_background)
            .error(R.drawable.placeholder_background)
            .fit()
            .noFade()
            .into(imageView);
    }


    public void showToastMessage(String msg)
    {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
    }

}
