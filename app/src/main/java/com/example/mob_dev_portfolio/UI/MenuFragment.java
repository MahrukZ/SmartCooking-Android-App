package com.example.mob_dev_portfolio.UI;

import static android.content.Context.MODE_PRIVATE;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.mob_dev_portfolio.R;
import com.example.mob_dev_portfolio.adaptors.RecipeListViewAdaptor;
import com.example.mob_dev_portfolio.model.Recipe;
import com.example.mob_dev_portfolio.utils.ApiTags;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;


public class MenuFragment extends Fragment implements RecipeListViewAdaptor.OnRecipeClickListener {

    RecyclerView recyclerView;
    List<Recipe> recipeList = new ArrayList<>();
    RecipeListViewAdaptor recipeListViewAdaptor;
    Context context;
    ImageView imageError;
    TextView tvErrorMessage;

    public MenuFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_menu, container, false);
        recyclerView = v.findViewById(R.id.recipesList);
        this.recipeListViewAdaptor = new RecipeListViewAdaptor(context, recipeList, this);
        recyclerView.setAdapter(this.recipeListViewAdaptor);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        tvErrorMessage = v.findViewById(R.id.tvErrorMesssage);
        imageError = v.findViewById(R.id.imageError);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("userInputIngredients", MODE_PRIVATE);
        String ingredientsList = sharedPreferences.getString("userInputIngredients","");
        if(ingredientsList.isEmpty()){
            tvErrorMessage.setVisibility(View.VISIBLE);
            imageError.setVisibility(View.VISIBLE);
        }
        else{
            tvErrorMessage.setVisibility(View.GONE);
            imageError.setVisibility(View.GONE);
            getRecipe(ingredientsList);
        }
        return v;
    }

    public void getRecipe(String ingredientList){
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, ApiTags.recipesURL(ingredientList), null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.e("message", response.toString());
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject recipeObject = response.getJSONObject(i);

                                Recipe recipe = new Recipe();
                                recipe.setId(recipeObject.getInt("id"));
                                recipe.setTitle(recipeObject.getString("title").toString());
                                recipe.setThumbnail(recipeObject.getString("image").toString());
                                recipeList.add(recipe);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                        recipeListViewAdaptor.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Log.e("Error on get request", error.toString());
                        Log.d("myTag", "error in fetching recipe details");
                    }
                });

        requestQueue.add(jsonArrayRequest);

    }

    @Override
    public void onRecipeClick(Recipe recipe) {
        SharedPreferences sharedPref = getActivity().getSharedPreferences("recipeId", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("recipeId", recipe.getId());
        editor.apply();
        RecipeFragment recipeFragment = new RecipeFragment();
        getFragmentManager().beginTransaction().replace(R.id.flFragment, recipeFragment).commit();
    }


}