package com.somaprojexts.projectsashimi.card;

public class Card {

    private String name;
    private int image;

    public Card(String name, int image) {
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public int getImage() {
        return image;
    }
}
