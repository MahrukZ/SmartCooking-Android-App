package com.example.mob_dev_portfolio.db.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "ShoppingCartItem", indices = {@Index(value = {"itemName"}, unique = true)}
)
public class Item {

    @PrimaryKey(autoGenerate = true)
    int id;

    @ColumnInfo(name = "itemName")
    String name;

    public Item() {
    }

    public Item(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
