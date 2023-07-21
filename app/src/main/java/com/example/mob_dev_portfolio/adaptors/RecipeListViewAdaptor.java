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
import com.example.mob_dev_portfolio.model.Recipe;
import com.squareup.picasso.Picasso;
import java.util.List;

public class RecipeListViewAdaptor extends RecyclerView.Adapter<RecipeListViewAdaptor.ViewHolder>{

    private Context context;
    List<Recipe> recipeList;
    private OnRecipeClickListener onRecipeClickListener;

    public RecipeListViewAdaptor(Context context, List<Recipe> recipeList, OnRecipeClickListener onRecipeClickListener){
        this.recipeList = recipeList;
        this.context= context;
        this.onRecipeClickListener = onRecipeClickListener;
    }

    public interface OnRecipeClickListener{
        void onRecipeClick(Recipe recipe);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View photoView = inflater.inflate(R.layout.menu_list_item, parent, false);
        return new ViewHolder(photoView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeListViewAdaptor.ViewHolder holder, int position) {
        holder.recipeTitle.setText(recipeList.get(position).getTitle());
        Picasso.get().load(recipeList.get(position).getThumbnail()).into(holder.recipeImage);
        holder.itemView.setOnClickListener(view -> {
            onRecipeClickListener.onRecipeClick(recipeList.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    public  class ViewHolder extends  RecyclerView.ViewHolder{
        TextView recipeTitle;
        ImageView recipeImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            recipeTitle = itemView.findViewById(R.id.recipeTitle);
            recipeImage = itemView.findViewById(R.id.recipeImage);
        }
    }

}
