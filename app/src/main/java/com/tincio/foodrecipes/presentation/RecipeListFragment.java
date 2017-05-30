package com.tincio.foodrecipes.presentation;


import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tincio.foodrecipes.R;
import com.tincio.foodrecipes.data.service.response.RecipeResponse;
import com.tincio.foodrecipes.dominio.callback.RecipeCallback;
import com.tincio.foodrecipes.presentation.adapter.AdapterRecyclerRecipe;
import com.tincio.foodrecipes.presentation.viewModel.RecipeViewModel;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class RecipeListFragment extends LifecycleFragment implements RecipeCallback{

    public static String TAG = RecipeListFragment.class.getSimpleName();

    private static final String UID_KEY = "recipeId";
    private RecipeViewModel viewModel;
    AdapterRecyclerRecipe adapterRecipe;
    RecyclerView.LayoutManager mManagerRecycler;

    /**OUTLEt**/
    RecyclerView mRecyclerRecipes;
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        /*viewModel = ViewModelProviders.of(this).get(UserProfileViewModel.class);
        viewModel.init(UID_KEY);*/
        viewModel = ViewModelProviders.of(this).get(RecipeViewModel.class);
        viewModel.init(UID_KEY);
        viewModel.getRecipe(this);
      //  viewModel.getRecipe().observe(this, recipe -> {
        //    adapterRecipe = new AdapterRecyclerRecipe(recipe);
          //  mRecyclerRecipes.setAdapter(adapterRecipe);
        //});
   //     int recipeId = getArguments().getInt(UID_KEY);
     /*   viewModel = ViewModelProviders.of(this).get(RecipeListViewModel.class);
        viewModel.init(recipeId);*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_recipe, container, false);
        mRecyclerRecipes = (RecyclerView)view.findViewById(R.id.recycler_recipe);
        mManagerRecycler = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false);
        mRecyclerRecipes.setLayoutManager(mManagerRecycler);
        mRecyclerRecipes.setHasFixedSize(true);
        return view;
    }

    @Override
    public void onResponse(LiveData<List<RecipeResponse>> responseMovies, String... mensajes) {
        if(responseMovies!= null){
            adapterRecipe = new AdapterRecyclerRecipe(responseMovies);
            mRecyclerRecipes.setAdapter(adapterRecipe);
            adapterRecipe.setOnItemClickListener(new AdapterRecyclerRecipe.OnItemClickListener() {
                @Override
                public void setOnItemClickListener(RecipeResponse recipe, Integer indice) {
                    getFragmentManager().beginTransaction().replace(R.id.fragment_base, new StepFragment()).commit();

                }
            });
        }

    }
}
