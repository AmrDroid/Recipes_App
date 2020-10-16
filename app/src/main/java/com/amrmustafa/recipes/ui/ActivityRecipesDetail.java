package com.amrmustafa.recipes.ui;


import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.amrmustafa.recipes.R;
import com.bumptech.glide.Glide;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;


public class ActivityRecipesDetail extends AppCompatActivity {

    TextView calories,carbos,description,fats,headline,name,proteins,time;
    ImageView image;


    final Context context = this;
    CollapsingToolbarLayout collapsingToolbarLayout;
    private AppBarLayout appBarLayout;
    ProgressBar progressBar;
    static final String TAG = "RecipesDetail";
    CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_recipes_detail);

        calories=(TextView) findViewById(R.id.calories);
        carbos=(TextView) findViewById(R.id.carbos);
        description=(TextView) findViewById(R.id.description);
        fats=(TextView) findViewById(R.id.fats);
        headline=(TextView) findViewById(R.id.headline);
        name=(TextView) findViewById(R.id.name);
        proteins=(TextView) findViewById(R.id.proteins);
        time=(TextView) findViewById(R.id.time);
        image=(ImageView)findViewById(R.id.image) ;
        calories.setText("calories : "+getIntent().getStringExtra("calories"));
        carbos.setText("carbos : "+getIntent().getStringExtra("carbos"));
        description.setText(""+getIntent().getStringExtra("description"));
        fats.setText("fats : "+getIntent().getStringExtra("fats"));
        headline.setText(""+getIntent().getStringExtra("headline"));
        name.setText(""+getIntent().getStringExtra("name"));
        proteins.setText("proteins : "+getIntent().getStringExtra("proteins"));
        time.setText(""+getIntent().getStringExtra("time"));


        Glide.with(this).load(getIntent().getStringExtra("image"))
                .into(image);


    }

}
