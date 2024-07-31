package com.group_30_lab3_2024.utils;

import java.util.ArrayList;
import java.util.List;
import com.group_30_lab3_2024.namedEntities.NamedEntity;

public class TopicsStadistics {
    private List<NamedEntity> sports;
    private List<NamedEntity> politics;
    private List<NamedEntity> economy;
    private List<NamedEntity> health;
    private List<NamedEntity> technology;
    private List<NamedEntity> entertainment;
    private List<NamedEntity> other;

    public TopicsStadistics() {
        this.sports = new ArrayList<>();
        this.politics = new ArrayList<>();
        this.economy = new ArrayList<>();
        this.health = new ArrayList<>();
        this.technology = new ArrayList<>();
        this.entertainment = new ArrayList<>();
        this.other = new ArrayList<>();
    }

    public List<NamedEntity> getSports() {
        return sports;
    }

    public void setSports(List<NamedEntity> sports) {
        this.sports = sports;
    }

    public List<NamedEntity> getPolitics() {
        return politics;
    }

    public void setPolitics(List<NamedEntity> politics) {
        this.politics = politics;
    }

    public List<NamedEntity> getEconomy() {
        return economy;
    }

    public void setEconomy(List<NamedEntity> economy) {
        this.economy = economy;
    }

    public List<NamedEntity> getHealth() {
        return health;
    }

    public void setHealth(List<NamedEntity> health) {
        this.health = health;
    }

    public List<NamedEntity> getTechnology() {
        return technology;
    }

    public void setTechnology(List<NamedEntity> technology) {
        this.technology = technology;
    }

    public List<NamedEntity> getEntertainment() {
        return entertainment;
    }

    public void setEntertainment(List<NamedEntity> entertainment) {
        this.entertainment = entertainment;
    }

    public List<NamedEntity> getOther() {
        return other;
    }

    public void setOther(List<NamedEntity> other) {
        this.other = other;
    }

    public void addPerson(List<NamedEntity> persons) {
        for (NamedEntity p : persons) {
            for (String s : p.getTopics()) {
                if (s.equals("SPORTS")) {
                    sports.add(p);
                } else if (s.equals("POLITICS")) {
                    politics.add(p);
                } else if (s.equals("ECONOMY")) {
                    economy.add(p);
                } else if (s.equals("HEALTH")) {
                    health.add(p);
                } else if (s.equals("TECHNOLOGY")) {
                    technology.add(p);
                } else if (s.equals("ENTERTAINMENT")) {
                    entertainment.add(p);
                } else if (s.equals("OTHER")) {
                    other.add(p);
                }
            }
        }
    }

    public void addOrganization(List<NamedEntity> organizations) {
        for (NamedEntity o : organizations) {
            for (String s : o.getTopics()) {
                if (s.equals("SPORTS")) {
                    sports.add(o);
                } else if (s.equals("POLITICS")) {
                    politics.add(o);
                } else if (s.equals("ECONOMY")) {
                    economy.add(o);
                } else if (s.equals("HEALTH")) {
                    health.add(o);
                } else if (s.equals("TECHNOLOGY")) {
                    technology.add(o);
                } else if (s.equals("ENTERTAINMENT")) {
                    entertainment.add(o);
                } else if (s.equals("OTHER")) {
                    other.add(o);
                }
            }
        }
    }

    public void addLocation(List<NamedEntity> locations) {
        for (NamedEntity l : locations) {
            for (String s : l.getTopics()) {
                if (s.equals("SPORTS")) {
                    sports.add(l);
                } else if (s.equals("POLITICS")) {
                    politics.add(l);
                } else if (s.equals("ECONOMY")) {
                    economy.add(l);
                } else if (s.equals("HEALTH")) {
                    health.add(l);
                } else if (s.equals("TECHNOLOGY")) {
                    technology.add(l);
                } else if (s.equals("ENTERTAINMENT")) {
                    entertainment.add(l);
                } else if (s.equals("OTHER")) {
                    other.add(l);
                }
            }
        }
    }

    public void addOther(List<NamedEntity> other) {
        for (NamedEntity o : other) {
            for (String s : o.getTopics()) {
                if (s.equals("SPORTS")) {
                    sports.add(o);
                } else if (s.equals("POLITICS")) {
                    politics.add(o);
                } else if (s.equals("ECONOMY")) {
                    economy.add(o);
                } else if (s.equals("HEALTH")) {
                    health.add(o);
                } else if (s.equals("TECHNOLOGY")) {
                    technology.add(o);
                } else if (s.equals("ENTERTAINMENT")) {
                    entertainment.add(o);
                } else if (s.equals("OTHER")) {
                    other.add(o);
                }
            }
        }
    }

    public void addEntities(List<NamedEntity> entities) {
        for (NamedEntity p : entities) {
            for (String s : p.getTopics()) {
                if (s.equals("SPORTS")) {
                    sports.add(p);
                } else if (s.equals("POLITICS")) {
                    politics.add(p);
                } else if (s.equals("ECONOMY")) {
                    economy.add(p);
                } else if (s.equals("HEALTH")) {
                    health.add(p);
                } else if (s.equals("TECHNOLOGY")) {
                    technology.add(p);
                } else if (s.equals("ENTERTAINMENT")) {
                    entertainment.add(p);
                } else if (s.equals("OTHER")) {
                    other.add(p);
                }
            }
        }
    }

}

