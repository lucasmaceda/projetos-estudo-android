package com.example.appmovie.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.appmovie.models.MovieModel;
import com.example.appmovie.request.MovieApiClient;

import java.util.List;

public class MovieRepository {

    private static MovieRepository instance;

    private MovieApiClient movieApiClient;

    private String mQuery;
    private int mPageNumber;

    public static MovieRepository getInstance()
    {
        if (instance == null)
        {
            instance = new MovieRepository();
        }

        return instance;
    }

    private MovieRepository()
    {
        movieApiClient = MovieApiClient.getInstance();
    }

    public LiveData<List<MovieModel>> getMovies()
    {
        return movieApiClient.getMovies();
    }
    public LiveData<List<MovieModel>> getMoviesPopulars()
    {
        return movieApiClient.getMoviesPopulars();
    }


    public void searchMovieApi(String query, int pageNumber)
    {
        mQuery = query;
        mPageNumber = pageNumber;
        movieApiClient.searchMoviesApi(query, pageNumber);
    }

    public void searchMoviePopularsApi(int pageNumber)
    {
        mPageNumber = pageNumber;
        movieApiClient.searchMoviesPopularsApi(pageNumber);
    }

    public void searchNextPage() {
        searchMovieApi(mQuery, mPageNumber+1);
    }
}
