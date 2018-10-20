package com.baoandjon.burri_do;

import android.graphics.drawable.Drawable;

public class Tag {
    private String name;
    private Drawable icon;

    public Tag(String name, Drawable icon) {
        this.name = name;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }
}
