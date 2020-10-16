package com.example.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class OneColorAdjustmentActivity extends AppCompatActivity {

    private int COLOR_ADJUSTMENT_VALUE = 50;
    private MyColorEnum DEFAULT_CHECKED_COLOR = MyColorEnum.GREEN;


    View.OnClickListener radioButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            RadioButton rb = (RadioButton)v;
            switch (rb.getId()) {
                case R.id.radio_red: adjustOneColor(MyColorEnum.RED, COLOR_ADJUSTMENT_VALUE);
                    break;
                case R.id.radio_green: adjustOneColor(MyColorEnum.GREEN, COLOR_ADJUSTMENT_VALUE);
                    break;
                case R.id.radio_blue: adjustOneColor(MyColorEnum.BLUE, COLOR_ADJUSTMENT_VALUE);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_color_adjustment);
        this.setUpRadioButtons();
        this.adjustOneColor(DEFAULT_CHECKED_COLOR, COLOR_ADJUSTMENT_VALUE);
    }

    private void setUpRadioButtons() {
        RadioButton buttonRed = findViewById(R.id.radio_red);
        RadioButton buttonGreen = findViewById(R.id.radio_green);
        RadioButton buttonBlue = findViewById(R.id.radio_blue);

        buttonRed.setOnClickListener(radioButtonClickListener);
        buttonGreen.setOnClickListener(radioButtonClickListener);
        buttonBlue.setOnClickListener(radioButtonClickListener);

        switch (DEFAULT_CHECKED_COLOR) {
            case RED: buttonRed.setChecked(true); break;
            case GREEN: buttonGreen.setChecked(true); break;
            case BLUE: buttonBlue.setChecked(true); break;
        }
    }

    private void adjustOneColor(MyColorEnum color, int value) {
        Bitmap src = BitmapFactory.decodeResource(getResources(), R.mipmap.dream_car_foreground);
        int width = src.getWidth();
        int height = src.getHeight();
        int[] srcPixels = new int[width * height];
        src.getPixels(srcPixels, 0, width, 0, 0, width, height);
        int[] dstPixels = new int[width * height];
        adjustPixels(srcPixels, dstPixels, color, value);

        ImageView dstImageView = findViewById(R.id.dst);
        Bitmap dstBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        dstBitmap.setPixels(dstPixels, 0, width, 0, 0, width, height);
        dstImageView.setImageBitmap(dstBitmap);
        src.recycle();
    }

    private void adjustPixels(int[] src, int[] dst, MyColorEnum color, int value) {
        for (int i = 0; i < src.length; i++) {
            MyColor srcColor = new MyColor(src[i]);
            switch (color) {
                case RED: srcColor.setR(srcColor.getR() + value); break;
                case GREEN: srcColor.setG(srcColor.getG() + value); break;
                case BLUE: srcColor.setB(srcColor.getB() + value); break;
            }
            dst[i] = srcColor.toInt();
        }
    }
}