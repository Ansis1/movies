package ru.igor.movies;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface ApiService {
    String X_API_KEY = "X-API-KEY:GBKGD9T-CWK49HW-QS5WDW6-GBQW6JB";
    String ACCEPT_TYPE = "accept: application/json";

    @GET("movie?limit=20&sortField=votes.kp&sortType=-1&type=movie&rating.kp=7-10")
    // @GET("movie?limit=100&sortField=votes.kp&sortType=1&type=movie&rating.kp=4-8")
    @Headers({
            ACCEPT_TYPE,
            X_API_KEY})
    Single<MovieResponse> loadMovies(
            @Query("page") int page
    );
}
