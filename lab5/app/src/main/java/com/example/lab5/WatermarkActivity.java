package com.example.lab5;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.Random;

public class WatermarkActivity extends AppCompatActivity {

    private int srcImageResId = R.mipmap.android_foreground;
    private Bitmap srcImageBitmap;

    private ImageView watermarkedImageView;

    private EditText keyEditText;
    private EditText secretEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watermark);
        this.initImages();
        this.initTextInputFields();
        this.setUpOnClickListeners();
    }

    private void setUpOnClickListeners() {
        Button encodeButton = findViewById(R.id.encodeButton);
        Button decodeButton = findViewById(R.id.decodeButton);

        if (encodeButton != null) {
            encodeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String secret = getSecretFromInput();
                    String key = generateKeyForSecret(secret);
                    keyEditText.setText(key);
                    if (key.isEmpty() || secret.isEmpty()) {
                        return;
                    }
                    Bitmap bitmap = makeWatermarkedImage(srcImageBitmap, secret, key);
                    watermarkedImageView.setImageBitmap(bitmap);
                }
            });
        }

        if (decodeButton != null) {
            decodeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String key = getKeyFromInput();
//                    watermarkedImageView.setDrawingCacheEnabled(true);
//                    Bitmap bitmap = watermarkedImageView.getDrawingCache();
                    Bitmap bitmap = ((BitmapDrawable)watermarkedImageView.getDrawable()).getBitmap();
                    String secret = getSecret(bitmap, key);
                    Log.e("SECRET result", secret);
                    secretEditText.setText(secret);
                }
            });
        }
    }

    private void initTextInputFields() {
        keyEditText = findViewById(R.id.key);
        secretEditText = findViewById(R.id.secret);
    }

    private void initImages() {
        this.srcImageBitmap = BitmapFactory.decodeResource(getResources(), srcImageResId);
        ((ImageView)findViewById(R.id.src)).setImageBitmap(srcImageBitmap);
        this.watermarkedImageView = findViewById(R.id.watermarked);
    }

    private Bitmap makeWatermarkedImage(Bitmap src, String secret, String key) {
        int width = src.getWidth();
        int height = src.getHeight();
        Bitmap watermarked = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        int[] watermarkedPixels = new int[width * height];
        src.getPixels(watermarkedPixels, 0, width, 0, 0, width, height);
        String secretBinary = stringToBinary(secret);
        String keyBinary = stringToBinary(key);

        for (int i = 0; i < keyBinary.length(); i++) {
            int secretBit = Integer.parseInt(String.valueOf(secretBinary.charAt(i)));
//            Log.e("LOG", "secretBit " + secretBit + " watermarkedPixels[i] " + Integer.toBinaryString(watermarkedPixels[i]) + " keyBinary.charAt(i) " + keyBinary.charAt(i));
//            Log.e("BEFORE", Integer.toBinaryString(watermarkedPixels[i]));
            if (keyBinary.charAt(i) == '0') {
                if (watermarkedPixels[i] % 2 == 0) {
                    watermarkedPixels[i] |= secretBit;
                } else {
                    watermarkedPixels[i] += watermarkedPixels[i] & secretBit;
                }
            } else {
                if ((watermarkedPixels[i] >> 1) % 2 == 0) {
                    watermarkedPixels[i] |= secretBit << 1;
                } else {
                    watermarkedPixels[i] = (((watermarkedPixels[i] >> 1) & (secretBit)) << 1) + 1;
                }
            }
//            Log.e("AFTER", Integer.toBinaryString(watermarkedPixels[i]));
        }

//        for (int watermarkedPixel : watermarkedPixels) {
//            Log.e("watermarkedPixel", Integer.toBinaryString(watermarkedPixel) + "");
//        }

        watermarked.setPixels(watermarkedPixels, 0, width, 0, 0, width, height);
        return watermarked;
    }

    private String getSecret(Bitmap bitmap, String key) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int[] buffer = new int[width * height];
        bitmap.getPixels(buffer, 0, width, 0, 0, width, height);

        String keyBinary = stringToBinary(key);
        StringBuilder resultBinary = new StringBuilder();

        for (int i = 0; i < keyBinary.length(); i++) {
            int secretBit = keyBinary.charAt(i) == '0' ? buffer[i] & 1 : ((buffer[i] & 0b10) >> 1);
            resultBinary.append(secretBit == 1 ? "1" : "0");
        }

        return binaryStringToNormal(resultBinary.toString());
    }

    private String generateKeyForSecret(String secret) {
        int leftLimit = 97;
        int rightLimit = 122;
        int targetStringLength = secret.length();
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }

    private String getKeyFromInput() {
        return this.keyEditText.getText().toString();
    }

    private String getSecretFromInput() {
        return this.secretEditText.getText().toString();
    }

    private String stringToBinary(String src) {
        return new BigInteger(src.getBytes()).toString(2);
    }

    private String binaryStringToNormal(String src) {
        return new String(new BigInteger(src, 2).toByteArray());
    }
}