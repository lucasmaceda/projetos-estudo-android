package com.example.exemplo_retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RetrofitClient restClient = new RetrofitClient();
        PostService service = restClient.createPostService();
        Call<List<PostEntity>> call = service.list();

        call.enqueue(new Callback<List<PostEntity>>() {
            public void onResponse(Call<List<PostEntity>> call, Response<List<PostEntity>> response) {
                response.body();
            }

            public void onFailure(Call<List<PostEntity>> call, Throwable t) {
                // todo deal with the failed network request
            }
        });
    }
}