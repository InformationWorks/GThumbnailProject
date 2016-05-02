package com.hbb20.gthumbnailproject;

/**
 * Created by hbb20 on 30/4/16.
 */
public class CaptainPlayer {
    String firstName, lastName, url, countryName;

    public CaptainPlayer(String firstName, String lastName, String countryName, String url) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.url = url;
        this.countryName = countryName;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCountryName() {
        return countryName;
    }
}
