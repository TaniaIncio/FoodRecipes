package com.tincio.foodrecipes.data.service;

import android.arch.lifecycle.LiveData;

import com.tincio.foodrecipes.data.database.DatabaseHelper;
import com.tincio.foodrecipes.data.model.Ingredient;

import java.util.List;

/**
 * Created by juan on 03/06/2017.
 */

public class IngredientRepository {
    private DatabaseHelper helper;

    public IngredientRepository(DatabaseHelper helper) {
        // this.webservice = webservice;
        this.helper = helper;
    }
    public LiveData<List<Ingredient>> getIngredients(int recipeId) {
        return helper.loadIngredient(recipeId);
    }

    public void saveInDataBase(List<Ingredient> lista){
        for (Ingredient Ingredient: lista){
            helper.insertIngredientAsync(Ingredient);
        }
    }

    public void deleteIngredient(){
        helper.deleteIngredient();
    }
}
