package ru.igor.movies;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Review implements Serializable { //Отзывы

    @SerializedName("author")
    private String author;
    @SerializedName("review")
    private String text;
    @SerializedName("type")
    private String type;

    public Review(String author, String text, String type) {
        this.author = author;
        this.text = text;
        this.type = type;
    }

    public String getAuthor() {
        return author;
    }

    public String getText() {
        return text;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Review{" +
                "author='" + author + '\'' +
                ", text='" + text + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
