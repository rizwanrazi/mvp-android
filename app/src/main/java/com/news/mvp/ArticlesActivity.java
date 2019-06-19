package com.news.mvp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.news.mvp.adapter.ArticlesAdapter;
import com.news.mvp.presenter.IRecyclerItemClickListener;
import com.news.mvp.view.IArticlesView;
import com.news.mvp.model.ResultsModel;
import com.news.mvp.presenter.ArticlesIntractor;
import com.news.mvp.presenter.IArticlesPresenter;
import com.news.mvp.presenter.ArticlesPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ArticlesActivity extends AppCompatActivity implements IArticlesView, IRecyclerItemClickListener {

    IArticlesPresenter IArticlesPresenter;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articles);
        ButterKnife.bind(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        IArticlesPresenter = new ArticlesPresenter(this, new ArticlesIntractor());
        IArticlesPresenter.requestDataFromServer();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setDataToRecyclerView(List<ResultsModel> resultsModelList) {
        ArticlesAdapter articlesAdapter = new ArticlesAdapter(resultsModelList, this);
        recyclerView.setAdapter(articlesAdapter);
    }

    @Override
    public void onResponseFailure(Throwable error) {
        Toast.makeText(this, error.getMessage(), Toast.LENGTH_LONG).show();
    }


    @Override
    public void onItemClick(int posituon, ResultsModel resultsModel) {
        Toast.makeText(this, "Item Number " + posituon + " Clicked"+resultsModel.getByline(), Toast.LENGTH_LONG).show();
    }
}
