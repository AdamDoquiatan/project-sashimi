package com.somaprojexts.projectsashimi.card;

import android.graphics.drawable.Drawable;

public class Card {

    private String name;
    private Drawable image;

    public Card(String name, Drawable image) {
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public Drawable getImage() {
        return image;
    }
}
