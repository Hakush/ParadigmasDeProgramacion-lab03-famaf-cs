package com.group_30_lab3_2024.namedEntities.categories;

import com.group_30_lab3_2024.namedEntities.NamedEntity;

public class Location extends NamedEntity {
    private String canonicalForm;
    private String country;
    private String latitude;
    private String longitude;

    public Location(String canonicalName, String canonicalForm, String country, String latitude, String longitude) {
        super(canonicalName, "LOCATION");
        this.canonicalForm = canonicalForm;
        this.country = country;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getCanonicalForm() {
        return canonicalForm;
    }

    public void setCanonicalForm(String canonicalForm) {
        this.canonicalForm = canonicalForm;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
