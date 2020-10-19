package com.example.lab5;


public class MyColor {

    private int _r, _g, _b;
    private int _alpha;

    public MyColor(int r, int g, int b) {
        this._r = trimToByteBounds(r);
        this._g = trimToByteBounds(g);
        this._b = trimToByteBounds(b);
        this._alpha = 255;
    }

    public MyColor(int r, int g, int b, int alpha) {
        this(r, g, b);
        this._alpha = trimToByteBounds(alpha);
    }

    public MyColor(int srcColor) {
        this((((srcColor & 0x00ff0000) >> 16) & 0x000000ff),
                (((srcColor & 0x0000ff00) >> 8) & 0x000000ff),
                (srcColor & 0x000000ff),
                ((srcColor & 0xff000000) >> 24) & 0x000000ff);
    }

    public MyColor(MyColor src) {
        this(src.getR(), src.getG(), src.getB(), src.getAlpha());
    }

    public int toInt() {
        return (_alpha << 24) + (_r << 16) + (_g << 8) + _b;
    }

    public int getAlpha() {
        return _alpha;
    }

    public int getR() {
        return _r;
    }

    public int getG() {
        return _g;
    }

    public int getB() {
        return _b;
    }


    public void setAlpha(int alpha) {
        this._alpha = trimToByteBounds(alpha);
    }

    public void setR(int r) {
        this._r = trimToByteBounds(r);
    }

    public void setG(int g) {
        this._g = trimToByteBounds(g);
    }

    public void setB(int b) {
        this._b = trimToByteBounds(b);
    }

    private int trimToByteBounds(int value) {
        if (value > 255) {
            value = 255;
        } else if (value < 0) {
            value = 0;
        }
        return value;
    }
}
