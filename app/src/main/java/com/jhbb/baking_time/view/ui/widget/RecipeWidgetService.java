package com.jhbb.baking_time.view.ui.widget;

import android.content.Intent;
import android.widget.RemoteViewsService;

public class RecipeWidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return (new RecipeWidgetRemoteViewFactory(this.getApplicationContext()));
    }
}
