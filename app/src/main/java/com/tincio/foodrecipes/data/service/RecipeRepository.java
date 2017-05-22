package com.tincio.foodrecipes.data.service;

import android.arch.lifecycle.LiveData;

import com.tincio.foodrecipes.data.dao.RecipeDao;
import com.tincio.foodrecipes.data.model.Recipe;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executor;

import javax.inject.Inject;

import retrofit2.Response;

/**
 * Created by juan on 21/05/2017.
 */

public class RecipeRepository {

    private final WebService webservice;
    private final RecipeDao recipeDao;
    private final Executor executor;

    @Inject
    public RecipeRepository(WebService webservice, RecipeDao recipeDao, Executor executor) {
        this.webservice = webservice;
        this.recipeDao = recipeDao;
        this.executor = executor;
    }

    public LiveData<List<Recipe>> getRecipe(int recipeId) {
        refreshRecipe(recipeId);
        // return a LiveData directly from the database.
        return recipeDao.load();
    }

    private void refreshRecipe(final int RecipeId) {
        executor.execute(() -> {
            // running in a background thread
            // check if Recipe was fetched recently
            boolean RecipeExists = false; //recipeDao.hasRecipe(FRESH_TIMEOUT);
            if (!RecipeExists) {
                // refresh the data
                Response response = null;
                try {
                    response = webservice.getRecipe(RecipeId).execute();
                    System.out.println("response "+response.body());
                    //Recipe mRecipe = Gson
                    //recipeDao.save(response.body());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // TODO check for error etc.
                // Update the database.The LiveData will automatically refresh so
                // we don't need to do anything else here besides updating the database

            }
        });
    }
    //private WebService webservice;
    
   /* public LiveData<Recipe> getRecipe(int RecipeId) {
        // This is not an optimal implementation, we'll fix it below
        final MutableLiveData<Recipe> data = new MutableLiveData<>();
        webservice.getRecipe(RecipeId).enqueue(new Callback<Recipe>() {
            @Override
            public void onResponse(Call<Recipe> call, Response<Recipe> response) {
                // error case is left out for brevity
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Recipe> call, Throwable t) {

            }
        });
        return data;
    }*/
}
