package com.tincio.foodrecipes.data.service;

import android.arch.lifecycle.LiveData;

import com.tincio.foodrecipes.data.database.DatabaseHelper;
import com.tincio.foodrecipes.data.model.StepRecipe;

import java.util.List;

/**
 * Created by juan on 28/05/2017.
 */

public class StepRepository {
    private DatabaseHelper helper;

    public StepRepository(DatabaseHelper helper) {
        // this.webservice = webservice;
        this.helper = helper;
    }
    public LiveData<List<StepRecipe>> getSteps(int recipeId) {
        return helper.loadSteps(recipeId);
    }

    public  void saveInDataBase(List<StepRecipe> lista){
        for (StepRecipe step: lista){
            helper.insertStepsAsync(step);
        }
    }

}
