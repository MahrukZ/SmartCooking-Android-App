package com.example.mob_dev_portfolio.utils;

public class ApiTags {

    public static final String BASE_URL= "https://api.spoonacular.com/";
    private static final String API_KEY = "a3e10887e03c4a65ba99ad4fa7acc014";

    public static final String recipesURL(String ingredients){
        return BASE_URL + "recipes/findByIngredients?ingredients=" + ingredients + "&apiKey=" + API_KEY;
    }

    public static String recipeDetailsURL(int recipeId){
        return BASE_URL + "recipes/" + recipeId + "/information?apiKey=" + API_KEY;
    }

}
