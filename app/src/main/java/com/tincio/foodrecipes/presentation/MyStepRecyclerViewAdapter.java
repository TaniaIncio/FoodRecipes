package com.tincio.foodrecipes.presentation;

import android.arch.lifecycle.LiveData;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tincio.foodrecipes.R;
import com.tincio.foodrecipes.data.model.StepRecipe;
import com.tincio.foodrecipes.data.service.response.StepResponse;
import com.tincio.foodrecipes.presentation.StepFragment.OnListFragmentInteractionListener;

import java.util.List;


/**
 * {@link RecyclerView.Adapter} that can display a {@link StepResponse} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyStepRecyclerViewAdapter extends RecyclerView.Adapter<MyStepRecyclerViewAdapter.ViewHolder> {

    private final List<StepRecipe> mValues;
    //private final OnListFragmentInteractionListener mListener;

    public MyStepRecyclerViewAdapter(List<StepRecipe> items) {//, OnListFragmentInteractionListener listener
        mValues = items;
      //  mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_step, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
      //  holder.mIdView.setText(mValues.get(position).id);
        holder.mContentView.setText(mValues.get(position).getName());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
       //         if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
     //               mListener.onListFragmentInteraction(holder.mItem);
         //       }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mContentView;
        public StepRecipe mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mContentView = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
