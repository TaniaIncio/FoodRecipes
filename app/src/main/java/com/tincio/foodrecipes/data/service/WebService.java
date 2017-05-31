package com.tincio.foodrecipes.data.service;

import com.tincio.foodrecipes.data.model.Recipe;
import com.tincio.foodrecipes.data.service.response.RecipeResponse;
import com.tincio.foodrecipes.data.service.response.StepResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by juan on 21/05/2017.
 */

public interface WebService {
    @GET("topher/2017/May/59121517_baking/baking.json")
    Call<List<RecipeResponse>> getRecipe();

   /* @GET("data/Step")
    Call<List<StepResponse>> geSteps(@Query(value = "where", encoded = true) String idRecipe);*/

}
