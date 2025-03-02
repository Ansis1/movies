package ru.igor.movies;

import com.google.gson.annotations.SerializedName;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

public class Rating implements Serializable {

    @SerializedName("kp")
    private Double kp;

    public Rating(Double kp) {
        this.kp = kp;
    }

    public Double getKp() {
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
