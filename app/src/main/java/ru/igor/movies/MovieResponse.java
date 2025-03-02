package ru.igor.movies;

import androidx.annotation.NonNull;
import com.google.gson.annotations.SerializedName;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class MovieResponse {

    @SerializedName("docs")
    private List<Movie> movies;

    public MovieResponse(List<Movie> movies) {
        this.movies = movies;
    }

    public List<Movie> getMovies() {
        return new ArrayList<>(movies);
    }


    @NonNull
    @NotNull
    @Override
    public String toString() {
        return String.format("MovieResponse{ movies= %s }", movies);
    }
}
