package com.jhbb.baking_time.view.ui.widget;

import android.content.Context;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.jhbb.baking_time.R;
import com.jhbb.baking_time.model.AppDatabase;
import com.jhbb.baking_time.model.entity.IngredientModel;

import java.util.List;

public class RecipeWidgetRemoteViewFactory implements RemoteViewsService.RemoteViewsFactory {

    private Context mContext;
    private List<IngredientModel> mList;

    public RecipeWidgetRemoteViewFactory(Context context) {
        mContext = context;
    }

    @Override
    public int getCount()
    {
        return mList.size();
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public RemoteViews getLoadingView()
    {
        return null;
    }

    @Override
    public RemoteViews getViewAt(int position)
    {
        RemoteViews remoteView = new RemoteViews(mContext.getPackageName(),
                R.layout.widget_list_item);

        remoteView.setTextViewText(R.id.widget_recipe_ingredients_text_view, mList.get(position).getIngredient());

        return remoteView;
    }

    @Override
    public int getViewTypeCount()
    {
        return 1;
    }

    @Override
    public boolean hasStableIds()
    {
        return false;
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onDataSetChanged() {
        mList = AppDatabase.getInstance(mContext).ingredientDao().getIngredients();
    }

    @Override
    public void onDestroy() {

    }
}
