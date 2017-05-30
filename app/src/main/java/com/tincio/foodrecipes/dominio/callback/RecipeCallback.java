package com.tincio.foodrecipes.dominio.callback;

import android.arch.lifecycle.LiveData;

import com.tincio.foodrecipes.data.model.Recipe;
import com.tincio.foodrecipes.data.service.response.RecipeResponse;

import java.util.List;

public interface RecipeCallback {

    void onResponse(LiveData<List<RecipeResponse>> responseMovies, String... mensajes);
}
