package com.example.mkr;

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

    public void onTask1(View v) {
        Intent in = new Intent();
        startCertainActivity(in, AstroidaActivity.class);
    }

    public void onTask2(View v) {
        Intent in = new Intent();
        startCertainActivity(in, Task2Activity.class);
    }

    private void startCertainActivity(Intent in, Class<?> cls) {
        in.setClass(this, cls);
        startActivity(in);
    }
}