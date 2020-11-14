package com.example.lab6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;

public class AnimationsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new DrawView(this));
    }

    class DrawView extends View {

        Paint p;

        int rectanglesCount = 10;

        MyRectangle[] rects;

        public DrawView(Context context) {
            super(context);
            p = new Paint();
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            if (rects == null) {
                rects = createRectangles(canvas.getWidth(), canvas.getHeight());
            }
            Paint paint = new Paint();
            paint.setColor(Color.RED);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(5);

            for (MyRectangle rect : rects) {
                rect.draw(canvas, paint);
            }

            for (MyRectangle rect : rects) {
                rect.update(canvas);
            }
            invalidate();
        }

        private MyRectangle[] createRectangles(float maxWidth, float maxHeight) {
            MyRectangle[] res = new MyRectangle[rectanglesCount];
            for (int i = 0; i < rectanglesCount; i++) {
                float randVX = RandUtils.floating(-10, 10);
                float randVY = RandUtils.floating(-10, 10);
                int randWidth = RandUtils.integer(100, (int)(maxWidth / 2));
                int randHeight = RandUtils.integer(100, (int)(maxHeight / 2));
                float randX = RandUtils.floating(1, maxWidth - randWidth);
                float randY = RandUtils.floating(randHeight, maxHeight - randHeight - 50);
                float randDheight = RandUtils.floating(-5, 5);
                float randDwidth = RandUtils.floating(-5, 5);
                res[i] = new MyRectangle(
                        randX,
                        randY,
                        randWidth,
                        randHeight,
                        randVX,
                        randVY,
                        randDheight,
                        randDwidth
                        );
            }
            return res;
        }
    }

}