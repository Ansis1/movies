package ru.igor.movies;

import android.app.Application;
import android.util.Log;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class FavouriteMoviesViewModel extends AndroidViewModel {
    private static final String LOG_TAG = "FavouriteMoviesViewModel";
    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>(false);
    private final MovieDao movieDao;

    public FavouriteMoviesViewModel(@NotNull Application application) {
        super(application);
        movieDao = MovieDatabase.getInstance(application).movieDao();
    }

    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public LiveData<List<Movie>> getFavouritesMovies() {
        return movieDao.getAllFavouriteMovies();
    }


}
