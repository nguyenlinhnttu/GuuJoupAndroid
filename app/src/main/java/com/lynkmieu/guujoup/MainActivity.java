package com.lynkmieu.guujoup;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.lynkmieu.guujoup.adapter.ArticleAdapter;
import com.lynkmieu.guujoup.model.Article;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String MY_URL = "https://guu.vn/cat/toc-dep";

    private RecyclerView recycler;
    private ArticleAdapter articleAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recycler = (RecyclerView) findViewById(R.id.recyler_category);
        configRecyclerView();
        new DownloadTask().execute(MY_URL);
    }

    private void configRecyclerView() {
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,3);
        recycler.hasFixedSize();
        recycler.setLayoutManager(layoutManager);
    }

    //Download HTML bằng AsynTask
    private class DownloadTask extends AsyncTask<String, Void, ArrayList<Article>> {

        private static final String TAG = "DownloadTask";

        @Override
        protected ArrayList<Article> doInBackground(String... strings) {
            Document document = null;
            ArrayList<Article> listArticle = new ArrayList<>();
            try {
                document = (Document) Jsoup.connect(strings[0]).get();
                if (document != null) {
                    //Lấy  html có thẻ như sau: div#latest-news > div.row > div.col-md-6 hoặc chỉ cần dùng  div.col-md-6
                    Elements sub = document.select("div#latest-news > div.row > div.col-md-6");
                    for (Element element : sub) {
                        Article article = new Article();
                        Element titleSubject = element.getElementsByTag("h3").first();
                        Element imgSubject = element.getElementsByTag("img").first();
                        Element linkSubject = element.getElementsByTag("a").first();
                        Element descrip = element.getElementsByTag("h4").first();
                        //Parse to model
                        if (titleSubject != null) {
                            String title = titleSubject.text();
                            article.setTitle(title);
                        }
                        if (imgSubject != null) {
                            String src = imgSubject.attr("src");
                            article.setThumnail(src);
                        }
                        if (linkSubject != null) {
                            String link = linkSubject.attr("href");
                            article.setUrl(link);
                        }
                        if (descrip != null) {
                            String des = descrip.text();
                            article.setDecription(des);
                        }
                        //Add to list
                        listArticle.add(article);
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            return listArticle;
        }

        @Override
        protected void onPostExecute(ArrayList<Article> articles) {
            super.onPostExecute(articles);
            //Setup data recyclerView
            articleAdapter = new ArticleAdapter(MainActivity.this,articles);
            recycler.setAdapter(articleAdapter);
        }
    }
}
