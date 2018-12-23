package com.jhbb.baking_time.view.ui.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.jhbb.baking_time.R;
import com.jhbb.baking_time.view.ui.activity.MainActivity;

import org.parceler.Parcels;

/**
 * Implementation of App Widget functionality.
 */
public class RecipeWidget extends AppWidgetProvider {

    static String mRecipeName;
    private static final String EXTRA_RECIPE_NAME = "recipeName";

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {
        Intent startRecipeList = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, startRecipeList, PendingIntent.FLAG_UPDATE_CURRENT);

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.recipe_widget);
        views.setOnClickPendingIntent(R.id.bowl_icon_image_view, pendingIntent);

        if (mRecipeName != null) {
            views.setTextViewText(R.id.widget_recipe_name_text_view, mRecipeName);

            Intent intent = new Intent(context, RecipeWidgetService.class);
            views.setRemoteAdapter(R.id.widget_recipe_ingredients_list_view, intent);
        }

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetId, R.id.widget_recipe_ingredients_list_view);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getExtras() != null) {
            mRecipeName = intent.getExtras().getString(EXTRA_RECIPE_NAME);
        }

        super.onReceive(context, intent);
    }
}

