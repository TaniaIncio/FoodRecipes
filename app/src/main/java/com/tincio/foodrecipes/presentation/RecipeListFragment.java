package com.tincio.foodrecipes.presentation;


import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tincio.foodrecipes.R;
import com.tincio.foodrecipes.presentation.adapter.AdapterRecyclerRecipe;
import com.tincio.foodrecipes.presentation.viewModel.RecipeListViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecipeListFragment extends LifecycleFragment{

    public static String TAG = RecipeListFragment.class.getSimpleName();

    private static final String UID_KEY = "recipeId";
    private RecipeListViewModel viewModel;
    AdapterRecyclerRecipe adapterRecipe;
    RecyclerView.LayoutManager mManagerRecycler;

    /**OUTLEt**/
    @BindView(R.id.recycler_recipe)
    RecyclerView mRecyclerRecipes;
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel.getRecipe().observe(this, recipe -> {
            adapterRecipe = new AdapterRecyclerRecipe(recipe);
            mRecyclerRecipes.setAdapter(adapterRecipe);
        });
        int recipeId = getArguments().getInt(UID_KEY);
        viewModel = ViewModelProviders.of(this).get(RecipeListViewModel.class);
        viewModel.init(recipeId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_recipe, container, false);
        ButterKnife.bind(this, view);
        mManagerRecycler = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false);
        mRecyclerRecipes.setHasFixedSize(true);
        return view;
    }
}
