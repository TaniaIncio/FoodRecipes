package com.tincio.foodrecipes.presentation.viewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.tincio.foodrecipes.data.database.DatabaseHelper;
import com.tincio.foodrecipes.data.model.StepRecipe;
import com.tincio.foodrecipes.data.service.StepRepository;

import java.util.List;

/**
 * Created by juan on 28/05/2017.
 */

public class StepViewModel extends ViewModel{

    String recipeId;
    private StepRepository recipeService;

    public void init(String recipeId){

        this.recipeId = recipeId;
    }
    public LiveData<List<StepRecipe>> getSteps(DatabaseHelper helper, int idRecipe)
    {
        recipeService = new StepRepository(helper);
        return recipeService.getSteps(idRecipe);
    }
}
