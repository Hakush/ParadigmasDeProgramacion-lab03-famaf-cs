package com.group_30_lab3_2024.utils;

import com.group_30_lab3_2024.feed.Article;
import java.util.ArrayList;
import java.util.List;

public class FeedsData {
    private String label;
    private String url;
    private String type;
    private List<Article> articles = new ArrayList<>();

    public FeedsData(String label, String url, String type) {
        this.label = label;
        this.url = url;
        this.type = type;
    }

    public String getLabel() {
        return label;
    }

    public String getUrl() {
        return url;
    }

    public String getType() {
        return type;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void addArticle(Article article) {
        articles.add(article);
    }

    public void addAll(List<Article> articles) {
        this.articles.addAll(articles);
    }

    public void print() {
        System.out.println("Feed: " + label);
        System.out.println("URL: " + url);
        System.out.println("Type: " + type);
    }
}