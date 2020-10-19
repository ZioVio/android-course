package com.example.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Binder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.Arrays;

public class MatrixManipulationActivity extends AppCompatActivity {

    private ImageView imageViewOriginal, imageViewBlur, imageViewSharpness,
                      imageViewMedian, imageViewErosionAndGrowth, imageViewSobel;

    private Bitmap srcBitmap;

    private int MEDIAN_MATRIX_SIZE = 10;
    private int SRC_IMAGE_RES_ID = R.mipmap.tesla;

    private MatrixManipulator defaultMatrixManipulator = new MatrixManipulator() {
        @Override
        public int getColor(double[][] matrix, int[][] pixels) {
            int size = matrix.length;
            int sumR = 0, sumG = 0, sumB = 0;

            for(int i = 0; i < size; ++i) {
                for(int j = 0; j < size; ++j) {
                    MyColor color = new MyColor(pixels[i][j]);
                    sumR += (color.getR() * matrix[i][j]);
                    sumG += (color.getG() * matrix[i][j]);
                    sumB += (color.getB() * matrix[i][j]);
                }
            }

            int center = (int)Math.floor(size / 2);
            int alpha = new MyColor(pixels[center][center]).getAlpha();
            MyColor color = new MyColor((int)sumR, (int)sumG, (int)sumB, alpha);
            return color.toInt();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matrix_manipulation);
        initImageViews();
        initSrcBitmap();
        applyMatrices();
    }

    private void initImageViews() {
        this.imageViewOriginal = findViewById(R.id.src);
        this.imageViewBlur = findViewById(R.id.imageBlur);
        this.imageViewSharpness = findViewById(R.id.imageSharpness);
        this.imageViewErosionAndGrowth = findViewById(R.id.imageErosionAndGrowth);
        this.imageViewMedian = findViewById(R.id.imageMedian);
        this.imageViewSobel = findViewById(R.id.imageSobel);
    }

    private void initSrcBitmap() {
        this.srcBitmap = BitmapFactory.decodeResource(getResources(), SRC_IMAGE_RES_ID);
        this.imageViewOriginal.setImageBitmap(srcBitmap);
    }

    private Bitmap applyMatrix(double[][] matrix, Bitmap src, MatrixManipulator matrixManipulator, boolean horizontal) {
        int width = src.getWidth();
        int height = src.getHeight();

        int size = matrix.length;

        Bitmap result = Bitmap.createBitmap(width, height, src.getConfig());

        int[][] pixels = new int[size][size];

        if (horizontal) {
            for(int y = 0; y < height - size; y++) {
                for(int x = 0; x < width - size; x++) {

                    for(int i = 0; i < size; i++) {
                        for(int j = 0; j < size; j++) {
                            pixels[i][j] = src.getPixel(x + i, y + j);
                        }
                    }

                    result.setPixel(x + 1, y + 1, matrixManipulator.getColor(matrix, pixels));
                }
            }
        } else {
            for(int x = 0; x < width - size; x++) {
                for(int y = 0; y < height - size; y++) {

                    for(int i = 0; i < size; i++) {
                        for(int j = 0; j < size; j++) {
                            pixels[i][j] = src.getPixel(x + i, y + j);
                        }
                    }

                    result.setPixel(x + 1, y + 1, matrixManipulator.getColor(matrix, pixels));
                }
            }
        }


        return result;
    }

    private Bitmap applyBlurMatrix(Bitmap bitmap) {
        double[][] matrix = new double[][] {
                new double[] { .000789, .006581, .013347, .000789, .006581 },
                new double[] { .006581, .054901, .111345, .054901, .006581 },
                new double[] { .013347, .111345, .225821, .111345, .013347 },
                new double[] { .006581, .054901, .111345, .054901, .006581 },
                new double[] { .000789, .006581, .013347, .000789, .006581 }
        };

        return applyMatrix(matrix, bitmap, defaultMatrixManipulator, true);
    }

    private Bitmap applyMedianMatrix(Bitmap bitmap, int size) {
        return applyMatrix(new double[size][size], bitmap, new MatrixManipulator() {
            @Override
            public int getColor(double[][] matrix, int[][] pixels) {
                int[] oneDimensionPixels = new int[pixels.length * pixels.length];
                int index = 0;
                for (int[] pixelsRow : pixels) {
                    for (int pixel : pixelsRow) {
                        oneDimensionPixels[index++] = pixel;
                    }
                }
                // to clear memory;
                pixels = null;
                //
                Arrays.sort(oneDimensionPixels);
                int leftCenterIndex = (int)Math.floor(oneDimensionPixels.length / 2.0) - 1;
                if (oneDimensionPixels.length % 2 == 0) {
                    MyColor color1 = new MyColor(oneDimensionPixels[leftCenterIndex]);
                    MyColor color2 = new MyColor(oneDimensionPixels[leftCenterIndex + 1]);
                    return new MyColor((color1.getR() + color2.getR()) / 2,
                                      (color1.getG() + color2.getG()) / 2,
                                      (color1.getB() + color2.getB()) / 2,
                                   (color1.getAlpha() + color2.getAlpha()) / 2).toInt();
                }
                return oneDimensionPixels[leftCenterIndex + 1];
            }
        }, true);
    }

    private Bitmap applySharpnessMatrix(Bitmap bitmap) {
        double[][] matrix = new double[][] {
                new double[] { -1, -1, -1 },
                new double[] { -1, 9, -1 },
                new double[] { -1, -1, -1 },
        };

        return applyMatrix(matrix, bitmap, defaultMatrixManipulator, true);
    }

    private Bitmap applyErosionAndGrowthMatrix(Bitmap bitmap) {
        double[][] matrix = new double[][] {
                new double[] { .0, .0, .1, .0, .0 },
                new double[] { .0, .1, .1, .1, .0 },
                new double[] { .1, .1, .1, .1, .1 },
                new double[] { .0, .1, .1, .1, .0 },
                new double[] { .0, .0, .1, .0, .0 },
        };

        return applyMatrix(matrix, bitmap, defaultMatrixManipulator, true);
    }


    private Bitmap applySobelMatrix(Bitmap bitmap) {
        double[][] horizontalMatrix = new double[][] {
                new double[] { -1, -2, -1 },
                new double[] { 0, 0, 0 },
                new double[] { 1, 2, 1 },
        };

        double[][] verticalMatrix = new double[][] {
                new double[] { -1, 0, 1 },
                new double[] { -2, 0, 2 },
                new double[] { -1, 0, 1 },
        };

        Bitmap horizontallyDone = applyMatrix(horizontalMatrix, bitmap, defaultMatrixManipulator, true);
        Bitmap finalResult = applyMatrix(verticalMatrix, horizontallyDone, defaultMatrixManipulator, false);
        return finalResult;
    }

    private void applyMatrices() {
        Bitmap blurred = applyBlurMatrix(srcBitmap);
        this.imageViewBlur.setImageBitmap(blurred);

        Bitmap sharpened = applySharpnessMatrix(srcBitmap);
        this.imageViewSharpness.setImageBitmap(sharpened);

        Bitmap medianned = applyMedianMatrix(srcBitmap, MEDIAN_MATRIX_SIZE);
        this.imageViewMedian.setImageBitmap(medianned);

        Bitmap erosionAndGrowthBitmap = applyErosionAndGrowthMatrix(srcBitmap);
        this.imageViewErosionAndGrowth.setImageBitmap(erosionAndGrowthBitmap);

        Bitmap sobelBitmap = applySobelMatrix(srcBitmap);
        this.imageViewSobel.setImageBitmap(sobelBitmap);
    }

}