package com.baoandjon.burri_do;

import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

public abstract class Task {
    private String title;
    private String description;
    private Date deadline;
    private ArrayList<Tag> tags;
    private Drawable icon;

    public Task(String title, Drawable icon) {
        this.title = title;
        this.icon = icon;
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

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }
}
