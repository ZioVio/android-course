package com.example.lab5;

public class Color {

    private int _r, _g, _b;
    private int _alpha;

    public Color(int r, int g, int b) {
        // do not perform any checks for simplification
        this._r = r;
        this._g = g;
        this._b = b;
        this._alpha = 255;
    }

    public Color(int r, int g, int b, int alpha) {
        this(r, g, b);
        this._alpha = alpha;
    }

    public Color(int srcColor) {
        this(((srcColor & 0x00ff0000) >> 16),
            ((srcColor & 0x0000ff00) >> 8),
            (srcColor & 0x000000ff),
            srcColor & 0xff000000);
    }

    public Color(Color src) {
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
        this._alpha = alpha;
    }

    public void setR(int r) {
        this._r = r;
    }

    public void setG(int g) {
        this._g = g;
    }

    public void setB(int b) {
        this._b = b;
    }
}
