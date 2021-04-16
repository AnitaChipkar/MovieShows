package com.anitachipkar.movieshows.ui.list;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.anitachipkar.movieshows.IMovieClickListner;
import com.anitachipkar.movieshows.R;
import com.anitachipkar.movieshows.entities.Search;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class MovieListAdapter extends PagedListAdapter<Search, MovieListAdapter.ShowListViewHolder>
{

    private static final String TAG = MovieListAdapter.class.getSimpleName();
    // View Types
    IMovieClickListner mListner;
    private Context mContext;

    public MovieListAdapter(Context mContext, IMovieClickListner listner)
    {
        super(DIFF_CALLBACK);
        mListner = listner;
        this.mContext = mContext;
    }


    @NonNull
    @Override
    public ShowListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View viewItem = inflater.inflate(R.layout.item_list_layout, parent, false);
        ShowListViewHolder viewHolder = new ShowListViewHolder(viewItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ShowListViewHolder holder, int position)
    {
        holder.bind(getItem(position));
    }

    private static DiffUtil.ItemCallback<Search> DIFF_CALLBACK =
        new DiffUtil.ItemCallback<Search>()
        {
            @Override
            public boolean areItemsTheSame(Search oldItem, Search newItem)
            {
                return oldItem.getImdbID() == newItem.getImdbID();
            }

            @Override
            public boolean areContentsTheSame(Search oldItem, Search newItem)
            {
                return oldItem.equals(newItem);
            }
        };


    class ShowListViewHolder extends RecyclerView.ViewHolder
    {
        ImageView imageView;
        TextView showNameView;
        TextView showYearView;
        ImageButton button;

        public ShowListViewHolder(@NonNull View itemView)
        {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_movie);
            showNameView = itemView.findViewById(R.id.tv_movie_name);
            showYearView = itemView.findViewById(R.id.tv_movie_year);
        }

        void bind(final Search search)
        {
            showYearView.setText(search.getYear());
            showNameView.setText(search.getTitle());
            Picasso.with(mContext)
                .load(search.getPoster())
                .placeholder(R.drawable.placeholder_background)
                .error(R.drawable.placeholder_background)
                .fit()
                .noFade()
                .into(imageView, new Callback()
                {
                    @Override
                    public void onSuccess()
                    {
                        //TODO Anuj
                    }

                    @Override
                    public void onError()
                    {
                        //TODO Anuj
                        Log.i(TAG, "Error while loading image");
                    }
                });

            itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    mListner.onShowClick(search);
                }
            });


        }
    }

}
