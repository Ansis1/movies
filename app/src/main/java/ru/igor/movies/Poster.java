package ru.igor.movies;

import com.google.gson.annotations.SerializedName;
import org.jetbrains.annotations.NotNull;

public class Poster {
    @SerializedName("url")
    private String url;

    public Poster(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    @NotNull
    @Override
    public String toString() {
        return "Poster{" +
                "url='" + url + '\'' +
                '}';
    }
}
