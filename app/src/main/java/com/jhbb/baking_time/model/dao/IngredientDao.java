package com.jhbb.baking_time.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.jhbb.baking_time.model.entity.IngredientModel;

import java.util.List;

@Dao
public interface IngredientDao {

    @Query("SELECT * FROM ingredients")
    List<IngredientModel> getIngredients();

    @Insert
    void insertIngredients(List<IngredientModel> ingredients);

    @Query("DELETE FROM ingredients")
    void deleteIngredients();
}
