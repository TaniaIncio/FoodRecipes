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
import android.widget.LinearLayout;

import com.tincio.foodrecipes.R;
import com.tincio.foodrecipes.data.database.DatabaseHelper;
import com.tincio.foodrecipes.data.model.StepRecipe;
import com.tincio.foodrecipes.presentation.viewModel.StepViewModel;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class StepFragment extends LifecycleFragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    private static final String RECIPE_ID = "idRecipe";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    //private OnListFragmentInteractionListener mListener;
    private StepViewModel viewModel;
    private static final String UID_KEY = "stepId";
    RecyclerView recyclerView;
    LinearLayout linearIngredient;
    public static String TAG = StepFragment.class.getSimpleName();
    int idRecipe ;
    MyStepRecyclerViewAdapter adapter;
    private SharedPreferences preferences;

    private int getIdRecipe(){
        return preferences.getInt(getString(R.string.preferences_recipeid), 0);
    }

    public StepFragment(){}
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(StepViewModel.class);
        viewModel.init(UID_KEY);
        preferences = getActivity().getSharedPreferences(getString(R.string.preferences), Context.MODE_PRIVATE);
        this.idRecipe = getIdRecipe();
        DatabaseHelper helper = new DatabaseHelper(getActivity());
        viewModel.getSteps(helper,this.idRecipe).observe(this, steps -> {
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(getContext(), mColumnCount));
            }
            adapter = new MyStepRecyclerViewAdapter(steps);
            recyclerView.setAdapter(adapter);
            adapter.setOnItemClickListener(new MyStepRecyclerViewAdapter.OnItemClickListener() {
                @Override
                public void setOnItemClickListener(StepRecipe step, Integer indice) {

                    if(getResources().getBoolean(R.bool.isTablet))
                        getFragmentManager().beginTransaction().replace(R.id.fragment_base_tablet, new InstructionFragment()).commit();
                    else
                        getFragmentManager().beginTransaction().replace(R.id.fragment_base_step, InstructionFragment.newInstance(step.getInstruction(), step.getUrlPlayer(), step.getId(), adapter.getValues())).addToBackStack(InstructionFragment.TAG).commit();
                }
            });
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_step_list, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_step);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setHasFixedSize(true);
        linearIngredient = (LinearLayout)view.findViewById(R.id.option_ingredient);
        linearIngredient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getResources().getBoolean(R.bool.isTablet))
                    getFragmentManager().beginTransaction().replace(R.id.fragment_base_tablet, IngredientFragment.newInstance(1,idRecipe)).commit();
                else
                {
                    getFragmentManager().beginTransaction().replace(R.id.fragment_base_step, IngredientFragment.newInstance(1,idRecipe)).addToBackStack(IngredientFragment.TAG).commit();
                }
            }
        });
        return view;
    }

}
