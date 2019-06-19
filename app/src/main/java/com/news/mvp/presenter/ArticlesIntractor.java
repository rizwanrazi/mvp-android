package com.news.mvp.presenter;


import com.news.mvp.model.ArticlesModel;
import com.news.mvp.network.GetService;
import com.news.mvp.network.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ArticlesIntractor implements IArticlesIntractor {

    @Override
    public void requestDataFromServer( final OnFinishedListener onFinishedListener) {

        GetService getService = RetrofitInstance.getRetrofitInstance().create(GetService.class);
        Call<ArticlesModel> articlesModelCall = getService.getArticlesList("7", "gjC3AuPLFGkHbGzaMXfqIcxNBrOkGjHG");
        articlesModelCall.enqueue(new Callback<ArticlesModel>() {
            @Override
            public void onResponse(Call<ArticlesModel> call, Response<ArticlesModel> response) {
                onFinishedListener.onFinished(response.body());
            }
            @Override
            public void onFailure(Call<ArticlesModel> call, Throwable t) {
                onFinishedListener.onFailure(t);
            }
        });
    }
}
