package com.jhbb.baking_time.view.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.jhbb.baking_time.R;
import com.jhbb.baking_time.model.RecipeModel;
import com.jhbb.baking_time.view.ui.fragment.RecipesListFragment;

import org.parceler.Parcels;

public class MainActivity extends AppCompatActivity
        implements RecipesListFragment.OnRecipeClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onRecipeClickListener(RecipeModel recipeModel) {
        Log.v(TAG, "item clicado: " + recipeModel);

        Intent startDetailsActivity = new Intent(this, DetailsActivity.class);
        startDetailsActivity.putExtra("recipeModel", Parcels.wrap(recipeModel));

        startActivity(startDetailsActivity);
    }
}
