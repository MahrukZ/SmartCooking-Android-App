package com.example.mob_dev_portfolio.db.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "Favourites", indices = {@Index(value = {"title"}, unique = true)}
)
public class Favourites {

    @PrimaryKey(autoGenerate = true)
    int id;

    int recipeId;

    @ColumnInfo(name = "title")
    String name;

    String imageURL;

    public Favourites(){

    }

    public Favourites(int recipeId, String name, String imageURL) {
        this.recipeId = recipeId;
        this.name = name;
        this.imageURL = imageURL;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
