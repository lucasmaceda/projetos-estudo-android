package com.example.exemplo_retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PostService {

    @GET("posts")
    Call<List<PostEntity>> list();

}
