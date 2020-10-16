package com.example.lab5;

import android.graphics.Bitmap;
import android.widget.ImageView;

import androidx.annotation.IdRes;

public class ImageUtils {

    public static void setImagePixels(int[] pixels, ImageView dstImageView, int width, int height) {
        Bitmap dstBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        dstBitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        dstImageView.setImageBitmap(dstBitmap);
    }

}
