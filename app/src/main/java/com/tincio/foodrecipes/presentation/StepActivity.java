package com.tincio.foodrecipes.presentation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tincio.foodrecipes.R;

public class StepActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step);
        this.changeFragment();
    }

    private void changeFragment(){
        if(getResources().getBoolean(R.bool.isTablet)) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_base, new StepFragment()).commit();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_base_tablet, new IngredientFragment()).commit();
        }else
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_base_step, new StepFragment()).commit();
    }
}
