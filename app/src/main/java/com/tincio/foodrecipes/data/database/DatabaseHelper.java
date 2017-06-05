package com.tincio.foodrecipes.data.database;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.tincio.foodrecipes.data.model.Ingredient;
import com.tincio.foodrecipes.data.model.Recipe;
import com.tincio.foodrecipes.data.model.StepRecipe;

import java.util.List;

/**
 * Created by juan on 30/05/2017.
 */

public class DatabaseHelper {
    private static final String LOG = DatabaseHelper.class.getSimpleName();

    private Context mContext;
    private MyDataBase mDb;

    /**
     * Constructor of DatabaseHelper class to initialize database
     * @param mContext Context of the application
     */
    public DatabaseHelper(Context mContext) {
        this.mContext = mContext;
        mDb=MyDataBase.getDatabase(mContext);
    }

    public MyDataBase getDataBase(){
        return mDb;
    }

    public void insertRecipeAsync(Recipe mRecipe){
        new InsertRecipe().execute(mRecipe);


    }

    private class InsertRecipe extends AsyncTask<Recipe,Void,Void>{

        @Override
        protected Void doInBackground(Recipe... params) {
            mDb.recipeDao().save(params[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }

    public LiveData<List<Recipe>> load(){
        return mDb.recipeDao().load();
    }

    /**Steps**/
    public LiveData<List<StepRecipe>> loadSteps(int idRecipe){
        System.out.println("idrecipe"+idRecipe);
        return mDb.recipeDao().loadSteps(idRecipe);
    }
    public void insertStepsAsync(StepRecipe mStep){
        new InsertStepsAsync().execute(mStep);
    }

    private class InsertStepsAsync extends AsyncTask<StepRecipe,Void,Void>{
        @Override
        protected Void doInBackground(StepRecipe... params) {
            mDb.recipeDao().saveStep(params[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }

    /**Ingredient**/
    public LiveData<List<Ingredient>> loadIngredient(int idRecipe){
        System.out.println("idrecipe"+idRecipe);
        return mDb.recipeDao().loadIngredients(idRecipe);
    }
    public void insertIngredientAsync(Ingredient mStep){
        new InsertIngredientAsync().execute(mStep);
    }

    private class InsertIngredientAsync extends AsyncTask<Ingredient,Void,Void>{
        @Override
        protected Void doInBackground(Ingredient... params) {
            mDb.recipeDao().saveIngredient(params[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }

    public void deleteIngredient(){
        new DeleteIngredientAsync().execute();
    }

    private class DeleteIngredientAsync extends AsyncTask<Void,Void,Void>{
        @Override
        protected Void doInBackground(Void... params) {
            mDb.recipeDao().deleteIngredient();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }
}
