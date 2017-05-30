package com.tincio.foodrecipes.data.service;

import android.arch.lifecycle.MutableLiveData;

import com.tincio.foodrecipes.dominio.callback.StepCallback;
import com.tincio.foodrecipes.data.service.Constants;
import com.tincio.foodrecipes.data.service.WebService;
import com.tincio.foodrecipes.data.service.response.StepResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by juan on 28/05/2017.
 */

public class StepRepository {
    private StepCallback callback;

    public StepRepository( StepCallback callback) {
        // this.webservice = webservice;
        this.callback = callback;
    }
    //LiveData<List<Step>>
    public void getStep(String StepId) {
        refreshStep(StepId);
        // return a LiveData directly from the database.
        ///return StepDao.load();
    }

    private void refreshStep(final String stepId) {
        //.client(httpClient.build())
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.serviceNames.BASE_RECIPES)
                .build();
        try {
            final MutableLiveData<List<StepResponse>> data = new MutableLiveData<>();
            WebService service = retrofit.create(WebService.class);
            Call<List<StepResponse>> call = service.geSteps(stepId);
            System.out.println("call url "+call.request().url());
            call.enqueue(new Callback<List<StepResponse>>() {
                @Override
                public void onResponse(Call<List<StepResponse>> call, Response<List<StepResponse>> response) {
                    data.setValue(response.body());
                    callback.onResponse(data,"");
                }

                @Override
                public void onFailure(Call<List<StepResponse>> call, Throwable t) {
                    callback.onResponse(null,t.getMessage());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
