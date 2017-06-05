package com.tincio.foodrecipes.presentation;

import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
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
import com.tincio.foodrecipes.data.service.response.StepResponse;
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
    public static String TAG = RecipeListFragment.class.getSimpleName();
    int idRecipe ;
    MyStepRecyclerViewAdapter adapter;
    public StepFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static StepFragment newInstance(int columnCount, int idRecipe) {
        StepFragment fragment = new StepFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        args.putInt(RECIPE_ID, idRecipe);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(StepViewModel.class);
        viewModel.init(UID_KEY);
        this.idRecipe = getArguments().getInt(RECIPE_ID, 0) == 0? this.idRecipe :getArguments().getInt(RECIPE_ID);
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
                    getFragmentManager().beginTransaction().replace(R.id.fragment_base, new InstructionFragment()).addToBackStack(TAG).commit();
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
                System.out.println("id enviada "+idRecipe);
                getFragmentManager().beginTransaction().replace(R.id.fragment_base, IngredientFragment.newInstance(1,idRecipe)).addToBackStack(TAG).commit();
            }
        });
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
       /* if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
       // mListener = null;
    }

 /*   @Override
    public void onListFragmentInteraction(StepResponse item) {

    }*/

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(StepResponse item);
    }
}
