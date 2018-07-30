package com.example.komsic.cryptoconverter.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.komsic.cryptoconverter.R;

public class TestingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);

        int resID = getResources().getIdentifier("ic_euro", "drawable", getPackageName());

    }
}
