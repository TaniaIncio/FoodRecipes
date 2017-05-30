package com.tincio.foodrecipes.presentation.viewModel;

import android.arch.lifecycle.ViewModel;

import com.tincio.foodrecipes.data.service.RecipeRepository;
import com.tincio.foodrecipes.data.service.StepRepository;
import com.tincio.foodrecipes.dominio.callback.RecipeCallback;
import com.tincio.foodrecipes.dominio.callback.StepCallback;

/**
 * Created by juan on 28/05/2017.
 */

public class StepViewModel extends ViewModel{

    String recipeId;
    //LiveData<List<Recipe>> listRecipe;
    private StepRepository recipeService;

    public void init(String recipeId){

        this.recipeId = recipeId;
    }

 /*   @Inject // UserRepository parameter is provided by Dagger 2
    public RecipeViewModel(RecipeRepository userRepo) {
        this.recipeService = userRepo;
    }*/

    public void getSteps(StepCallback callback, String idRecipe)
    {
        recipeService = new StepRepository(callback);
        recipeService.getStep(idRecipe);
        //return this.listRecipe;
    }
}
