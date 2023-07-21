package com.example.mob_dev_portfolio.UI;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mob_dev_portfolio.R;
import com.example.mob_dev_portfolio.adaptors.ShoppingItemAdaptor;
import com.example.mob_dev_portfolio.db.SmartCooking;
import com.example.mob_dev_portfolio.db.entities.Item;

import java.util.ArrayList;
import java.util.List;

public class ShoppingFragment extends Fragment {

    List<Item> itemList;
    RecyclerView recyclerView;
    ShoppingItemAdaptor shoppingItemAdaptor;
    SmartCooking smartCooking;
    EditText etAddItems;
    Button btn_add, btn_clear;

    public ShoppingFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        smartCooking = SmartCooking.getInstance(getContext());
        itemList = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shopping, container, false);
        etAddItems = view.findViewById(R.id.etAddItems);
        btn_add = view.findViewById(R.id.bt_add);
        btn_clear = view.findViewById(R.id.bt_clear);
        recyclerView = view.findViewById(R.id.rvShoppingItems);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        btn_add.setOnClickListener(
                new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sText=etAddItems.getText().toString().trim();
                if(!sText.equals("")){
                    Item item = new Item();
                    item.setName(sText);
                    smartCooking.getShoppingCartDAO().addItem(item);
                    etAddItems.setText("");
                    itemList.clear();
                    itemList.addAll(smartCooking.getShoppingCartDAO().getAllItems());
                    shoppingItemAdaptor.notifyDataSetChanged();

                }
            }
        });

        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etAddItems.setText("");
            }
        });

        getDBItems();
        return view;
    }

    private void getDBItems(){
        itemList = smartCooking.getShoppingCartDAO().getAllItems();
        shoppingItemAdaptor = new ShoppingItemAdaptor(itemList, new ShoppingItemAdaptor.OnDeleteClickListener() {
            @Override
            public void onDeleteClick(Item item) {
                smartCooking.getShoppingCartDAO().deleteItem(item);
            }
        });
        recyclerView.setAdapter(shoppingItemAdaptor);
        shoppingItemAdaptor.notifyDataSetChanged();
    }

}