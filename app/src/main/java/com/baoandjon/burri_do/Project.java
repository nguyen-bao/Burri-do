package com.baoandjon.burri_do;

import java.util.ArrayList;

public class Project extends Task {
    private ArrayList<Ingredient> ingredients;

    public Project(String title, Sprite icon) {
        super(title, icon);
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}
