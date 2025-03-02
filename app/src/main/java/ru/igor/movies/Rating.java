package ru.igor.movies;

import com.google.gson.annotations.SerializedName;
import org.jetbrains.annotations.NotNull;

public class Rating {

    @SerializedName("kp")
    private String kp;

    public Rating(String kp) {
        this.kp = kp;
    }

    public String getKp() {
        return kp;
    }

    @NotNull
    @Override
    public String toString() {
        return "Rating{" +
                "kp='" + kp + '\'' +
                '}';
    }
}
