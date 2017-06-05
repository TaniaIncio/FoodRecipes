package com.tincio.foodrecipes.presentation.viewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.tincio.foodrecipes.data.database.DatabaseHelper;
import com.tincio.foodrecipes.data.model.Ingredient;
import com.tincio.foodrecipes.data.service.IngredientRepository;

import java.util.List;

/**
 * Created by juan on 03/06/2017.
 */

public class IngredientViewModel extends ViewModel {

    String recipeId;
    private IngredientRepository recipeService;

    public void init(String recipeId){

        this.recipeId = recipeId;
    }
    public LiveData<List<Ingredient>> getIngredient(DatabaseHelper helper, int idRecipe)
    {
        recipeService = new IngredientRepository(helper);
        return recipeService.getIngredients(idRecipe);
    }

}
