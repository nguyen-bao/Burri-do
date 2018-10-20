package com.baoandjon.burri_do;

import java.util.ArrayList;
import java.util.Date;

public abstract class Task {
    private String title;
    private String description;
    private Date deadline;
    private ArrayList<Tag> tags;
    private int iconRef;

    public Task(String title, int iconRef) {
        this.title = title;
        this.iconRef = iconRef;
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

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public ArrayList<Tag> getTags() {
        return tags;
    }

    public void setTags(ArrayList<Tag> tags) {
        this.tags = tags;
    }

    public int getIconRef() {
        return iconRef;
    }

    public void setIconRef(int iconRef) {
        this.iconRef = iconRef;
    }
}
