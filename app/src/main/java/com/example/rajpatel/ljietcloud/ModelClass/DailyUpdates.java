package com.example.rajpatel.ljietcloud.ModelClass;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by himangi on 11/05/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DailyUpdates {

    private String title;
    private String description;
    private String user;

    public DailyUpdates() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
