package com.example.android.newsfeedapp.modules;

/**
 * Created by Ahmad Siafaddin on 10/28/2017.
 */

public class NewsResult {
    private String sectionName;
    private String webPublicationDate;
    private String webTitle;
    private String shortUrl;
    private String byline;

    public String getByline() {
        return byline;
    }

    public void setByline(String byline) {
        this.byline = byline;
    }

    public NewsResult(String sectionName, String webPublicationDate, String webTitle, String shortUrl, String byline) {
        this.sectionName = sectionName;
        this.webPublicationDate = webPublicationDate;
        this.webTitle = webTitle;
        this.shortUrl = shortUrl;
        this.byline = byline;

    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getWebPublicationDate() {
        return webPublicationDate;
    }

    public void setWebPublicationDate(String webPublicationDate) {
        this.webPublicationDate = webPublicationDate;
    }

    public String getWebTitle() {
        return webTitle;
    }

    public void setWebTitle(String webTitle) {
        this.webTitle = webTitle;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String webUrl) {
        this.shortUrl = webUrl;
    }


}
