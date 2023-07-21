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
import com.example.mob_dev_portfolio.model.Ingredient;
import java.util.List;

public class IngredientAdaptor extends RecyclerView.Adapter<IngredientAdaptor.ViewHolder> {

    List<Ingredient> ingredientList;
    OnIngredientClickListener onIngredientClickListener;

    public IngredientAdaptor(List<Ingredient> ingredientList, OnIngredientClickListener onIngredientClickListener) {
        this.ingredientList = ingredientList;
        this.onIngredientClickListener = onIngredientClickListener;
    }

    public interface OnIngredientClickListener{
        void onIngredientClick(Ingredient ingredient);
    }

    @NonNull
    @Override
    public IngredientAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View photoView = inflater.inflate(R.layout.ingredients_list_item, parent, false);
        return new IngredientAdaptor.ViewHolder(photoView);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientAdaptor.ViewHolder holder, int position) {
        holder.tvIngredientTitle.setText(ingredientList.get(position).getName());
        holder.imgConfirmation.setOnClickListener(view -> {
            onIngredientClickListener.onIngredientClick(ingredientList.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return ingredientList.size();
    }

    public  class ViewHolder extends  RecyclerView.ViewHolder{
        TextView tvIngredientTitle;
        ImageView imgConfirmation;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvIngredientTitle = itemView.findViewById(R.id.tvIngredientsTitle);
            imgConfirmation = itemView.findViewById(R.id.imgConfirmation);
        }
    }
}
