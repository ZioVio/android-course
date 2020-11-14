package com.example.lab6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.solver.widgets.Rectangle;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SeekBar;

public class CirclingSquareActivity extends AppCompatActivity {

    private int rectWidth = 300;
    private int rectHeight = 400;

    private double speed = 0.01f;
    private float radius = 300;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new CirclingSquareView(this));
//        setContentView(R.layout.activity_task2);
    }


    public class CirclingSquareView extends View {

        MyRectangle rect;
        double angle = 0;
        double rectCX;
        double rectCY;
        double cx, cy;

        Paint paint;

        public CirclingSquareView(Context context) {
            super(context);
            init();
        }

        public CirclingSquareView(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            init();
        }

        public CirclingSquareView(Context context, AttributeSet attributeSet, int defStyle) {
            super(context, attributeSet, defStyle);
            init();
        }

        public void init() {
            paint = new Paint();
            paint.setStrokeWidth(5);
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(Color.RED);
        }


        @Override
        protected void onDraw(Canvas canvas) {
            if (this.rect == null) {
                this.rect = createRect(canvas);
                cx = getWidth() / 2.0;
                cy = getHeight() / 2.0;
            }

            rectCX = radius * Math.cos(angle);
            rectCY = radius * Math.sin(angle);

            angle += speed;

            if (angle > Math.PI * 2) {
                angle = 0;
            }

            rect.setCX((float)cx + (float)rectCX);
            rect.setCY((float)cy + (float)rectCY);
            paint.setStrokeWidth(10);
            rect.draw(canvas, paint);
            paint.setStrokeWidth(20);
            canvas.drawPoint((float)cx, (float)cy, paint);
            invalidate();
        }

        private MyRectangle createRect(Canvas c) {
            float cx = c.getWidth() / 2.0f;
            float cy = c.getHeight() / 2.0f;
            return getRectFromCenterPoint(cx, cy - radius, rectWidth, rectHeight);
        }

        private MyRectangle getRectFromCenterPoint(float x, float y, float width, float height) {
            float rectX = x - width / 2;
            float rectY = y + height / 2;
            return new MyRectangle(rectX, rectY, width, height);
        }
    }
}