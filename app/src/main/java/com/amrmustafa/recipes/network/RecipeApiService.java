package com.amrmustafa.recipes.network;

import com.amrmustafa.recipes.model.Recipe;


import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;

public interface RecipeApiService {

    @GET("recipes.json")
    Observable<List<Recipe>> getRecipes();




}
