package com.imakeanapp.imapmovies;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MoviesResponse {

    @SerializedName("page")
    @Expose
    private int page;

    @SerializedName("results")
    @Expose
    private List<Movie> movies;


    public int getPage() {
        return page;
    }

    public List<Movie> getMovies() {
        return movies;
    }
}
