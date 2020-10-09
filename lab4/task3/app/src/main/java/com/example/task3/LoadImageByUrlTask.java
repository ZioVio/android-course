package com.example.task3;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class LoadImageByUrlTask extends AsyncTask<String, Void, Bitmap> {
    ImageView imageView;

    private static Map<String, Bitmap> cache = new HashMap<String, Bitmap>();

    public LoadImageByUrlTask(ImageView iv) {
        this.imageView = iv;
    }


    @Override
    protected Bitmap doInBackground(String ...urls) {
        String URL = urls[0];
        if (cache.containsKey(URL)) {
            return cache.get(URL);
        }
        try {
            InputStream in = new URL(URL).openStream();
            Bitmap bitmap = BitmapFactory.decodeStream(in);
            cache.put(URL, bitmap);
            return bitmap;
        } catch (Exception e) {
            System.out.println("Loading image error " + e.getMessage());
            return null;
        }
    }

    protected void onPostExecute(Bitmap result) {
        if (result == null) {
            return;
        }
        imageView.setImageBitmap(result);
    }
}
