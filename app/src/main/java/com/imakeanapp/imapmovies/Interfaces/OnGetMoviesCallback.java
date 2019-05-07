package com.imakeanapp.imapmovies.Interfaces;


import com.imakeanapp.imapmovies.Movie;

import java.util.List;

public interface OnGetMoviesCallback {

    void onSuccess(int page, List<Movie> movies);

    void onError();
}
