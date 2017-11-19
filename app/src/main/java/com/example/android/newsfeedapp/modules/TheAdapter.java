package com.example.android.newsfeedapp.modules;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.android.newsfeedapp.R;

import java.util.ArrayList;

/**
 * Created by Ahmad Siafaddin on 10/28/2017.
 */

public class TheAdapter extends ArrayAdapter<NewsResult> {
    Context context;
    int resource;
    ArrayList<NewsResult> objects;

    public TheAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList<NewsResult> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(resource, null);
        }

        TextView sectionName = convertView.findViewById(R.id.sectionName);
        TextView webTitle = convertView.findViewById(R.id.webTitle);
        TextView webPublicationDate = convertView.findViewById(R.id.webPublicationDate);
        TextView webUrl = convertView.findViewById(R.id.webUrl);
        TextView byline = convertView.findViewById(R.id.byline);
        NewsResult object = objects.get(position);

        sectionName.setText(object.getSectionName());
        webTitle.setText(object.getWebTitle());
        webPublicationDate.setText(object.getWebPublicationDate());
        webUrl.setText(object.getShortUrl());
        byline.setText(object.getByline());
        return convertView;
    }
}


