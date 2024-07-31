package com.group_30_lab3_2024.utils;

import java.util.List;
import com.group_30_lab3_2024.namedEntities.NamedEntity;

public class TuplaEntity {
    private String key;
    private List<NamedEntity> value;

    public TuplaEntity(String key, List<NamedEntity> value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public List<NamedEntity> getValue() {
        return value;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setValue(List<NamedEntity> value) {
        this.value = value;
    }

}
