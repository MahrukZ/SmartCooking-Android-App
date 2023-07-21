package com.example.mob_dev_portfolio.UI;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.example.mob_dev_portfolio.R;
import com.example.mob_dev_portfolio.db.entities.Item;
import com.example.mob_dev_portfolio.db.SmartCooking;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class BottomSheetFragment extends BottomSheetDialogFragment {

    TextView tvCancelItem;
    Button btnAddCart;
    public static final String ITEM_ID = "itemId";
    public static final String ITEM_NAME = "itemName";
    int itemId;
    String itemName;
    SmartCooking smartCooking;


    public static BottomSheetFragment bottomSheetFragment(int id, String name){
        BottomSheetFragment bottomSheetFragment = new BottomSheetFragment();
        Bundle getItemData = new Bundle();
        getItemData.putInt(ITEM_ID, id);
        getItemData.putString(ITEM_NAME, name);
        bottomSheetFragment.setArguments(getItemData);
        return bottomSheetFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        smartCooking = SmartCooking.getInstance(getContext());
        if(getArguments() != null){
            itemId = getArguments().getInt(ITEM_ID, 0);
            itemName = getArguments().getString(ITEM_NAME, "");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bottom_sheet, container, false);
        tvCancelItem = view.findViewById(R.id.tvCancelItems);
        btnAddCart = view.findViewById(R.id.btnAddItems);

        btnAddCart.setOnClickListener(v -> {
            Item item = new Item(itemName);
            smartCooking.getShoppingCartDAO().addItem(item);
            dismiss();
        });
        tvCancelItem.setOnClickListener(v -> {
            dismiss();
        });

        return view;
    }
}