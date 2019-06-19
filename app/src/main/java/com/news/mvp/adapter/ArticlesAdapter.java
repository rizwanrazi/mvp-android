package com.news.mvp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.news.mvp.R;
import com.news.mvp.model.ResultsModel;
import com.news.mvp.presenter.IRecyclerItemClickListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;


public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.MyViewHolder> {

    private List<ResultsModel> resultsModelList;
    Context context;
    IRecyclerItemClickListener iRecyclerItemClickListener;

    public ArticlesAdapter(List<ResultsModel> resultsModelList, IRecyclerItemClickListener iRecyclerItemClickListener) {
        this.resultsModelList = resultsModelList;
        this.iRecyclerItemClickListener = iRecyclerItemClickListener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_title)
        TextView tv_title;

        @BindView(R.id.tv_author)
        TextView tv_author;

        @BindView(R.id.tv_date)
        TextView tv_date;

        @BindView(R.id.image_circle)
        CircleImageView img_circle;


        public MyViewHolder(@NonNull View itemView, Context context) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public int getItemCount() {
        return resultsModelList.size();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View rowView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.article_recycler_row, viewGroup, false);
        return new MyViewHolder(rowView, viewGroup.getContext());
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int i) {
        final ResultsModel resultsModel = resultsModelList.get(i);
        myViewHolder.tv_title.setText(resultsModel.getTitle());
        myViewHolder.tv_author.setText(resultsModel.getByline());
        myViewHolder.tv_date.setText(resultsModel.getPublishedDate());
        String url = resultsModel.getMedia().get(0).getMediaMetadata().get(1).getUrl();
        Glide.with(context).load(url).into(myViewHolder.img_circle);

        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iRecyclerItemClickListener.onItemClick(i, resultsModel);
            }
        });


    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        context = recyclerView.getContext();
    }
}
