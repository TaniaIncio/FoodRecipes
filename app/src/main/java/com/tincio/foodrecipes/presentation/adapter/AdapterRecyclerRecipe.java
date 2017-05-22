package com.tincio.foodrecipes.presentation.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tincio.foodrecipes.R;
import com.tincio.foodrecipes.data.model.Recipe;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by tincio on 03/09/16.
 */
public class AdapterRecyclerRecipe extends  RecyclerView.Adapter<AdapterRecyclerRecipe.ViewHolderItem> {

    List<Recipe> listRecipe;
    Context context;
    String favoritoOff= "ic_favorite_border_white_24dp";
    String favoritoOn= "ic_favorite_white_24dp";
    int imagen;
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
        // Retrieves an image specified by the URL, displays it in the UI.

    }

    @Override
    public int getItemCount() {
        return listRecipe==null?0:listRecipe.size();
    }


    public class ViewHolderItem extends RecyclerView.ViewHolder {

        @BindView(R.id.rowrecyclermovie_img)
        ImageView imgMovie;
        @BindView(R.id.activity_gridlayout_txt)
        TextView txtItemRecycler;


        public ViewHolderItem(View itemView){
            super(itemView);
            ButterKnife.bind(this,itemView);
          //  iconfavorito.setTag(favoritoOff);
        /*    itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(mOnItemClickListener!=null){
                        mOnItemClickListener.setOnItemClickListener(listRecipe.get(getAdapterPosition()), getAdapterPosition());
                    }
                }
            });*/
        }

    }


/*
    OnItemClickListener mOnItemClickListener;
    public interface OnItemClickListener{
        public void setOnItemClickListener(Result movie, Integer indice);
    }

    public void setOnItemClickListener(OnItemClickListener mItemClickListener){
        this.mOnItemClickListener = mItemClickListener;
    }

*/

}