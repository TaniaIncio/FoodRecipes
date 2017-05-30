package com.tincio.foodrecipes.data.service;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.room.Database;

import com.tincio.foodrecipes.data.dao.RecipeDao;
import com.tincio.foodrecipes.data.database.DatabaseHelper;
import com.tincio.foodrecipes.data.model.Recipe;
import com.tincio.foodrecipes.data.service.response.RecipeResponse;
import com.tincio.foodrecipes.dominio.callback.RecipeCallback;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by juan on 21/05/2017.
 */
public class RecipeRepository {

   // private  WebService webservice;
    //private final RecipeDao recipeDao;
    private RecipeCallback callback;
    private DatabaseHelper helper;

    public RecipeRepository(DatabaseHelper helper, RecipeCallback callback) {
       // this.webservice = webservice;
        this.helper = helper;
        this.callback = callback;
    }
//LiveData<List<Recipe>>
    public void getRecipe(int recipeId) {
        refreshRecipe(recipeId);
        // return a LiveData directly from the database.
        ///return recipeDao.load();
    }

    private Recipe convertToRecipe(RecipeResponse mResponse){
        Recipe mRecipe = new Recipe();
        mRecipe.setId(mResponse.getId());
        mRecipe.setDescription(mResponse.getDescription());
        mRecipe.setName(mResponse.getName());
        mRecipe.setImage(mResponse.getImage());
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
                    data.setValue(convertListRecipe(response.body()));
                    callback.onResponse(data,"");
                    /***save in data base*/
                    saveInDataBase(response.body());
                }

                @Override
                public void onFailure(Call<List<RecipeResponse>> call, Throwable t) {
                    if(helper.load()!=null){
                        callback.onResponse(helper.load(),"");
                    }else{
                        callback.onResponse(null,t.getMessage());
                    }

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
