package com.tincio.foodrecipes.presentation.viewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.tincio.foodrecipes.data.model.Recipe;
import com.tincio.foodrecipes.data.model.User;
import com.tincio.foodrecipes.data.service.RecipeRepository;

import java.util.List;

public class RecipeListViewModel extends ViewModel {
    private String recipeId;
    private LiveData<User> recipes;
    //private RecipeRepository recipeService;

    public void init(String recipeId) {
  /*      if(this.recipes != null){
            return;
        }
       // recipes = recipeService.getRecipe(1);*/
        this.recipeId = recipeId;
    }

    public RecipeListViewModel(RecipeRepository recipeService) {
     //   this.recipeService = recipeService;
    }


    public LiveData<User> getRecipe() {
        return recipes;
    }
}
