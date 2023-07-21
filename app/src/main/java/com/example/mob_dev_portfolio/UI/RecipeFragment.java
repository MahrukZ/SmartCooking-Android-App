package com.example.mob_dev_portfolio.UI;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.mob_dev_portfolio.R;
import com.example.mob_dev_portfolio.adaptors.IngredientAdaptor;
import com.example.mob_dev_portfolio.db.SmartCooking;
import com.example.mob_dev_portfolio.db.entities.Favourites;
import com.example.mob_dev_portfolio.model.Ingredient;
import com.example.mob_dev_portfolio.utils.ApiTags;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class RecipeFragment extends Fragment {

    ImageView imageView, vegetarian, imgHealth;
    TextView tvTitle, tvInstructions, readyTime, servingsSize, healthy;
    FloatingActionButton btnFavorites;
    private List<Ingredient> ingredientsList = new ArrayList<Ingredient>();
    private JSONArray ingredientsArr;
    private RecyclerView recyclerView;
    SharedPreferences sharedPreferences;
    String ingredients;
    SmartCooking smartCooking;
    String recipeTitle, recipeImageURL;
    private int isFavourite;

    public RecipeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getActivity().getSharedPreferences("userInputIngredients", MODE_PRIVATE);
        ingredients = sharedPreferences.getString("userInputIngredients","");
        smartCooking = SmartCooking.getInstance(getContext());
        isFavourite = smartCooking.getFavouritesDAO().isFavorite(getRecipeId());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_recipe, container, false);
        btnFavorites = v.findViewById(R.id.btnFavorites);
        imageView = v.findViewById(R.id.recipeImage);
        imgHealth = v.findViewById(R.id.imgHealth);
        healthy = v.findViewById(R.id.tvHealth);
        vegetarian = v.findViewById(R.id.imgVegetarian);
        tvTitle = v.findViewById(R.id.tvTitle);
        tvInstructions = v.findViewById(R.id.tvInstructions);
        readyTime = v.findViewById(R.id.tvTime);
        servingsSize = v.findViewById(R.id.tvServing);
        recyclerView = v.findViewById(R.id.recipe_ingredients_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        getRecipeDetails(getRecipeId());
        setFavouriteIcon();
        addToFavourites();
        return v;
    }

    private void setFavouriteIcon(){
        if(isFavourite == 1){
            btnFavorites.setImageResource(R.drawable.ic_favourite_selected);
        }
        else{
            btnFavorites.setImageResource(R.drawable.ic_favorite_unselected);
        }
    }

    private void addToFavourites(){
        btnFavorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isFavourite == 1){
                    smartCooking.getFavouritesDAO().deleteFavouriteById(getRecipeId());
                    btnFavorites.setImageResource(R.drawable.ic_favorite_unselected);
                }
                else{
                    Favourites favourites = new Favourites(getRecipeId(), recipeTitle, recipeImageURL);
                    smartCooking.getFavouritesDAO().addFavourite(favourites);
                    btnFavorites.setImageResource(R.drawable.ic_favourite_selected);
                }
                isFavourite = smartCooking.getFavouritesDAO().isFavorite(getRecipeId());
            }
        });
    }

    private void getRecipeDetails(int recipeId){
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, ApiTags.recipeDetailsURL(recipeId), null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            try {
                                Picasso.get().load((String) response.get("image")).into(imageView);
                            }
                            catch (Exception e){
                                imageView.setImageResource(R.drawable.ic_error_menu);
                            }
                            recipeTitle = response.get("title").toString();
                            recipeImageURL = response.get("image").toString();

                            tvTitle.setText(response.get("title").toString());
                            readyTime.setText(response.get("readyInMinutes").toString());
                            servingsSize.setText(response.get("servings").toString());
                            if ((boolean) response.get("veryHealthy")) {
                                healthy.setText(R.string.healthyText);
                                imgHealth.setImageResource(R.drawable.ic_health);
                            }
                            if ((boolean) response.get("vegetarian")) {
                                vegetarian.setImageResource(R.drawable.vegetarian);
                            }
                            try {
                                if(response.get("instructions").equals("")){
                                    throw new Exception("No instructions");
                                }
                                else{
                                    tvInstructions.setText(Html.fromHtml((String) response.get("instructions")));
                                }

                            } catch (Exception e) {
                                String msg= "Unfortunately, the recipe you were looking for not found, to view the original recipe click on the link below:" + "<a href="+response.get("spoonacularSourceUrl")+">"+response.get("spoonacularSourceUrl")+"</a>";
                                tvInstructions.setMovementMethod(LinkMovementMethod.getInstance());
                                tvInstructions.setText(Html.fromHtml(msg));
                            }

                            ingredientsArr = (JSONArray) response.get("extendedIngredients");
                            for (int i = 0; i < ingredientsArr.length(); i++) {
                                JSONObject jsonObject;
                                jsonObject = ingredientsArr.getJSONObject(i);
                                String presentIngredients = jsonObject.getString("original");
                                ingredientsList.add(new Ingredient(presentIngredients));

                            }

                            IngredientAdaptor myAdapter = new IngredientAdaptor(ingredientsList, new IngredientAdaptor.OnIngredientClickListener() {
                                @Override
                                public void onIngredientClick(Ingredient ingredient) {
                                    FragmentTransaction transaction = getFragmentManager()
                                            .beginTransaction();
                                    BottomSheetFragment.bottomSheetFragment(ingredient.getId(), ingredient.getName()).show(transaction, "bottomsheet");
                                }
                            });
                            recyclerView.setAdapter(myAdapter);
                            ViewCompat.setNestedScrollingEnabled(recyclerView, false);
                            myAdapter.notifyDataSetChanged();
                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("exception:", error.toString());

                    }
                }
        );
        requestQueue.add(jsonObjectRequest);
    }

    private int getRecipeId(){
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("recipeId", MODE_PRIVATE);
        int recipeId = sharedPreferences.getInt("recipeId",0);
        return recipeId;
    }
}