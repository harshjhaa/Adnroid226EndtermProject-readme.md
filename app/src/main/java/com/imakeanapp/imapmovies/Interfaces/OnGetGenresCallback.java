package com.imakeanapp.imapmovies.Interfaces;


import com.imakeanapp.imapmovies.Genre;

import java.util.List;

public interface OnGetGenresCallback {

    void onSuccess(List<Genre> genres);

    void onError();
}
