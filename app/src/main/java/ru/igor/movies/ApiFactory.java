package ru.igor.movies;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiFactory {
    private final static String BASE_URL = "https://api.kinopoisk.dev/v1.4/";
    private static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build();


    //упрощенная реализация Singleton (когда для получения объекта не нужно передавать параметры
    public static final ApiService apiService = retrofit.create(ApiService.class);


}
