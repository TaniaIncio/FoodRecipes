package com.tincio.foodrecipes.presentation.viewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.persistence.room.Database;

import com.tincio.foodrecipes.data.database.DatabaseHelper;
import com.tincio.foodrecipes.data.model.Recipe;
import com.tincio.foodrecipes.data.service.RecipeRepository;
import com.tincio.foodrecipes.dominio.callback.RecipeCallback;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by juan on 27/05/2017.
 */

public class RecipeViewModel extends ViewModel{

    String recipeId;
    LiveData<List<Recipe>> listRecipe;
    private RecipeRepository recipeService;

    public void init(String recipeId){
        if(this.listRecipe!= null){
            return;
        }
        this.recipeId = recipeId;
    }

 /*   @Inject // UserRepository parameter is provided by Dagger 2
    public RecipeViewModel(RecipeRepository userRepo) {
        this.recipeService = userRepo;
    }*/

    public void getRecipe(RecipeCallback callback, DatabaseHelper helper)
    {
        recipeService = new RecipeRepository(helper,callback);
        recipeService.getRecipe(1);
        //return this.listRecipe;
    }


}
