package com.example.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class CombineImagesActivity extends AppCompatActivity {

    private Bitmap imageBitmap1, imageBitmap2;
    private TextView alphaTextView;
    private int width, height;
    private int[] pixels1, pixels2;

    private ImageView imageView;

    private float INITIAL_ALPHA = 0.3f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combine_images);
        this.initImages();
        this.initTextView();
        this.setSeekbarEventListener();
        this.onAlphaChange(INITIAL_ALPHA);
    }

    private void initImages() {
        imageBitmap1 = BitmapFactory.decodeResource(getResources(), R.mipmap.tesla);
        imageBitmap2 = BitmapFactory.decodeResource(getResources(), R.mipmap.dream_car_foreground);

        width = imageBitmap1.getWidth();
        height = imageBitmap1.getHeight();

        pixels1 = new int[width * height];
        pixels2 = new int[pixels1.length];

        imageBitmap1.getPixels(pixels1, 0, width, 0, 0, width, height);
        imageBitmap2.getPixels(pixels2, 0, width, 0, 0, width, height);

        imageView = findViewById(R.id.image);
    }

    private void initTextView() {
        alphaTextView = findViewById(R.id.alphaTextView);
    }

    private void setSeekbarEventListener() {
        SeekBar seekBar = findViewById(R.id.seekBar);
        seekBar.setProgress((int)(INITIAL_ALPHA * 100));
        if (seekBar != null) {
            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    onAlphaChange(progress / 100.0f);
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) { }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) { }
            });
        }
    }

    private void onAlphaChange(float alpha) {
        alphaTextView.setText(Float.toString(alpha));
        combineImages(alpha);
    }

    private void combineImages(float alpha) {
        int[] dst = new int[pixels1.length];
        for (int i = 0; i < pixels1.length; i++) {
            MyColor color1 = new MyColor(pixels1[i]);
            MyColor color2 = new MyColor(pixels2[i]);
            int r = (int)(color1.getR() * alpha + (1 - alpha) * color2.getR());
            int g = (int)(color1.getG() * alpha + (1 - alpha) * color2.getG());
            int b = (int)(color1.getB() * alpha + (1 - alpha) * color2.getB());
            dst[i] = new MyColor(r, g, b).toInt();
        }

        ImageUtils.setImagePixels(dst, imageView, width, height);
    }
}