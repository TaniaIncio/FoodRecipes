package com.tincio.foodrecipes.dominio.callback;

import android.arch.lifecycle.LiveData;

import com.tincio.foodrecipes.data.service.response.StepResponse;

import java.util.List;

/**
 * Created by juan on 28/05/2017.
 */

public interface StepCallback {
    void onResponse(LiveData<List<StepResponse>> responseSteps, String... mensajes);
}
