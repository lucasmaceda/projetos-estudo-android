package com.example.appmovie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.appmovie.adapters.MovieRecyclerView;
import com.example.appmovie.adapters.OnMovieListener;
import com.example.appmovie.models.MovieModel;
import com.example.appmovie.request.Service;
import com.example.appmovie.response.MovieSearchResponse;
import com.example.appmovie.utils.Credentials;
import com.example.appmovie.utils.MovieApi;
import com.example.appmovie.viewmodel.MovieListViewModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieListActivity extends AppCompatActivity implements OnMovieListener {

    private RecyclerView recyclerView;
    private MovieRecyclerView movieRecyclerAdapter;

    private MovieListViewModel movieListViewModel;

    boolean isPopular = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SetupSearchView();

        recyclerView = findViewById(R.id.recyclerView);
        movieListViewModel = new ViewModelProvider(this).get(MovieListViewModel.class);

        ConfigureRecyclerView();
        ObserverAnyChange();
        ObserverPopularMovies();

        movieListViewModel.searchMoviePopularsApi(1);
    }

    private void ObserverPopularMovies() {
        movieListViewModel.getMoviesPopulars().observe(this, new Observer<List<MovieModel>>() {
            @Override
            public void onChanged(List<MovieModel> movieModels) {
                // Observing for any data change
                if (movieModels != null)
                {
                    for (MovieModel movieModel: movieModels)
                    {
                        Log.v("Tag", "onChanged: "+movieModel.getTitle());
                        movieRecyclerAdapter.setmMovies(movieModels);
                    }
                }
            }
        });
    }

    private void ObserverAnyChange()
    {
        movieListViewModel.getMovies().observe(this, new Observer<List<MovieModel>>() {
            @Override
            public void onChanged(List<MovieModel> movieModels) {
                // Observing for any data change
                if (movieModels != null)
                {
                    for (MovieModel movieModel: movieModels)
                    {
                        Log.v("Tag", "onChanged: "+movieModel.getTitle());
                        movieRecyclerAdapter.setmMovies(movieModels);
                    }
                }
            }
        });
    }

    private void ConfigureRecyclerView()
    {
        movieRecyclerAdapter = new MovieRecyclerView(this);

        recyclerView.setAdapter(movieRecyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                if(!recyclerView.canScrollVertically(1)) {
                    movieListViewModel.searchNextPage();
                }
            }
        });

    }

    @Override
    public void onMovieClick(int position) {
        Intent intent = new Intent(this, MovieDetailsActivity.class);
        intent.putExtra("movie", movieRecyclerAdapter.getSelectedMovie(position));
        startActivity(intent);
    }

    private void SetupSearchView() {
        final SearchView searchView = findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                movieListViewModel.searchMovieApi(
                        query,
                        1
                );

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        searchView.setOnSearchClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                isPopular = false;
            }
        });
    }

    @Override
    public void onCategoryClick(String category) {

    }

    private void GetRetrofitResponse()
    {
        MovieApi movieApi = Service.getMovieApi();

        Call<MovieSearchResponse> responseCall = movieApi
                .searchMovie(Credentials.API_KEY, "Jack Reacher", 1);

        responseCall.enqueue(new Callback<MovieSearchResponse>() {
            @Override
            public void onResponse(Call<MovieSearchResponse> call,
                                   Response<MovieSearchResponse> response)
            {
                if(response.code() == 200)
                {
                    Log.v("Tag", "The response: " + response.body().toString());

                    List<MovieModel> movies = new ArrayList<>(response.body().getMovies());

                    for(MovieModel movie: movies)
                    {
                        Log.v("Tag", "The release date: " + movie.getRelease_date());
                    }
                }
                else
                {
                    try
                    {
                        Log.v("Tag", "Error " + response.errorBody().string());
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieSearchResponse> call, Throwable t)
            {

            }
        });

    }

    private void GetRetrofitResponseAccordingToID()
    {
        MovieApi movieApi = Service.getMovieApi();
        Call<MovieModel> responseCall = movieApi
                .getMovie(550, Credentials.API_KEY);

        responseCall.enqueue(new Callback<MovieModel>()
        {
            @Override
            public void onResponse(Call<MovieModel> call, Response<MovieModel> response)
            {
                if(response.code() == 200)
                {
                    MovieModel movie = response.body();
                    Log.v("Tag", "The response: " + movie.getTitle());
                }
                else
                {
                    try
                    {
                        Log.v("Tag", "Error " + response.errorBody().string());
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void onFailure(Call<MovieModel> call, Throwable t)
            {

            }
        });
    }
}