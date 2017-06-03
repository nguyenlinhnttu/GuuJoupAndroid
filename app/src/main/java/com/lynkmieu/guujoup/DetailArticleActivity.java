package com.lynkmieu.guujoup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.lynkmieu.guujoup.model.Article;

public class DetailArticleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_article);
        Article article = (Article) getIntent().getSerializableExtra("Article");
    }
}
