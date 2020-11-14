package com.example.lab6;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

public class MyRectangle {
    private float x;
    private float y;

    private float width;
    private float height;

    private float maxWidth;
    private float maxHeight;

    private float vx;
    private float vy;

    private float dwidth;
    private float dheight;

    public float getVx() {
        return vx;
    }

    public float getVy() {
        return vy;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getHeight() {
        return height;
    }

    public float getWidth() {
        return width;
    }

    public void setVx(float vx) {
        this.vx = vx;
    }

    public void setVy(float vy) {
        this.vy = vy;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setCX(float cx) {
        this.setX(cx - width / 2);
    }

    public void setCY(float cy) {
        this.setY(cy + height / 2);
    }

    public void setY(float y) {
        this.y = y;
    }

    public MyRectangle(float x, float y, float width, float height, float vx, float vy, float dwidth, float dheight) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.maxWidth = width;
        this.maxHeight = height;
        this.vx = vx;
        this.vy = vy;
        this.dwidth = dwidth;
        this.dheight = dheight;
    }

    public MyRectangle(float x, float y, float width, float height) {
        this(x, y, width, height, 0, 0, 0, 0);
    }

    public void draw(Canvas c, Paint p) {
        c.drawRect(x, y, x + width, y - height, p);
    }

    public void update(Canvas c) {
        if (this.width >= this.maxWidth || this.width <= 0) {
            this.dwidth = -this.dwidth;
        }
        if (this.height >= this.maxHeight || this.height <= 0) {
            this.dheight = -this.dheight;
        }

        if (x + width >= c.getWidth() || x <= 0) {
            this.vx = -this.vx;
        }
        if (y >= c.getHeight() || y - height <= 0) {
            this.vy = -this.vy;
        }

        this.x += this.vx;
        this.y += this.vy;

        this.width += this.dwidth;
        this.height += this.dheight;
    }
}