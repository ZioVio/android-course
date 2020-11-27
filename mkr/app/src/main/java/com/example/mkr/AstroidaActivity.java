package com.example.mkr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

public class AstroidaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new AstroidaView(this));
    }

    public class AstroidaView extends View {

        Paint paint;
        int R = 500;
        int smallR = 20;
        int smallCircleColor = Color.BLACK;
        float t = 0;
        float dt = 0.01f;
        float cx, cy;
        int strokeWidth = 10;
        int astroidaColor = Color.RED;
        ArrayList<Point> points;

        int pointIndexForCircle = 0;

        boolean isInited = false;
        boolean astroidaIsDrawen = false;

        public AstroidaView(Context context) {
            super(context);
        }

        private void init(Canvas canvas) {
            paint = new Paint();
            this.points = new ArrayList<Point>();
            this.isInited = true;
            this.cx = canvas.getWidth() / 2.0f;
            this.cy = canvas.getHeight() / 2.0f;
            paint.setColor(astroidaColor);
            paint.setStrokeWidth(strokeWidth);
            // calculate points
            while (t < Math.PI * 2) {
                double x = R * Math.pow(Math.cos(t), 3);
                double y = R * Math.pow(Math.sin(t), 3);
                t += dt;
                Point point = new Point((int)x, (int)y);
                points.add(point);
            }
        }


        @Override
        protected void onDraw(Canvas canvas) {
            if (!this.isInited) {
                this.init(canvas);
            }
            drawAstroida(canvas);
            drawPoint(canvas);
            pointIndexForCircle++;
            if (pointIndexForCircle >= points.size()) {
                pointIndexForCircle = 0;
            }
            invalidate();
        }

        private void drawAstroida(Canvas canvas) {
            if (points.isEmpty()) {
                return;
            }

            for (Point p : points) {
                canvas.drawPoint(p.x + cx, p.y + cy, paint);
            }
        }

        private void drawPoint(Canvas canvas) {
            Point p = points.get(pointIndexForCircle);
            paint.setColor(smallCircleColor);
            canvas.drawCircle(p.x + cx, p.y + cy, smallR, paint);
            paint.setColor(astroidaColor);
        }
    }
}