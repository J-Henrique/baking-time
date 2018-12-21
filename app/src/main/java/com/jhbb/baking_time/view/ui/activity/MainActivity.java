package com.jhbb.baking_time.view.ui.activity;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.jhbb.baking_time.R;
import com.jhbb.baking_time.model.AppDatabase;
import com.jhbb.baking_time.model.entity.IngredientModel;
import com.jhbb.baking_time.model.entity.RecipeModel;
import com.jhbb.baking_time.view.ui.fragment.RecipesListFragment;
import com.jhbb.baking_time.view.ui.widget.RecipeWidget;

import org.parceler.Parcels;

import java.util.List;

public class MainActivity extends AppCompatActivity
        implements RecipesListFragment.OnRecipeClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    public static AppDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDb = AppDatabase.getInstance(getApplicationContext());
    }

    @Override
    public void onRecipeClickListener(final RecipeModel recipeModel) {
        Log.v(TAG, "item clicado: " + recipeModel);

        Intent startDetailsActivity = new Intent(this, DetailsActivity.class);
        startDetailsActivity.putExtra("recipeModel", Parcels.wrap(recipeModel));

        Intent updateWidget = new Intent(this, RecipeWidget.class);
        updateWidget.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);

        Intent intent = new Intent(this, RecipeWidget.class);
        intent.setAction("android.appwidget.action.APPWIDGET_UPDATE");
        int ids[] = AppWidgetManager.getInstance(getApplication()).getAppWidgetIds(
                new ComponentName(getApplication(), RecipeWidget.class));
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids);
        intent.putExtra("RECIPE", Parcels.wrap(recipeModel));

        new AsyncTask<List<IngredientModel>, Void, Void>() {
            @Override
            protected Void doInBackground(List<IngredientModel>... lists) {
                mDb.ingredientDao().deleteIngredients();
                mDb.ingredientDao().insertIngredients(recipeModel.getIngredients());
                return null;
            }
        }.execute();

        sendBroadcast(intent);
        startActivity(startDetailsActivity);
    }
}
