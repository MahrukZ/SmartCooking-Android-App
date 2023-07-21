package com.example.mob_dev_portfolio.adaptors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mob_dev_portfolio.R;
import com.example.mob_dev_portfolio.db.entities.Favourites;
import com.squareup.picasso.Picasso;
import java.util.List;

public class FavouriteAdaptor extends RecyclerView.Adapter<FavouriteAdaptor.ViewHolder>{

    List<Favourites> favouritesList;
    private OnFavouriteDeleteClickListener onFavouriteDeleteClickListener;
    private OnFavouriteClickListener onFavouriteClickListener;


    public FavouriteAdaptor(List<Favourites> favouritesList, OnFavouriteDeleteClickListener onFavouriteDeleteClickListener, OnFavouriteClickListener onFavouriteClickListener){
        this.favouritesList = favouritesList;
        this.onFavouriteDeleteClickListener = onFavouriteDeleteClickListener;
        this.onFavouriteClickListener = onFavouriteClickListener;
    }
    public interface OnFavouriteDeleteClickListener{
        void onDeleteClick(Favourites favourites);
    }

    public interface OnFavouriteClickListener{
        void onFavouriteClick(Favourites favourites);
    }


    @NonNull
    @Override
    public FavouriteAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.favourite_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavouriteAdaptor.ViewHolder holder, int position) {
        holder.recipeName.setText(favouritesList.get(position).getName());
        if (favouritesList.get(position).getImageURL().isEmpty()) {
            holder.imgRecipe.setImageResource(R.drawable.vegetarian);
        } else{
            Picasso.get().load(favouritesList.get(position).getImageURL()).into(holder.imgRecipe);
        }
        holder.itemView.setOnClickListener(view -> {
            onFavouriteClickListener.onFavouriteClick(favouritesList.get(position));
        });

        holder.fav_btn.setOnClickListener(view ->
        {onFavouriteDeleteClickListener.onDeleteClick(favouritesList.get(position));
            favouritesList.remove(position);
            notifyItemRemoved(position);
        });
    }

    @Override
    public int getItemCount() {
        return favouritesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgRecipe, fav_btn;
        TextView recipeName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgRecipe = itemView.findViewById(R.id.img_recipe);
            recipeName = itemView.findViewById(R.id.recipe_name);
            fav_btn = itemView.findViewById(R.id.fav_btn);
        }
    }
}
