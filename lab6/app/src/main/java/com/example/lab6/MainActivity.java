package com.example.lab6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onAnimationsClick(View v) {
        Intent in = new Intent();
        startCertainActivity(in, AnimationsActivity.class);
    }

    public void onCirclingSquareAClick(View v) {
        Intent in = new Intent();
        startCertainActivity(in, CirclingSquareActivity.class);
    }

    private void startCertainActivity(Intent in, Class<?> cls) {
        in.setClass(this, cls);
        startActivity(in);
    }
}