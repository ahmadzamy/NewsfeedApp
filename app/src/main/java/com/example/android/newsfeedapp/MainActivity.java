package com.example.android.newsfeedapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.newsfeedapp.modules.NewsResult;
import com.example.android.newsfeedapp.modules.TheAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {

    ListView listView;
    TextView total;
    TextView notFound;
    FloatingActionButton refresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.list_view);
        total = (TextView) findViewById(R.id.total_count);
        notFound = (TextView) findViewById(R.id.not_found);
        refresh = (FloatingActionButton) findViewById(R.id.refresh);


        getSupportLoaderManager().initLoader(0, null, MainActivity.this).forceLoad();

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getSupportLoaderManager().getLoader(0) == null) {
                    getSupportLoaderManager().initLoader(0, null, MainActivity.this).forceLoad();
                } else {
                    getSupportLoaderManager().restartLoader(80, null, MainActivity.this).forceLoad();
                }
                Toast.makeText(MainActivity.this, "The page is updated", Toast.LENGTH_SHORT).show();
            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                NewsResult newsResult = (NewsResult) adapterView.getItemAtPosition(i);
                Intent intent = new Intent();
                intent.setData(Uri.parse(newsResult.getShortUrl()));
                startActivity(intent);
            }
        });

    }

    @Override
    public Loader<String> onCreateLoader(int id, Bundle args) {
        return new NewaAsynkTask(this);
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String s) {
        if (s != null && !s.isEmpty())
            updateUI(s);
        else {
            Toast.makeText(MainActivity.this, "No Internet", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onLoaderReset(Loader<String> loader) {

    }

    String shortUrl;

    private void updateUI(String s) {

        ArrayList<NewsResult> newsList = new ArrayList<>();
        int total2 = 0;
        try {
            JSONObject root = new JSONObject(s);
            Log.v("JSON", s);
            JSONObject r = root.getJSONObject("response");
            total2 = r.getInt("total");

            JSONArray NewsList = r.getJSONArray("results");

            for (int i = 0; i < NewsList.length(); i++) {

                JSONObject newsOject = NewsList.getJSONObject(i);
                String sectionName = newsOject.getString("sectionName");
                String webPublicationDate = newsOject.getString("webPublicationDate");
                String webTitle = newsOject.getString("webTitle");

                JSONObject Author = newsOject.getJSONObject("fields");
                String bylin = Author.getString("byline");
                shortUrl = Author.getString("shortUrl");

                NewsResult book = new NewsResult(sectionName, webPublicationDate, webTitle, shortUrl, bylin);
                newsList.add(book);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        TheAdapter adapter = new TheAdapter(this, R.layout.item_news, newsList);
        listView.setAdapter(adapter);

        if (total2 == 0) {
            notFound.setVisibility(View.VISIBLE);
            notFound.setText("Result Not Found");
            listView.setVisibility(View.GONE);
            total.setVisibility(View.GONE);

        } else {
            notFound.setVisibility(View.GONE);
            listView.setVisibility(View.VISIBLE);
            total.setVisibility(View.VISIBLE);
            total.setText("Result " + String.valueOf(total2) + " Found");
        }
    }
}
