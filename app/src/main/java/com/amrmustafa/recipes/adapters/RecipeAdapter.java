package com.amrmustafa.recipes.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SortedList;


import com.amrmustafa.recipes.ui.ActivityRecipesDetail;
import com.amrmustafa.recipes.R;
import com.amrmustafa.recipes.model.Recipe;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipesViewHolder> implements Filterable {
    private ArrayList<Recipe> mList = new ArrayList<>();
    private ArrayList<Recipe> recipeListFiltered = new ArrayList<>();
    private RecipeAdapterListener listener;
    private Context mContext;

    public RecipeAdapter(Context mContext) {
        this.mContext = mContext;
    }
    public RecipeAdapter(Context context, ArrayList<Recipe> recipeList, RecipeAdapterListener listener) {
        this.mContext = context;
        this.listener = listener;
        this.mList = recipeList;
        this.recipeListFiltered = recipeList;
    }

    @NonNull
    @Override
    public RecipesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecipesViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecipesViewHolder holder, int position) {
        holder.recipeName.setText(recipeListFiltered.get(position).getName());
        Glide.with(mContext).load(recipeListFiltered.get(position).getthumb())
                .into(holder.recipeImage);



        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), ActivityRecipesDetail.class);

                intent.putExtra("name", mList.get(position).getName());
                intent.putExtra("calories", mList.get(position).getCalories());
                intent.putExtra("carbos", mList.get(position).getCarbos());
                intent.putExtra("description", mList.get(position).getDescription());
                intent.putExtra("fats", mList.get(position).getFats());
                intent.putExtra("headline", mList.get(position).getHeadline());
                intent.putExtra("proteins", mList.get(position).getProteins());
                intent.putExtra("time", mList.get(position).getTime());
                intent.putExtra("image", mList.get(position).getImage());

                v.getContext().startActivity(intent);


            }
        });


    }





    public void setList(ArrayList<Recipe> mList) {
        Collections.sort(mList);
        this.mList = mList;
        this.recipeListFiltered = mList;
        notifyDataSetChanged();
    }


    public class RecipesViewHolder extends RecyclerView.ViewHolder {
        private ImageView recipeImage;
        private TextView recipeName;
        private RelativeLayout card;
        public RecipesViewHolder(@NonNull View itemView) {
            super(itemView);
            recipeImage = itemView.findViewById(R.id.recipe_imageView);
            recipeName = itemView.findViewById(R.id.recipe_name_textView);
            card = itemView.findViewById(R.id.holder);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onRecipeSelected(recipeListFiltered.get(getAdapterPosition()));
                }
            });

        }
    }






    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    recipeListFiltered = mList;
                } else {
                    ArrayList<Recipe> filteredList = new ArrayList<>();
                    for (Recipe row : mList) {

                        if (row.getName().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }

                    recipeListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = recipeListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                recipeListFiltered = (ArrayList<Recipe>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
    @Override
    public int getItemCount() {
        return recipeListFiltered.size();
    }



    public interface RecipeAdapterListener {
        void onRecipeSelected(Recipe recipe);
    }

}
