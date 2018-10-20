package com.baoandjon.burri_do;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;

import java.util.ArrayList;

public class Project extends Task {
    private ArrayList<Ingredient> ingredients;

    public Project(String title, int iconRef) {
        super(title, iconRef);
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}
