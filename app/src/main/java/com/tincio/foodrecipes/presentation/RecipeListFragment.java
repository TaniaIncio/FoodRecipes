package com.tincio.foodrecipes.presentation;


import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tincio.foodrecipes.R;
import com.tincio.foodrecipes.data.database.DatabaseHelper;
import com.tincio.foodrecipes.data.model.Recipe;
import com.tincio.foodrecipes.presentation.adapter.AdapterRecyclerRecipe;
import com.tincio.foodrecipes.presentation.viewModel.RecipeViewModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class RecipeListFragment extends LifecycleFragment {

    public static String TAG = RecipeListFragment.class.getSimpleName();

    private static final String UID_KEY = "recipeId";
    private RecipeViewModel viewModel;
    AdapterRecyclerRecipe adapterRecipe;
    RecyclerView.LayoutManager mManagerRecycler;
    private int COUNT_ROW = 3;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    /**OUTLEt**/
    RecyclerView mRecyclerRecipes;
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(RecipeViewModel.class);
        viewModel.init(UID_KEY);
        DatabaseHelper helper = new DatabaseHelper(getActivity());
        this.preferences = getActivity().getSharedPreferences(getString(R.string.preferences), Context.MODE_PRIVATE);
        viewModel.getRecipe(helper).observe(this, recipe -> {
            adapterRecipe = new AdapterRecyclerRecipe(recipe);
            mRecyclerRecipes.setAdapter(adapterRecipe);
            adapterRecipe.setOnItemClickListener(new AdapterRecyclerRecipe.OnItemClickListener() {
                @Override
                public void setOnItemClickListener(Recipe recipe, Integer indice) {
                        editor = preferences.edit();
                        editor.putInt(getString(R.string.preferences_recipeid), recipe.getId());
                        editor.apply();
                    getActivity().startActivity(new Intent(getActivity(), StepActivity.class));
                }
            });
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_recipe, container, false);
        mRecyclerRecipes = (RecyclerView)view.findViewById(R.id.recycler_recipe);
        if(getResources().getBoolean(R.bool.isTablet)){
            mManagerRecycler = new GridLayoutManager(getContext(),COUNT_ROW);
        }else{
            mManagerRecycler = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false);
        }

        mRecyclerRecipes.setLayoutManager(mManagerRecycler);
        mRecyclerRecipes.setHasFixedSize(true);
        return view;
    }
}
