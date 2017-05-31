package com.tincio.foodrecipes.presentation.adapter;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tincio.foodrecipes.R;
import com.tincio.foodrecipes.data.model.Recipe;

import java.util.List;

/**
 * Created by tincio on 03/09/16.
 */
public class AdapterRecyclerRecipe extends  RecyclerView.Adapter<AdapterRecyclerRecipe.ViewHolderItem> {

    List<Recipe> listRecipe;
    Context context;
    ImageView imgRecipe;
    TextView txtRecipe;
    public AdapterRecyclerRecipe(List<Recipe> arrayString) {
        this.listRecipe = arrayString;
    }

    @Override
    public ViewHolderItem onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_recycler_recipe, parent, false);
        ViewHolderItem viewHolder = new ViewHolderItem(view);
        context = parent.getContext();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolderItem holder, int position) {
        holder.txtItemRecycler.setText(listRecipe.get(position).getName());
        if(listRecipe.get(position).getImage().isEmpty())return;
        Picasso.with(context).load(listRecipe.get(position).getImage()).into(holder.imgRecipe);
        // Retrieves an image specified by the URL, displays it in the UI.

    }

    @Override
    public int getItemCount() {
        return listRecipe==null?0:listRecipe.size();
    }


    public class ViewHolderItem extends RecyclerView.ViewHolder {

        ImageView imgRecipe;
        TextView txtItemRecycler;



        public ViewHolderItem(View itemView){
            super(itemView);
            txtItemRecycler = (TextView)itemView.findViewById(R.id.activity_gridlayout_txt);
            imgRecipe = (ImageView)itemView.findViewById(R.id.rowrecyclermovie_img);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mOnItemClickListener != null){
                        if(getAdapterPosition()>=0){
                            mOnItemClickListener.setOnItemClickListener(listRecipe.get(getAdapterPosition()),getAdapterPosition());
                        }

                    }
                }
            });

          }

    }

    OnItemClickListener mOnItemClickListener;
    public interface OnItemClickListener{
        public void setOnItemClickListener(Recipe recipe, Integer indice);
    }

    public void setOnItemClickListener(OnItemClickListener mItemClickListener){
        this.mOnItemClickListener = mItemClickListener;
    }

}