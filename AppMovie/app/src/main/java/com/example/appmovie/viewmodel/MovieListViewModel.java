package com.example.appmovie.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.appmovie.models.MovieModel;
import com.example.appmovie.repositories.MovieRepository;

import java.util.List;

public class MovieListViewModel extends ViewModel {

    private MovieRepository movieRepository;

    public MovieListViewModel()
    {
        movieRepository = MovieRepository.getInstance();
    }

    public LiveData<List<MovieModel>> getMovies()
    {
        return movieRepository.getMovies();
    }

    public LiveData<List<MovieModel>> getMoviesPopulars()
    {
        return movieRepository.getMoviesPopulars();
    }

    public void searchMovieApi(String query, int pageNumber)
    {
        movieRepository.searchMovieApi(query, pageNumber);
    }

    public void searchMoviePopularsApi(int pageNumber)
    {
        movieRepository.searchMoviePopularsApi(pageNumber);
    }

    public void searchNextPage() {
        movieRepository.searchNextPage();
    }
}
