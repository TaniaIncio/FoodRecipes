package com.tincio.foodrecipes.data.service;

import com.tincio.foodrecipes.data.model.Recipe;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by juan on 21/05/2017.
 */

public interface WebService {
    @GET("/data/Recipe")
    Call<Recipe> getRecipe(@Path("recipe") int recipeId);
}
