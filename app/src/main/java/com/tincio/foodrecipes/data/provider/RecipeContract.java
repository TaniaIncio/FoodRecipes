package com.tincio.foodrecipes.data.provider;

import android.net.Uri;
import android.provider.BaseColumns;

public class RecipeContract {

    public static final String AUTHORITY = "com.tincio.foodrecipes";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    public static final String PATH_RECIPES = "ingredient";

    public static final long INVALID_PLANT_ID = -1;

    public static final class RecipeEntry implements BaseColumns {

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_RECIPES).build();

        public static final String TABLE_NAME = "Ingredient";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_NAME = "ingredient";
    }
}
