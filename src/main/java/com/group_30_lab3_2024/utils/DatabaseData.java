package com.group_30_lab3_2024.utils;

import java.io.Serializable;

public class DatabaseData implements Serializable{
    private String label;
    private String Category;
    private String[] Topics;
    private String[] keywords;

    public DatabaseData(String label, String Category, String[] Topics, String[] keywords) {
        this.label = label;
        this.Category = Category;
        this.Topics = Topics;
        this.keywords = keywords;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String Category) {
        this.Category = Category;
    }

    public String[] getTopics() {
        return Topics;
    }

    public void setTopics(String[] Topics) {
        this.Topics = Topics;
    }

    public String[] getKeywords() {
        return keywords;
    }

    public void setKeywords(String[] keywords) {
        this.keywords = keywords;
    }

}