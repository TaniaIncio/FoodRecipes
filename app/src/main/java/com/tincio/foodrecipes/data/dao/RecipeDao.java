package com.tincio.foodrecipes.data.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.tincio.foodrecipes.data.model.Recipe;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by juan on 20/05/2017.
 */
@Dao
public interface RecipeDao {
    @Insert(onConflict = REPLACE)
    void save(Recipe recipe);
//    @Query("select * from recipe where id = :recipeId")
    @Query("SELECT * FROM Recipe")
    LiveData<List<Recipe>> load();
}
