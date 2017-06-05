package com.tincio.foodrecipes.data.service;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.tincio.foodrecipes.data.database.DatabaseHelper;
import com.tincio.foodrecipes.data.model.Ingredient;
import com.tincio.foodrecipes.data.model.Recipe;
import com.tincio.foodrecipes.data.model.StepRecipe;
import com.tincio.foodrecipes.data.service.response.IngredientResponse;
import com.tincio.foodrecipes.data.service.response.RecipeResponse;
import com.tincio.foodrecipes.data.service.response.Step;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by juan on 21/05/2017.
 */
public class RecipeRepository {

    private DatabaseHelper helper;
    public RecipeRepository(DatabaseHelper helper) {
        this.helper = helper;
    }

    public LiveData<List<Recipe>> getRecipe(int recipeId) {
        refreshRecipe(recipeId);
        return helper.load();
    }

    private Recipe convertToRecipe(RecipeResponse mResponse){
        Recipe mRecipe = new Recipe();
        mRecipe.setId(mResponse.getId());
        mRecipe.setDescription(mResponse.getName());
        mRecipe.setName(mResponse.getName());
        mRecipe.setImage(mResponse.getImage());
        convertListStep(mResponse.getSteps(), mResponse.getId());
        convertListIngredient(mResponse.getIngredients(), mResponse.getId());
        return mRecipe;
    }

    private void convertListStep(List<Step> list, int idRecipe){
        List<StepRecipe> lista = new ArrayList<>();
        for (Step response: list){
            lista.add(convertToStep(response, idRecipe));
        }
        StepRepository repositoryStep = new StepRepository(helper);
        repositoryStep.saveInDataBase(lista);
    }

    private void convertListIngredient(List<IngredientResponse> list, int idRecipe){
        IngredientRepository repository = new IngredientRepository(helper);

        List<Ingredient> lista = new ArrayList<>();
        for (IngredientResponse response: list){
            lista.add(convertToIngredient(response, idRecipe));
        }

        repository.saveInDataBase(lista);
    }

    private Ingredient convertToIngredient(IngredientResponse mResponse, int idRecipe){
        Ingredient mIngredient = new Ingredient();
        mIngredient.setIdRecipe(idRecipe);
        mIngredient.setIngredient(mResponse.getIngredient());
        mIngredient.setMeasure(mResponse.getMeasure());

        return mIngredient;
    }

    private StepRecipe convertToStep(Step mResponse, int idRecipe){
        StepRecipe mRecipe = new StepRecipe();
        mRecipe.setId(mResponse.getId());
        mRecipe.setIdRecipe(idRecipe);
        mRecipe.setName(mResponse.getShortDescription());
        mRecipe.setInstruction(mResponse.getDescription());
        mRecipe.setImage(mResponse.getThumbnailURL());
        mRecipe.setUrlPlayer(mResponse.getVideoURL());
        return mRecipe;
    }


    private void saveInDataBase(List<RecipeResponse> list){
        IngredientRepository repository = new IngredientRepository(helper);
        repository.deleteIngredient();
        for (RecipeResponse response: list){
            helper.insertRecipeAsync(convertToRecipe(response));
        }
    }
    private List<Recipe> convertListRecipe(List<RecipeResponse> list){
        List<Recipe> lista = new ArrayList<>();
        for (RecipeResponse response: list){
            lista.add(convertToRecipe(response));
        }
        return  lista;
    }

    private void refreshRecipe(final int RecipeId) {
        //.client(httpClient.build())
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.serviceNames.BASE_RECIPES)
                .build();
        try {
            final MutableLiveData<List<Recipe>> data = new MutableLiveData<>();
            WebService service = retrofit.create(WebService.class);
            Call<List<RecipeResponse>> call = service.getRecipe();
            System.out.println("call url "+call.request().url());
            call.enqueue(new Callback<List<RecipeResponse>>() {
                @Override
                public void onResponse(Call<List<RecipeResponse>> call, Response<List<RecipeResponse>> response) {
                    System.out.println("lista recipe "+ response);
                    data.setValue(convertListRecipe(response.body()));
                    /***save in data base*/
                    saveInDataBase(response.body());
                }

                @Override
                public void onFailure(Call<List<RecipeResponse>> call, Throwable t) {
                    System.out.println("error " +
                            "lista recipe "+t.getMessage());

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
