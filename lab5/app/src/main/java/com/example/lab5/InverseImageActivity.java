package com.example.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

public class InverseImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inverse_image);
        this.invertSecondImage();
    }

    private void invertSecondImage() {
        Bitmap src = BitmapFactory.decodeResource(getResources(), R.mipmap.tesla);
        int width = src.getWidth();
        int height = src.getHeight();
        int[] srcPixels = new int[width * height];
        src.getPixels(srcPixels, 0, width, 0, 0, width, height);
        int[] dstPixels = new int[width * height];
        this.invertPixels(srcPixels, dstPixels);

        ImageView dstImageView = findViewById(R.id.dst);
        Bitmap dstBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        dstBitmap.setPixels(dstPixels, 0, width, 0, 0, width, height);
        dstImageView.setImageBitmap(dstBitmap);
        src.recycle();
    }

    private void invertPixels(int[] src, int[] dst) {
        for (int i = 0; i < src.length; i++) {
            MyColor srcColor = new MyColor(src[i]);
            int newR = 255 - srcColor.getR();
            int newG = 255 - srcColor.getG();
            int newB = 255 - srcColor.getB();
            dst[i] = new MyColor(newR, newG, newB).toInt();
        }
    }
}