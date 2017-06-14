package com.tincio.foodrecipes.presentation;

import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
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
import com.tincio.foodrecipes.presentation.viewModel.IngredientViewModel;

public class IngredientFragment extends LifecycleFragment {

    // TODO: Customize parameters
    private int mColumnCount = 1;
    private static final String ARG_COLUMN_COUNT = "column-count";
    private static final String RECIPE_ID = "idRecipe";
    private IngredientViewModel viewModel;
    private static final String UID_KEY = "ingredientId";
    RecyclerView recyclerView;
    public static String TAG = IngredientFragment.class.getSimpleName();
    MyIngredientRecyclerViewAdapter adapter;
    private SharedPreferences preferences;
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public IngredientFragment() {
    }

    public static IngredientFragment newInstance(int columnCount, int idRecipe) {
        IngredientFragment fragment = new IngredientFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        args.putInt(RECIPE_ID, idRecipe);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private int getIdRecipe(){
        return preferences.getInt(getString(R.string.preferences_recipeid), 0);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(IngredientViewModel.class);
        viewModel.init(UID_KEY);
        preferences = getActivity().getSharedPreferences(getString(R.string.preferences), Context.MODE_PRIVATE);
        DatabaseHelper helper = new DatabaseHelper(getActivity());
        viewModel.getIngredient(helper,getIdRecipe()).observe(this, ingredients -> {
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(getContext(), mColumnCount));
            }
            adapter = new MyIngredientRecyclerViewAdapter(ingredients);
            recyclerView.setAdapter(adapter);

            /*adapterRecipe.setOnItemClickListener(new AdapterRecyclerRecipe.OnItemClickListener() {
                @Override
                public void setOnItemClickListener(Recipe recipe, Integer indice) {
                    getFragmentManager().beginTransaction().replace(R.id.fragment_base, new StepFragment()).commit();

                }
            });*/
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ingredient_list, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_ingredient);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setHasFixedSize(true);
        // Set the adapter

            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), mColumnCount));
            }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
