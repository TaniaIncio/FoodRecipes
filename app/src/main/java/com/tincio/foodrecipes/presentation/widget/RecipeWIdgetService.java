package com.tincio.foodrecipes.presentation.widget;

import android.app.IntentService;
import android.content.ContentValues;
import android.content.Intent;
import android.content.Context;
import android.net.Uri;

import com.tincio.foodrecipes.data.provider.RecipeContract;

import static com.tincio.foodrecipes.data.provider.RecipeContract.BASE_CONTENT_URI;
import static com.tincio.foodrecipes.data.provider.RecipeContract.PATH_RECIPES;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class RecipeWIdgetService extends IntentService {
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    public static final String ACTION_RECIPE = "com.tincio.foodrecipes.presentation.widget.action.RECPE";

    // TODO: Rename parameters
    /*private static final String EXTRA_PARAM1 = "com.tincio.foodrecipes.presentation.widget.extra.PARAM1";
    private static final String EXTRA_PARAM2 = "com.tincio.foodrecipes.presentation.widget.extra.PARAM2";*/

    public RecipeWIdgetService() {
        super("RecipeWIdgetService");
    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionRecipe(Context context) {
        Intent intent = new Intent(context, RecipeWIdgetService.class);
        intent.setAction(ACTION_RECIPE);
        context.startService(intent);
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_RECIPE.equals(action)) {
                /*final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);*/
                handleActionRecipe();
            }
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionRecipe() {
        // TODO: Handle action Foo
        /*throw new UnsupportedOperationException("Not yet implemented");*/
        Uri RECIPE_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_RECIPES).build();
        ContentValues contentValues = new ContentValues();
        long timeNow = System.currentTimeMillis();
    //    contentValues.put(RecipeContract.RecipeEntry.COLUMN_LAST_WATERED_TIME, timeNow);
        // Update only plants that are still alive
        try{
            getContentResolver().update(
                    RECIPE_URI,
                    contentValues,
                    null,//RecipeContract.PlantEntry.COLUMN_LAST_WATERED_TIME+">?"
                    null);// new String[]{String.valueOf(timeNow - PlantUtils.MAX_AGE_WITHOUT_WATER)}
        }catch (Exception e){
            System.out.println("esception "+e.getMessage());
        }

    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private void handleActionBaz(String param1, String param2) {
        // TODO: Handle action Baz
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
