package ru.igor.movies;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.google.gson.annotations.SerializedName;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

import static ru.igor.movies.Movie.TABLE_NAME;


@Entity(tableName = TABLE_NAME)
public class Movie implements Serializable {
    @PrimaryKey
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("description")
    private String description;
    @SerializedName("year")
    private int year;
    @SerializedName("poster")
    @Embedded
    private Poster poster;
    @SerializedName("rating")
    @Embedded
    private Rating rating;

    public static final String TABLE_NAME = "favourite_movies";
    public Movie(int id, String name, String description, int year, Poster poster, Rating rating) {
        this.name = name;
        this.rating = rating;
        this.year = year;
        this.poster = poster;
        this.id = id;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    public Poster getPoster() {
        return poster;
    }

    public Rating getRating() {
        return rating;
    }

    public int getYear() {
        return year;
    }

    @NotNull
    @Override
    public String toString() {
        return String.format("Movie{ description= %s,id= %s, name= %s, year= %s, poster= %s, rating= %s}",
                description,
                id,
                name,
                year,
                poster,
                rating);
    }
}
