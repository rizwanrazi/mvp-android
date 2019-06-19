package com.news.mvp.presenter;

import com.news.mvp.model.ResultsModel;


public interface IRecyclerItemClickListener {
    void onItemClick(int position, ResultsModel resultsModel);
}
