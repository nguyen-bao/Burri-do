package com.baoandjon.burri_do;

public class Tag {
    private String name;
    private int iconRef;

    public Tag(String name, int iconRef) {
        this.name = name;
        this.iconRef = iconRef;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIconRef() {
        return iconRef;
    }

    public void setIcon(int iconRef) {
        this.iconRef = iconRef;
    }
}
