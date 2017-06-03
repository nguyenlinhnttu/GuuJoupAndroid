package com.lynkmieu.guujoup.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.lynkmieu.guujoup.DetailArticleActivity;
import com.lynkmieu.guujoup.R;
import com.lynkmieu.guujoup.model.Article;

import java.util.ArrayList;

/**
 * Created by LynkMieu on 6/1/2017.
 */

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleHolder> {
    private Activity activity;
    private ArrayList<Article> listAticle;

    public ArticleAdapter(Activity activity, ArrayList<Article> listAticle) {
        this.activity = activity;
        this.listAticle = listAticle;
    }

    @Override
    public ArticleHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.article_item,parent,false);
        return new ArticleHolder(view);
    }

    @Override
    public void onBindViewHolder(final ArticleHolder holder, int position) {
        final Article article = listAticle.get(position);
        holder.tvTitle.setText(article.getTitle());
        Glide.with(activity)
                .load(article.getThumnail())
                .asBitmap()
                .atMost()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .animate(android.R.anim.fade_in)
                .approximate()
                .into(holder.imgThumnal);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               activity.startActivity(new Intent(activity,DetailArticleActivity.class).putExtra("Article",article));
            }
        });
    }

    @Override
    public int getItemCount() {
        return listAticle.size();
    }

    class ArticleHolder extends RecyclerView.ViewHolder{

        private ImageView imgThumnal;
        private TextView tvTitle;
        public ArticleHolder(View itemView) {
            super(itemView);
            imgThumnal = (ImageView) itemView.findViewById(R.id.img_thumbnail);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
        }
    }
}
