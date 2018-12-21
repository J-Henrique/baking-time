package com.jhbb.baking_time.model;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.jhbb.baking_time.model.dao.IngredientDao;
import com.jhbb.baking_time.model.entity.IngredientModel;

@Database(entities = {IngredientModel.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public static final String DB_NAME = "baking_time_db";

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context, AppDatabase.class, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }

    public abstract IngredientDao ingredientDao();
}
