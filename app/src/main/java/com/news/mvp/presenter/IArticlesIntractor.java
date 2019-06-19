package com.news.mvp.presenter;

import com.news.mvp.model.ArticlesModel;



public interface IArticlesIntractor {

    void requestDataFromServer( OnFinishedListener onFinishedListener);

    interface OnFinishedListener {
        void onFinished(ArticlesModel articlesModel);

        void onFailure(Throwable error);
    }
}
