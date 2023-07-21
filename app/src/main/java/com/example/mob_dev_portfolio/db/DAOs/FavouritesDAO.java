package com.example.mob_dev_portfolio.db.DAOs;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.example.mob_dev_portfolio.db.entities.Favourites;
import java.util.List;

@Dao
public interface FavouritesDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addFavourite(Favourites favourite);

    @Query("SELECT * FROM Favourites")
    List<Favourites> getAllFavourites();

    @Query("SELECT EXISTS (SELECT 1 FROM Favourites WHERE recipeId=:recipeId)")
    public int isFavorite(int recipeId);

    @Query("DELETE FROM Favourites WHERE recipeId=:recipeId")
    void deleteFavouriteById(int recipeId);

    @Delete
    void deleteFavourite(Favourites favourite);
}
