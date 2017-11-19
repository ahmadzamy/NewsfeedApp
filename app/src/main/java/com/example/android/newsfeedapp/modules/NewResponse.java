package com.example.android.newsfeedapp.modules;

/**
 * Created by Ahmad Siafaddin on 10/28/2017.
 */

public class NewResponse {
        private int total;
    private NewsResult results;

    public NewResponse(int total, NewsResult results) {
        this.total = total;
        this.results = results;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public NewsResult getResults() {
        return results;
    }

    public void setResults(NewsResult results) {
        this.results = results;
    }
}
