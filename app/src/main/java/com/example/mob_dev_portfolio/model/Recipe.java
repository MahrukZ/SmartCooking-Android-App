package com.example.mob_dev_portfolio.model;

public class Recipe {

    private int id;
    private String Title;
    private String Thumbnail;
    private int amountOfDishes;
    private int readyInMins;
    private boolean missedIngredientCount;

    public Recipe(){

    }

    public Recipe(int id, String title, String thumbnail, int amountOfDishes, int readyInMins, boolean missedIngredientCount) {
        this.id = id;
        Title = title;
        Thumbnail = thumbnail;
        this.amountOfDishes = amountOfDishes;
        this.readyInMins = readyInMins;
        this.missedIngredientCount = missedIngredientCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getThumbnail() {
        return Thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        Thumbnail = thumbnail;
    }

    public int getAmountOfDishes() {
        return amountOfDishes;
    }

    public void setAmountOfDishes(int amountOfDishes) {
        this.amountOfDishes = amountOfDishes;
    }

    public int getReadyInMins() {
        return readyInMins;
    }

    public void setReadyInMins(int readyInMins) {
        this.readyInMins = readyInMins;
    }

    public boolean getMissedIngredientCount() {
        return missedIngredientCount;
    }

    public void setMissedIngredientCount(boolean missedIngredientCount) {
        this.missedIngredientCount = missedIngredientCount;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id='" + id + '\'' +
                ", Title='" + Title + '\'' +
                ", Thumbnail='" + Thumbnail + '\'' +
                ", amountOfDishes=" + amountOfDishes +
                ", readyInMins=" + readyInMins +
                ", missedIngredientCount=" + missedIngredientCount +
                '}';
    }
}

