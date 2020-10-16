package com.amrmustafa.recipes.repository;

import com.amrmustafa.recipes.model.Recipe;
import com.amrmustafa.recipes.network.RecipeApiService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;


public class Repository {
    private RecipeApiService recipeApiService;

    @Inject
    public Repository(RecipeApiService recipeApiService) {
        this.recipeApiService = recipeApiService;
    }

    public Observable<List<Recipe>> getRecipes(){
        return recipeApiService.getRecipes();
    }
}
