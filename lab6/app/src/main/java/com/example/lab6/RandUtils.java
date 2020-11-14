package com.example.lab6;

public class RandUtils {
    public static int integer(int from, int to) {
        return (int) ((Math.random() * (to - from)) + from);
    }

    public static float floating(float from, float to) {
        return (float) ((Math.random() * (to - from)) + from);
    }
}
