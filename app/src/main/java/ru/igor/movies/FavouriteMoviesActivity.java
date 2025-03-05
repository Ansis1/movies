package ru.igor.movies;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.ProgressBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FavouriteMoviesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_movies);

        ProgressBar progressbarloading = findViewById(R.id.progressbarloadingfavMov);
        RecyclerView recyclerViewFavouriteMovies = findViewById(R.id.recyclerViewFavouriteMovies);
        MoviesAdapter favouriteMoviesAdapter = new MoviesAdapter();

        recyclerViewFavouriteMovies.setAdapter(favouriteMoviesAdapter);
        recyclerViewFavouriteMovies.setLayoutManager(new GridLayoutManager(this, 2)); //колонки в RV

        FavouriteMoviesViewModel viewmodel = new ViewModelProvider(this).get(FavouriteMoviesViewModel.class);

        viewmodel.getFavouritesMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                favouriteMoviesAdapter.setMovies(movies);
                //Log.d(LOG_TAG, movies.toString());
            }
        });

        viewmodel.getIsLoading().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isLoad) {
                progressbarloading.setVisibility(isLoad ? View.VISIBLE : View.GONE);
            }
        });

        favouriteMoviesAdapter.setOnMovieClickListener(new MoviesAdapter.OnMovieClickListener() {
            @Override
            public void onMovieClick(Movie movie) {
                Intent intent = MovieDetailActivity.newIntent(getApplication(), movie);
                startActivity(intent);
            }
        });
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, FavouriteMoviesActivity.class);
    }
}