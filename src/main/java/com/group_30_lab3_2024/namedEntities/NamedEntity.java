package com.group_30_lab3_2024.namedEntities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class NamedEntity implements Serializable {
    private String canonicalName;
    private List<String> topics = new ArrayList<>();
    private int counter;
    private String category;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NamedEntity that = (NamedEntity) o;
        return Objects.equals(canonicalName, that.canonicalName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(canonicalName);
    }

    public NamedEntity(String canonicalName, String category) {
        this.canonicalName = canonicalName;
        this.topics.add("");
        this.counter = 1;
        this.category = category;
    }

    public String getCanonicalName() {
        return canonicalName;
    }

    public void setCanonicalName(String canonicalName) {
        this.canonicalName = canonicalName;
    }

    public List<String> getTopics() {
        return topics;
    }

    public void setTopics(String topic) {
        this.topics.add(topic);
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
