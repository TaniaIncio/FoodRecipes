package com.tincio.foodrecipes.presentation.viewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.tincio.foodrecipes.data.model.Recipe;
import com.tincio.foodrecipes.data.service.RecipeRepository;

import java.util.List;

public class RecipeListViewModel extends ViewModel {
    private int recipeId;
    private LiveData<List<Recipe>> recipes;
    private RecipeRepository recipeService;

    public RecipeListViewModel(RecipeRepository recipeService) {
        this.recipeService = recipeService;
    }

    public void init(int recipeId) {
        if(this.recipes != null){
            return;
        }
        recipes = recipeService.getRecipe(recipeId);
        this.recipeId = recipeId;
    }
    public LiveData<List<Recipe>> getRecipe() {
        return recipes;
    }
}
