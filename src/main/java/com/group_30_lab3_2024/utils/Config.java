package com.group_30_lab3_2024.utils;

public class Config {
    private boolean printFeed = false;
    private boolean computeNamedEntities = false;
    private String feedKey;
    private String heuristic;
    private String statsFormat;

    public Config(boolean printFeed, boolean computeNamedEntities, String feedKey, String heuristic,
            String statsFormat) {
        this.printFeed = printFeed;
        this.computeNamedEntities = computeNamedEntities;
        this.feedKey = feedKey;
        this.heuristic = heuristic;
        this.statsFormat = statsFormat;
    }

    public boolean getPrintFeed() {
        return printFeed;
    }

    public boolean getComputeNamedEntities() {
        return computeNamedEntities;
    }

    public String getFeedKey() {
        return feedKey;
    }

    public String getHeuristic() {
        return heuristic;
    }

    public String getStatsFormat() {
        return statsFormat;
    }

    public void setStatsFormat(String statsFormat) {
        this.statsFormat = statsFormat;
    }

}
