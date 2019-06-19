package com.news.mvp;

import com.news.mvp.model.ArticlesModel;
import com.news.mvp.network.GetService;
import com.news.mvp.presenter.ArticlesIntractor;
import com.news.mvp.presenter.ArticlesPresenter;
import com.news.mvp.view.IArticlesView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ArticlePresenterTest {

    @Mock
    GetService service;

    @Mock
    Call<ArticlesModel> mockedCall;

    @Mock
    ArticlesModel articlesModel;

    @Mock
    IArticlesView iArticlesView;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }


    @Test
    public void testApiResponse() {

        Mockito.doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Callback<ArticlesModel> callback = invocation.getArgument(0);
                callback.onResponse(mockedCall, Response.success(articlesModel));
                return null;
            }
        }).when(mockedCall).enqueue(any(Callback.class));

    }

    @Test
    public void fetchValidData() {
        Mockito.when(service.getArticlesList("filename", "key")).thenReturn(mockedCall);
        ArticlesPresenter articlesPresenter = new ArticlesPresenter(iArticlesView, new ArticlesIntractor());
        articlesPresenter.requestDataFromServer();
    }
}