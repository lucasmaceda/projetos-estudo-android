package com.example.exemplo_retrofit;

import com.google.gson.annotations.SerializedName;

public class PostEntity {

    @SerializedName("userId")
    int userId = 0;

    @SerializedName("id")
    int id = 0;

    @SerializedName("title")
    String title = "";

    @SerializedName("body")
    String body = "";

}

//{
//    "userId": "1",
//    "id": "1",
//    "title": "titulo",
//    "body": "teste"
//}