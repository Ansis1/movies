package ru.igor.movies;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import org.jetbrains.annotations.NotNull;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {

    private OnReachEndListener onReachEndListener;
    private OnMovieClickListener onMovieClickListener;
    private static final String LOG_TAG = "LOG_MoviesAdapter";

    private List<Movie> movies = new ArrayList<>();

    public List<Movie> getMovies() {
        return movies;
    }

    public void setOnReachEndListener(OnReachEndListener onReachEndListener) {
        this.onReachEndListener = onReachEndListener;
    }

    public void setOnMovieClickListener(OnMovieClickListener onMovieClickListener) {
        this.onMovieClickListener = onMovieClickListener;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }

    @NonNull
    @NotNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup viewGroup, int i) {
        // Log.d(LOG_TAG, "onCreateViewHolder()" + i);
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.movie_item,
                viewGroup,
                false
        );
        final MovieViewHolder holder = new MovieViewHolder(view);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int position = holder.getAdapterPosition();
                onMovieClickListener.onMovieClick(movies.get(position));
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MovieViewHolder movieViewHolder, int i) {
        // Log.d(LOG_TAG, "onBindViewHolder()" + i);

        Movie movie = movies.get(i);
        Log.d(LOG_TAG, movies.toString());

        Glide.with(movieViewHolder.itemView)
                .load(movie.getPoster().getUrl())
                .error(R.drawable.ic_launcher_foreground)
                .into(movieViewHolder.imageviewPoster);
        double rating = movie.getRating().getKp();

        int backgroundId;
        if (rating > 7) {
            backgroundId = R.drawable.circle_green;
        } else if (rating > 5) {
            backgroundId = R.drawable.circle_orange;
        } else {
            backgroundId = R.drawable.circle_red;
        }
        Drawable background = ContextCompat.getDrawable(movieViewHolder.itemView.getContext(), backgroundId);
        String sRating = String.format("%.1f", rating).replace(",", ".");
        movieViewHolder.textViewRating.setBackground(background);
        movieViewHolder.textViewRating.setText(sRating);

        if (i >= (movies.size() - 7) && onReachEndListener != null) {
            onReachEndListener.onReachEnd();
        }
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    interface OnReachEndListener {

        void onReachEnd();

    }

    interface OnMovieClickListener {
        void onMovieClick(Movie movie);
    }

    static class MovieViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imageviewPoster;
        private final TextView textViewRating;

        public MovieViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            imageviewPoster = itemView.findViewById(R.id.imageviewPoster);
            textViewRating = itemView.findViewById(R.id.textViewRating);
        }
    }
}
