package dev.meetup.mooresville.meetupmovies;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;


class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder> {

    private List<Movie> mMovies;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("MMM d, yyyy");

    MovieAdapter(List<Movie> movies) {
        mMovies = movies;
    }

    @Override
    public MovieAdapter.MovieHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.recyclerview_item_row, parent, false);
        MovieHolder holder = new MovieHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(MovieAdapter.MovieHolder holder, int position) {
        final Movie movie = mMovies.get(position);

        holder.mTitleTextView.setText(movie.getTitle());
        holder.mVoteAverageTextView.setText(getRating(movie));
        holder.mReleaseDateTextView.setText(movie.getReleaseDate());
        holder.mOverviewTextView.setText(movie.getOverview());
    }

    private String getRating(Movie movie) {
        return String.format(Locale.getDefault(), "Rating: %.1f", movie.getVoteAverage());
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    public void updateMovies(List<Movie> movies) {
        mMovies = movies;
        notifyDataSetChanged();
    }

    class MovieHolder extends RecyclerView.ViewHolder {

        TextView mTitleTextView;
        TextView mVoteAverageTextView;
        TextView mReleaseDateTextView;
        TextView mOverviewTextView;
        View mLayout;

        public MovieHolder(View itemView) {
            super(itemView);
            mLayout = itemView;
            mTitleTextView = itemView.findViewById(R.id.titleTextView);
            mVoteAverageTextView = itemView.findViewById(R.id.voteAverageTextView);
            mReleaseDateTextView = itemView.findViewById(R.id.releaseDateTextView);
            mOverviewTextView = itemView.findViewById(R.id.overviewTextView);
        }
    }
}
