package com.group_30_lab3_2024.utils;

import java.util.ArrayList;
import java.util.List;
import com.group_30_lab3_2024.namedEntities.NamedEntity;

public class StadisticsPrinter {
    private List<TuplaEntity> categories;
    private List<TuplaEntity> topics;

    public StadisticsPrinter(List<NamedEntity> namedEntities) {

        this.categories = new ArrayList<>();
        List<NamedEntity> persons = new ArrayList<>();
        List<NamedEntity> organizations = new ArrayList<>();
        List<NamedEntity> locations = new ArrayList<>();
        List<NamedEntity> others = new ArrayList<>();
        for (NamedEntity ne : namedEntities) {
            switch (ne.getCategory()) {
                case "PERSON":
                    persons.add(ne);
                    break;
                case "ORGANIZATION":
                    organizations.add(ne);
                    break;
                case "LOCATION":
                    locations.add(ne);
                    break;
                case "OTHER":
                    others.add(ne);
                    break;
                default:
                    // Skip any unrecognized category
                    break;
        }
}

        this.categories.add(new TuplaEntity("PERSON", persons));
        this.categories.add(new TuplaEntity("ORGANIZATION", organizations));
        this.categories.add(new TuplaEntity("LOCATION", locations));
        this.categories.add(new TuplaEntity("OTHER", others));

        TopicsStadistics ts = new TopicsStadistics();

        ts.addEntities(namedEntities);
        this.topics = new ArrayList<>();
        this.topics.add(new TuplaEntity("SPORTS", ts.getSports()));
        this.topics.add(new TuplaEntity("POLITICS", ts.getPolitics()));
        this.topics.add(new TuplaEntity("ECONOMY", ts.getEconomy()));
        this.topics.add(new TuplaEntity("HEALTH", ts.getHealth()));
        this.topics.add(new TuplaEntity("TECHNOLOGY", ts.getTechnology()));
        this.topics.add(new TuplaEntity("ENTERTAINMENT", ts.getEntertainment()));
        this.topics.add(new TuplaEntity("OTHER", ts.getOther()));

    }

    public void printStatsCat() {

        for (TuplaEntity t : categories) {
            System.out.println("*****************" + t.getKey() + ":******************* ");
            for (NamedEntity p : t.getValue()) {
                System.out.println(p.getCanonicalName() + "(" + p.getCounter() + ")");
            }

        }

    }

    public void printStatsTopic() {
        for (TuplaEntity t : topics) {
            System.out.println("*****************" + t.getKey() + ":******************* ");
            for (NamedEntity p : t.getValue()) {
                System.out.println(p.getCanonicalName() + "(" + p.getCounter() + ")");
            }

        }

    }

    public List<TuplaEntity> getCategories() {
        return categories;
    }

    public void setCategories(List<TuplaEntity> categories) {
        this.categories = categories;
    }

    public List<TuplaEntity> getTopics() {
        return topics;
    }

    public void setTopics(List<TuplaEntity> topics) {
        this.topics = topics;
    }

    public void addCategory(TuplaEntity category) {
        this.categories.add(category);
    }

    public void addTopic(TuplaEntity topic) {
        this.topics.add(topic);
    }

}
