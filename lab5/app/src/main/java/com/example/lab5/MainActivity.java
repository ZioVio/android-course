package com.example.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button inverseButton;
    Button oneColorAdjustmentButton;
    Button splitIntoColorsButton;
    Button combineIntoTwoImagesButton;
    Button matrixButton;
    Button watermarkButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        this.initViews();
    }

    private void initViews() {
        this.inverseButton = findViewById(R.id.inverse_button);
        this.oneColorAdjustmentButton = findViewById(R.id.one_color_adjustment_button);
        this.splitIntoColorsButton = findViewById(R.id.split_into_three_colors_button);
        this.combineIntoTwoImagesButton = findViewById(R.id.combine_two_images_button);
        this.matrixButton = findViewById(R.id.matrix_button);
        this.watermarkButton = findViewById(R.id.watermark_button);
    }


    public void onInverseClick(View v) {
        Intent in = new Intent();
        startCertainActivity(in, InverseImageActivity.class);
    }

    public void onOneColorAdjustmentClick(View v) {
        Intent in = new Intent();
        startCertainActivity(in, OneColorAdjustmentActivity.class);
    }

    public void onSplitIntoColorsClick(View v) {
        Intent in = new Intent();
        startCertainActivity(in, SplitIntoColorsActivity.class);
    }

    public void onCombineImagesClicked(View v) {
        Intent in = new Intent();
        startCertainActivity(in, CombineImagesActivity.class);
    }

    private void startCertainActivity(Intent in, Class<?> cls) {
        in.setClass(this, cls);
        startActivity(in);
    }

}