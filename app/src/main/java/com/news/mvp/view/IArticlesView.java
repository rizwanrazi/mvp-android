package com.news.mvp.view;


import com.news.mvp.model.ResultsModel;

import java.util.List;

public interface IArticlesView {

    void showProgress();

    void setDataToRecyclerView(List<ResultsModel> noticeArrayList);

    void onResponseFailure(Throwable error);

    void hideProgress();

}
