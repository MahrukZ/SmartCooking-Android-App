package com.example.mob_dev_portfolio.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.example.mob_dev_portfolio.db.DAOs.FavouritesDAO;
import com.example.mob_dev_portfolio.db.DAOs.ShoppingCartDAO;
import com.example.mob_dev_portfolio.db.entities.Favourites;
import com.example.mob_dev_portfolio.db.entities.Item;

@Database(entities = {Item.class, Favourites.class}, version = 1)
public abstract class SmartCooking extends RoomDatabase {

    private static SmartCooking INSTANCE;
    public abstract ShoppingCartDAO getShoppingCartDAO();
    public abstract FavouritesDAO getFavouritesDAO();

    public static synchronized SmartCooking getInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), SmartCooking.class, "SmartCooking")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }

}
