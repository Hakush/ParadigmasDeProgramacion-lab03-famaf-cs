package com.group_30_lab3_2024.utils;

import java.util.ArrayList;
import java.util.List;
import com.group_30_lab3_2024.namedEntities.NamedEntity;
import com.group_30_lab3_2024.namedEntities.categories.*;

public class EntityParser {
    private List<String> candidate;
    private List<String> categories;

    public EntityParser(List<String> can) {
        this.candidate = can;
        this.categories = new ArrayList<>();
        this.categories.add("PERSON");
        this.categories.add("ORGANIZATION");
        this.categories.add("LOCATION");
        this.categories.add("OTHER");
    }

    public List<String> getCandidate() {
        return candidate;
    }

    public void setCandidate(List<String> candidate) {
        this.candidate = candidate;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public void addCategory(String category) {
        this.categories.add(category);
    }

 public List<NamedEntity> parseEntities(List<DatabaseData> dictionary) {
    List<NamedEntity> entities = new ArrayList<>();
            for(Object e : this.candidate){
                for (DatabaseData data : dictionary) {
                    for (String keyword : data.getKeywords()) {
                            if (keyword.equals(e)) {
                                            if(data.getCategory().equals("PERSON")){
                                                Person p = new Person(data.getLabel(), 0, "", "");
                                                for (String s : data.getTopics()) {
                                                    p.setTopics(s);
                                                }
                                                entities.add(p);
                                                
                                            }else if(data.getCategory().equals("ORGANIZATION")){
                                                Organization o = new Organization(data.getLabel(), "", 0, "");
                                                for (String s : data.getTopics()) {
                                                    o.setTopics(s);
                                                }
                                                entities.add(o);
                                            }else if(data.getCategory().equals("LOCATION")){
                                                Location l = new Location(data.getLabel(), "", "", "", "");
                                                for (String s : data.getTopics()) {
                                                    l.setTopics(s);
                                                }
                                                entities.add(l);
                                            }else if(data.getCategory().equals("OTHER")){
                                                Other m = new Other(data.getLabel(), "");
                                                for (String s : data.getTopics()) {
                                                    m.setTopics(s);
                                                }
                                                entities.add(m);
                                            }            
                        }   
                    }
                }
            }
                
    return entities;
    }


}