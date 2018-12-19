package com.jhbb.baking_time.utils;

import com.jhbb.baking_time.R;

public class RecipeImageUtils {

    private static final int BROWNIE_IMAGE = R.drawable.brownie;
    private static final int YELLOW_CAKE_IMAGE = R.drawable.yellow_cake;
    private static final int CAKE_IMAGE = R.drawable.cheesecake;
    private static final int PIE_IMAGE = R.drawable.pie;

    public static int getBrownieImage(String recipeName) {
        int recipeId = 0;

        switch (recipeName) {
            case "Brownies":
                recipeId = BROWNIE_IMAGE;
                break;
            case "Nutella Pie":
                recipeId = PIE_IMAGE;
                break;
            case "Yellow Cake":
                recipeId = YELLOW_CAKE_IMAGE;
                break;
            case "Cheesecake":
                recipeId = CAKE_IMAGE;
                break;
        }

        return recipeId;
    }
}
