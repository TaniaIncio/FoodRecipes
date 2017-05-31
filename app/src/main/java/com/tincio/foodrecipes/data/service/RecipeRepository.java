package com.tincio.foodrecipes.data.service;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.tincio.foodrecipes.data.database.DatabaseHelper;
import com.tincio.foodrecipes.data.model.Recipe;
import com.tincio.foodrecipes.data.model.StepRecipe;
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
    List<Recipe> listRecipe;
    List<StepRecipe> listSteps;
    public RecipeRepository(DatabaseHelper helper) {
       // this.webservice = webservice;
        this.helper = helper;
    }
//LiveData<List<Recipe>>
    public LiveData<List<Recipe>> getRecipe(int recipeId) {
        refreshRecipe(recipeId);
        // return a LiveData directly from the database.
        return helper.load();
    }

    private Recipe convertToRecipe(RecipeResponse mResponse){
        Recipe mRecipe = new Recipe();
        mRecipe.setId(mResponse.getId());
        mRecipe.setDescription(mResponse.getName());
        mRecipe.setName(mResponse.getName());
        mRecipe.setImage(mResponse.getImage());
        convertListStep(mResponse.getSteps(), mResponse.getId());
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
