package com.example.lab6;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

public class CellularAutomata {

    private float left, top, right, bottom, cellSize;

    private int step;

    private int[][] cells;

    private ConditionCell[] conditionCells;

    public CellularAutomata(float left, float right, float top, float bottom, float cellSize, ConditionCell[] conditionCells) {
        this.left = left;
        this.right = right;
        this.top = top;
        this.bottom = bottom;
        this.cellSize = cellSize;
        this.step = 0;
        this.conditionCells = conditionCells;
        this.initCells(cellSize);
    }

    public void draw(Canvas canvas, Paint paint) {
        float prevStrokeWidth = paint.getStrokeWidth();
        Paint.Style prevFillStyle = paint.getStyle();

        paint.setStrokeWidth(1);
        paint.setStyle(Paint.Style.FILL);

        for (int i = 0; i < this.cells.length; i++) {
            for (int j = 0; j < this.cells[i].length; j++) {
                float left = j * cellSize + this.left;
                float top = i * cellSize + this.top;
                if (this.cells[i][j] == 1 && left + cellSize <= this.right && top + cellSize <= this.bottom) {
                    canvas.drawRect(left, top, left + cellSize, top + cellSize, paint);
                }
            }
        }

        paint.setStrokeWidth(prevStrokeWidth);
        paint.setStyle(prevFillStyle);
    }

    public boolean update() {
        if (this.step + 1 >= this.cells.length) {
            return false;
        }

        int conditionLength = this.conditionCells[0].condition.length;
        for (int i = 0; i < this.cells[this.step].length - conditionLength; i++) {
            int[] condition = new int[conditionLength];
            for (int j = i; j < conditionLength + i; j++) {
                condition[j - i] = this.cells[this.step][j];
            }
            for (ConditionCell conditionCell : this.conditionCells) {
                if (conditionCell.conditionMatches(condition)) {
                    this.cells[this.step + 1][i + (int)Math.floor(conditionLength / 2.0)] = conditionCell.cell;
                }
            }
        }

        this.step += 1;
        return true;
    }

    public void reset() {
        this.step = 0;
        for (int i = 0; i < this.cells.length; i++) {
            for (int j = 0; j < this.cells[i].length; j++) {
                this.cells[i][j] = 0;
            }
        }
        this.cells[0][this.cells[0].length / 2] = 1;
    }

    private void initCells(float cellSize) {
        float width = this.right - this.left;
        float height = this.bottom - this.top;

        int cellsWidth = (int)Math.floor(width / cellSize);
        int cellsHeight = (int)Math.floor(height / cellSize);

        this.cells = new int[cellsHeight][cellsWidth];
        this.cells[0][cellsWidth / 2] = 1;
    }

    public static class ConditionCell {

        int[] condition;
        int cell;

        public ConditionCell(int[] condition, int cell) {
            this.condition = condition;
            this.cell = cell;
        }

        public boolean conditionMatches(int[] cells) {
            for (int i = 0; i < this.condition.length; i++) {
                if (cells[i] != this.condition[i]) {
                    return false;
                }
            }
            return true;
        }
    }
}
