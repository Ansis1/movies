package ru.igor.movies;

import android.app.Application;
import android.util.Log;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MainViewModel extends AndroidViewModel {
    private static final String LOG_TAG = "MainViewModell";
    private final MutableLiveData<List<Movie>> moviesMLD = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>(false);
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private int page = 1;

    public MainViewModel(@NotNull Application application) {
        super(application);
        loadMovies(); //будет вызываться только при создании активити, а не при повороте экрана.
    }

    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public LiveData<List<Movie>> getMoviesMLD() {
        return moviesMLD;
    }

    public void loadMovies() {

        Boolean loading = isLoading.getValue();
        if (loading != null && loading) {
            return;
        }
        Disposable disposable = ApiFactory.apiService.loadMovies(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Throwable {
                        isLoading.setValue(true);
                    }
                })
                .doAfterTerminate(new Action() {
                    @Override
                    public void run() throws Throwable {
                        isLoading.setValue(false);
                    }
                })
                .subscribe(new Consumer<MovieResponse>() {
                    @Override
                    public void accept(MovieResponse movieResponse) throws Throwable {
                        List<Movie> loadedMovies = moviesMLD.getValue();
                        if (loadedMovies != null) {
                            loadedMovies.addAll(movieResponse.getMovies());
                            moviesMLD.setValue(loadedMovies); //Исп. дополненную коллекцию
                        } else {
                            moviesMLD.setValue(movieResponse.getMovies());
                        }
                        // Log.d(LOG_TAG, "Loaded - " + String.valueOf(page));
                        page++;
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        Log.d(LOG_TAG, throwable.toString());

                    }
                });
        compositeDisposable.add(disposable);

    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}
