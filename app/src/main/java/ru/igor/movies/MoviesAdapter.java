package ru.igor.movies;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import org.jetbrains.annotations.NotNull;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {
    private static final String LOG_TAG = "LOG_MoviesAdapter";

    private List<Movie> movies = new ArrayList<>();

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }

    @NonNull
    @NotNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.movie_item,
                viewGroup,
                false
        );
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MovieViewHolder movieViewHolder, int i) {

        Movie movie = movies.get(i);
        Log.d(LOG_TAG, movies.toString());

        Glide.with(movieViewHolder.itemView)
                .load(movie.getPoster().getUrl())
                .into(movieViewHolder.imageviewPoster);
        movieViewHolder.textViewRating.setText(movie.getRating().getKp());
    }

    @Override
    public int getItemCount() {
        return movies.size();
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
