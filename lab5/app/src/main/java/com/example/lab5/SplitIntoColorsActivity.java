package com.example.lab5;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

public class SplitIntoColorsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_split_into_colors);
        this.createThreeImagesAndFillThem();
    }


    private void createThreeImagesAndFillThem() {
        Bitmap src = BitmapFactory.decodeResource(getResources(), R.mipmap.android_foreground);
        int width = src.getWidth();
        int height = src.getHeight();
        int[] srcPixels = new int[width * height];
        int[] bufferPixels = new int[srcPixels.length];
        src.getPixels(srcPixels, 0, width, 0, 0, width, height);

        getRedPicture(srcPixels, bufferPixels);
        setImagePixels(bufferPixels, R.id.redImage, width, height);

        getGreenPicture(srcPixels, bufferPixels);
        setImagePixels(bufferPixels, R.id.greenImage, width, height);

        getBluePicture(srcPixels, bufferPixels);
        setImagePixels(bufferPixels, R.id.blueImage, width, height);
        src.recycle();
    }

    private void getRedPicture(int[] src, int[] dst) {
        for (int i = 0; i < src.length; i++) {
            MyColor color = new MyColor(src[i]);
            color.setG(0);
            color.setB(0);
            dst[i] = color.toInt();
        }
    }

    private void getGreenPicture(int[] src, int[] dst) {
        for (int i = 0; i < src.length; i++) {
            MyColor color = new MyColor(src[i]);
            color.setR(0);
            color.setB(0);
            dst[i] = color.toInt();
        }
    }

    private void getBluePicture(int[] src, int[] dst) {
        for (int i = 0; i < src.length; i++) {
            MyColor color = new MyColor(src[i]);
            color.setR(0);
            color.setG(0);
            dst[i] = color.toInt();
        }
    }



    private void setImagePixels(int[] pixels, @IdRes int resId, int width, int height) {
        ImageUtils.setImagePixels(pixels, (ImageView) findViewById(resId), width, height);
    }
}