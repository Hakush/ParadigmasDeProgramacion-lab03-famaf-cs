package com.group_30_lab3_2024.namedEntities.categories;

import com.group_30_lab3_2024.namedEntities.NamedEntity;

public class Person extends NamedEntity {
    private int personId;
    private String firstName;
    private String lastName;

    public Person(String canonicalName, int personId, String firstName, String lastName) {
        super(canonicalName, "PERSON");
        this.personId = personId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}
