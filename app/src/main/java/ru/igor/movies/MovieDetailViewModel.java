package ru.igor.movies;

import android.app.Application;
import android.util.Log;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MovieDetailViewModel extends AndroidViewModel {

    private static final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private static final String LOG_TAG = "MovieDetailViewModel";
    private MutableLiveData<List<Trailer>> trailers = new MutableLiveData<>();

    public LiveData<List<Trailer>> getTrailers() {
        return trailers;
    }

    public MovieDetailViewModel(@NotNull Application application) {
        super(application);
    }

    public void loadTrailers(int id) {
        Log.v(LOG_TAG, "init loadTrailers() with id=" + id);

        Disposable disposable = ApiFactory.apiService.loadTrailers(id)
                .subscribeOn(Schedulers.io())
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        Log.d(LOG_TAG, "loadTrailers - doOnError..." + throwable.toString());

                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<TrailerResponse, List<Trailer>>() { //преобразование типа
                    @Override
                    public List<Trailer> apply(TrailerResponse trailerResponse) throws Throwable {
                        Log.d(LOG_TAG, "apply map is null= " + (trailerResponse == null));

                        return trailerResponse.getTrailersList().getTrailers();
                    }
                })

                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Throwable {
                        Log.d(LOG_TAG, " loadTrailers - Starting load trailers...");
                    }
                })
                .subscribe(new Consumer<List<Trailer>>() {
                    @Override
                    public void accept(List<Trailer> trailerList) {
                        trailers.setValue(trailerList);
                        Log.d(LOG_TAG, "loadTrailers - Success - trailers get.");

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        Log.d(LOG_TAG, "loadTrailers - ERROR" + throwable.toString());

                    }
                });

        compositeDisposable.add(disposable);

    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d(LOG_TAG, "Clear view model detail");
        compositeDisposable.dispose();
    }
}
