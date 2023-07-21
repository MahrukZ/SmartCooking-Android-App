package com.example.mob_dev_portfolio.UI;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mob_dev_portfolio.R;
import com.example.mob_dev_portfolio.adaptors.FavouriteAdaptor;
import com.example.mob_dev_portfolio.db.SmartCooking;
import com.example.mob_dev_portfolio.db.entities.Favourites;
import java.util.ArrayList;
import java.util.List;

public class FavouriteFragment extends Fragment {

    List<Favourites> favouritesList;
    RecyclerView recyclerView;
    FavouriteAdaptor favouriteAdaptor;
    SmartCooking smartCooking;
    ImageView imageError;
    TextView tvErrorMessage;
    
    public FavouriteFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        smartCooking = SmartCooking.getInstance(getContext());
        favouritesList = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_favourite, container, false);
        recyclerView = v.findViewById(R.id.favorites_recycleview);
        tvErrorMessage = v.findViewById(R.id.tvErrorMesssage);
        imageError = v.findViewById(R.id.imageError);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        checkIfFavouritesExist();
        return v;
    }

    private void checkIfFavouritesExist(){
        favouritesList = smartCooking.getFavouritesDAO().getAllFavourites();
        if(favouritesList.isEmpty()){
            tvErrorMessage.setVisibility(View.VISIBLE);
            imageError.setVisibility(View.VISIBLE);
        }
        else{
            tvErrorMessage.setVisibility(View.GONE);
            imageError.setVisibility(View.GONE);
            getFavourites();
        }
    }

    private void getFavourites(){
        favouritesList = smartCooking.getFavouritesDAO().getAllFavourites();
        favouriteAdaptor = new FavouriteAdaptor(favouritesList, new FavouriteAdaptor.OnFavouriteDeleteClickListener() {
            @Override
            public void onDeleteClick(Favourites favourites) {
                smartCooking.getFavouritesDAO().deleteFavourite(favourites);
                checkIfFavouritesExist();
            }
        }, favourites -> {
            OpenRecipeDetails(favourites);
        });
        recyclerView.setAdapter(favouriteAdaptor);
        favouriteAdaptor.notifyDataSetChanged();
    }

    private void OpenRecipeDetails(Favourites favourites){
        SharedPreferences sharedPref = getActivity().getSharedPreferences("recipeId", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("recipeId", favourites.getRecipeId());
        editor.apply();
        RecipeFragment recipeFragment = new RecipeFragment();
        getFragmentManager().beginTransaction().replace(R.id.flFragment, recipeFragment).commit();
    }
}