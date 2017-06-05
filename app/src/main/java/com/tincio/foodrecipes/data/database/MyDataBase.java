package com.tincio.foodrecipes.data.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.tincio.foodrecipes.data.dao.RecipeDao;
import com.tincio.foodrecipes.data.model.Ingredient;
import com.tincio.foodrecipes.data.model.Recipe;
import com.tincio.foodrecipes.data.model.StepRecipe;

import static com.tincio.foodrecipes.data.database.MyDataBase.DATABASE_VERSION;

/**
 * Created by juan on 30/05/2017.
 */
@Database(entities = {Recipe.class, StepRecipe.class, Ingredient.class},
        version = DATABASE_VERSION)
public abstract class MyDataBase extends RoomDatabase {
    private static final String DATABASE_NAME = "RecipeDB";
    static final int DATABASE_VERSION = 1;

    private static MyDataBase INSTANCE;
    public abstract RecipeDao recipeDao();

    /**
     * Get database instance
     * @param context Context of the application
     * @return instance of database
     */
    public static MyDataBase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    MyDataBase.class,
                    DATABASE_NAME).build();
        }
        return INSTANCE;
    }

    /**
     * Destroy database instance
     */
    public static void destroyInstance() {
        INSTANCE = null;
    }
}
