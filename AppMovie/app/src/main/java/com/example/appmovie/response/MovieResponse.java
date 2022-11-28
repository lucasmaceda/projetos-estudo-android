package com.example.appmovie.response;

import com.example.appmovie.models.MovieModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

// this class is for single movie request
public class MovieResponse {
    // 1 - finding the Movie Object
    @SerializedName("results")
    @Expose
    private MovieModel movie;

    public MovieModel getMovie() {
        return movie;
    }
}
