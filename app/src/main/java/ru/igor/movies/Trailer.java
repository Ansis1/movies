package ru.igor.movies;

import androidx.annotation.NonNull;
import com.google.gson.annotations.SerializedName;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

public class Trailer implements Serializable {

    @SerializedName("name")
    private String name;

    @SerializedName("url")
    private String url;

    public Trailer(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    @NonNull
    @NotNull
    @Override
    public String toString() {
        return "Trailer{ name=" + name + "url=" + url;
    }
}
