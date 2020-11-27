package com.example.mkr;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class Task2Activity extends AppCompatActivity {

    int img1ResId = R.mipmap.mac_foreground;
    int img2ResId = R.mipmap.student_foreground;

    double alpha = 0.5;

    int width, height;

    Bitmap bm1, bm2;
    int[] pixels1, pixels2;

    ImageView imageView1, imageView2, result;
    TextView aplhaTextView;
    SeekBar seekBar;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task2);
        init();
        setupEventListeners();
        combineImages();
    }

    private void init() {
        this.imageView1 = findViewById(R.id.imageView1);
        this.imageView2 = findViewById(R.id.imageView2);
        this.result = findViewById(R.id.resultView);
        this.aplhaTextView = findViewById(R.id.aplhaTextView);

        bm1 = BitmapFactory.decodeResource(getResources(), img1ResId);
        bm2 = BitmapFactory.decodeResource(getResources(), img2ResId);
        this.imageView1.setImageBitmap(bm1);
        this.imageView2.setImageBitmap(bm2);

        width = bm1.getWidth();
        height = bm1.getHeight();

        pixels1 = new int[width * height];
        pixels2 = new int[pixels1.length];

        bm1.getPixels(pixels1, 0, width, 0, 0, width, height);
        bm2.getPixels(pixels2, 0, width, 0, 0, width, height);

        this.seekBar = findViewById(R.id.seekBar);
        this.seekBar.setProgress((int)(this.alpha * 100));
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void combineImages() {
        this.aplhaTextView.setText(Double.toString(alpha));
        int[] dst = new int[pixels1.length];
        for (int i = 0; i < pixels1.length; i++) {
            Color color1 = Color.valueOf(pixels1[i]);
            Color color2 = Color.valueOf(pixels2[i]);
            int r = (int)(color1.red() * alpha * 255) + (int)((1f - alpha) * color2.red() * 255);
            int g = (int)(color1.green() * alpha * 255) + (int)((1f - alpha) * color2.green() * 255);
            int b = (int)(color1.blue() * alpha * 255) + (int)((1f - alpha) * color2.blue() * 255);
            dst[i] = Color.argb(255, r, g, b);
        }

        Bitmap dstBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        dstBitmap.setPixels(dst, 0, width, 0, 0, width, height);
        result.setImageBitmap(dstBitmap);
    }


    private void setupEventListeners() {
        this.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                alpha = progress / 100.0;
                combineImages();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }
}