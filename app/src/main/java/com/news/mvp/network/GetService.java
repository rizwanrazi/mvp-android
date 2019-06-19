package com.news.mvp.network;

import com.news.mvp.model.ArticlesModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface GetService {

    @GET("mostpopular/v2/mostviewed/all-sections/{fileName}.json")
    Call<ArticlesModel> getArticlesList(@Path("fileName") String fileName, @Query("api-key") String apiKey);
}
