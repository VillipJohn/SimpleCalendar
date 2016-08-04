package com.example.villip.simplecalendar.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.villip.simplecalendar.R;

public class MainActivity extends AppCompatActivity implements MainHeaderFragment.OnFragmentHeaderInteractionListener{


    private static final String TAG = "myLogs";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // Log.d(TAG, "onCreate активити");
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onFragmentHeaderInteraction(String monthYearFromFragment) {
        MainContentFragment fragmentContent = (MainContentFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_content_in_activity);
        Log.d(TAG, "MainActivity onFragmentHeaderInteraction monthYearFromFragment = " + monthYearFromFragment);
        //if (fragment != null && fragment.isInLayout()) {

        fragmentContent.onSetupData(monthYearFromFragment);

    }


    public void onClickBtnPreviousMonth(View v){

    }

    public void onClickBtnNextMonth(View v){

    }
}
