package com.news.mvp.presenter;

import com.news.mvp.view.IArticlesView;
import com.news.mvp.model.ArticlesModel;


public class ArticlesPresenter implements IArticlesPresenter {


    IArticlesView articlesView;
    IArticlesIntractor articlesIntractor;

    public ArticlesPresenter(IArticlesView articlesView, IArticlesIntractor articlesIntractor) {
        this.articlesView = articlesView;
        this.articlesIntractor = articlesIntractor;
    }

    @Override
    public void requestDataFromServer() {

        articlesView.showProgress();

        articlesIntractor.requestDataFromServer( new IArticlesIntractor.OnFinishedListener() {
            @Override
            public void onFinished(ArticlesModel articlesModel) {
                articlesView.hideProgress();
                articlesView.setDataToRecyclerView(articlesModel.getResults());
            }

            @Override
            public void onFailure(Throwable error) {
                articlesView.hideProgress();
                articlesView.onResponseFailure(error);
            }
        });

    }
}
