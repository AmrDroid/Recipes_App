package com.amrmustafa.recipes.viewmodels;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.amrmustafa.recipes.model.Recipe;
import com.amrmustafa.recipes.repository.Repository;

import java.util.ArrayList;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RecipeViewModel extends ViewModel {

    private Repository repository;
    private MutableLiveData<ArrayList<Recipe>> recipeList = new MutableLiveData<>();

    @ViewModelInject
    public RecipeViewModel(Repository repository) {
        this.repository = repository;
    }

    public MutableLiveData<ArrayList<Recipe>> getRecipeList() {
        return recipeList;
    }


        @SuppressLint("CheckResult")
        public void getRecipes(){
            repository.getRecipes()
                    .subscribeOn(Schedulers.io())

                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(result -> recipeList.setValue((ArrayList<Recipe>) result),
                            error-> Log.e("viwModel", error.getMessage() ));
    }




}
