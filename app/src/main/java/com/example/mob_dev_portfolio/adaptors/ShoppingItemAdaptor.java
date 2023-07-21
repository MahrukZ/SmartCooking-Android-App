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
import com.example.mob_dev_portfolio.db.entities.Item;
import java.util.List;

public class ShoppingItemAdaptor extends RecyclerView.Adapter<ShoppingItemAdaptor.ViewHolder> {

    List<Item> itemList;
    private OnDeleteClickListener onDeleteClickListener;

    public ShoppingItemAdaptor(List<Item> itemList, OnDeleteClickListener onDeleteClickListener){
        this.itemList = itemList;
        this.onDeleteClickListener = onDeleteClickListener;
    }

    public interface OnDeleteClickListener {
        void onDeleteClick(Item item);
    }


    @NonNull
    @Override
    public ShoppingItemAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.shopping_list_item, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ShoppingItemAdaptor.ViewHolder holder, int position) {
        holder.tvItemName.setText(itemList.get(position).getName());
        holder.imgDelete.setOnClickListener(view -> {
            onDeleteClickListener.onDeleteClick(itemList.get(position));
            itemList.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position,itemList.size());
        });
    }


    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public  class ViewHolder extends  RecyclerView.ViewHolder{
        ImageView imgDelete;
        TextView tvItemName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgDelete = itemView.findViewById(R.id.bt_delete);
            tvItemName = itemView.findViewById(R.id.tvItemName);

        }
    }
}
