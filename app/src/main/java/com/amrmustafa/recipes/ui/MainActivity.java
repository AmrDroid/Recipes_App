package com.amrmustafa.recipes.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import com.amrmustafa.recipes.R;
import com.amrmustafa.recipes.adapters.RecipeAdapter;
import com.amrmustafa.recipes.model.Recipe;
import com.amrmustafa.recipes.viewmodels.RecipeViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity  implements RecipeAdapter.RecipeAdapterListener{

    private RecipeViewModel viewModel;
    private RecyclerView recyclerView;
    private RecipeAdapter adapter;
    ArrayList<Recipe> recipesli;
    public static int SORT_BY=0;
    private ArrayList<Recipe> recipeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        SharedPreferences prefs = getSharedPreferences("SORT_BY", MODE_PRIVATE);
        SORT_BY = prefs.getInt("sorted", 0);



        recyclerView = findViewById(R.id.pokemon_recyclerView);
        recipeList = new ArrayList<>();
        adapter = new RecipeAdapter(this, recipeList, this);


        recyclerView.setAdapter(adapter);


        viewModel = new ViewModelProvider(this).get(RecipeViewModel.class);

        viewModel.getRecipes();


        viewModel.getRecipeList().observe(this, new Observer<ArrayList<Recipe>>() {
            @Override
            public void onChanged(ArrayList<Recipe> recipeslist) {
                Collections.sort(recipeslist);
                adapter.setList(recipeslist);
                recipesli=recipeslist;
            }
        });


    }





    private SearchView searchView;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search)
                .getActionView();
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        // listening to search query text change
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter recycler view when query submitted
                adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                adapter.getFilter().filter(query);
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        SharedPreferences.Editor editor = getSharedPreferences("SORT_BY", MODE_PRIVATE).edit();

        switch (item.getItemId()) {
            case R.id.action_search:
                return true;
            case R.id.sortbycal:
                SORT_BY=0;
                editor.putInt("sorted",SORT_BY );
                editor.apply();

                adapter.setList(recipesli);
                return true;
            case R.id.sortbyfat:
                SORT_BY=1;
                editor.putInt("sorted",SORT_BY );
                editor.apply();

                adapter.setList(recipesli);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
      }



    @Override
    public void onRecipeSelected(Recipe recipe) {

    }
}