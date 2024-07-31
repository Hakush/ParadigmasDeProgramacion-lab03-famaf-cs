package com.group_30_lab3_2024.namedEntities.categories;

import com.group_30_lab3_2024.namedEntities.NamedEntity;

public class Other extends NamedEntity {
    private String type;

    public Other(String canonicalName, String type) {
        super(canonicalName, "OTHER");
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
