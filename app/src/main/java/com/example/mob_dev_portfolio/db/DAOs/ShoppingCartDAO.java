package com.example.mob_dev_portfolio.db.DAOs;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.mob_dev_portfolio.db.entities.Item;

import java.util.List;

@Dao
public interface ShoppingCartDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addItem(Item item);

    @Query("SELECT * FROM ShoppingCartItem")
    List<Item> getAllItems();

    @Delete
    void deleteItem(Item item);



}
