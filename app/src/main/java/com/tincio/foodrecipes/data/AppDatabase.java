package com.tincio.foodrecipes.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.tincio.foodrecipes.data.dao.RecipeDao;
import com.tincio.foodrecipes.data.model.Recipe;
import com.tincio.foodrecipes.data.model.StepRecipe;

/**
 * Created by juan on 20/05/2017.
 */
@Database(entities = {Recipe.class, StepRecipe.class} , version = 1)
public abstract class AppDatabase extends RoomDatabase{
    public abstract RecipeDao recipeDao();
}
