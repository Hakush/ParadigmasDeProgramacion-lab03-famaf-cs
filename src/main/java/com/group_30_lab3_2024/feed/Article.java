package com.group_30_lab3_2024.feed;

import java.util.Date;

public class Article {
    private String title;
    private String description;
    private String link;
    private Date pubDate;

    public Article(String title, String description, String link, Date pubDate) {
        this.title = title;
        this.description = description;
        this.link = link;
        this.pubDate = pubDate;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getLink() {
        return link;
    }

    public Date getPubDate() {
        return pubDate;
    }

    public void print() {
        System.out.println("Title: " + title);
        System.out.println("Description: " + description);
        System.out.println("Link: " + link);
        System.out.println("Publication Date: " + pubDate);
    }

    public void prettyPrint() {
        System.out.println(
                "**********************************************************************************************");
        System.out.println("Title: " + this.getTitle());
        System.out.println("Publication Date: " + this.getPubDate());
        System.out.println("Link: " + this.getLink());
        System.out.println("Text: " + this.getDescription());
        System.out.println(
                "**********************************************************************************************");
    }
}