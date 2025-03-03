package ru.igor.movies;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG = "MainActivity";

    private MainViewModel viewmodel;
    private RecyclerView recyclerViewMovies;
    private ProgressBar progressbarloading;
    private MoviesAdapter moviesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        moviesAdapter = new MoviesAdapter();
        recyclerViewMovies.setAdapter(moviesAdapter);
        recyclerViewMovies.setLayoutManager(new GridLayoutManager(this, 2));
        viewmodel = new ViewModelProvider(this).get(MainViewModel.class);
        viewmodel.getMoviesMLD().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                moviesAdapter.setMovies(movies);
                //Log.d(LOG_TAG, movies.toString());

            }
        });

        viewmodel.getIsLoading().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isLoading) {
                progressbarloading.setVisibility(isLoading ? View.VISIBLE : View.GONE);
            }
        });
        moviesAdapter.setOnReachEndListener(new MoviesAdapter.OnReachEndListener() {
            @Override
            public void onReachEnd() {
                viewmodel.loadMovies();
            }
        });
        moviesAdapter.setOnMovieClickListener(new MoviesAdapter.OnMovieClickListener() {
            @Override
            public void onMovieClick(Movie movie) {
                Intent intent = MovieDetailActivity.newIntent(getApplication(), movie);
                startActivity(intent);
            }
        });
    }

    private void initViews() {
        recyclerViewMovies = findViewById(R.id.recyclerViewMovies);
        progressbarloading = findViewById(R.id.progressbarloading);
    }

}