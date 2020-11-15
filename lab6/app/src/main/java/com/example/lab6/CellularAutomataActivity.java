package com.example.lab6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;

public class CellularAutomataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new CellularAutomataView(this));
    }

    public class CellularAutomataView extends View {

        Paint paint;
        CellularAutomata ca;
        CellularAutomata[] automatas;

        public CellularAutomataView(Context context) {
            super(context);
            paint = new Paint();
            paint.setColor(Color.RED);
            ca = new CellularAutomata(
                    0,
                    1080,
                    0,
                    1920,
                    5,
                    new CellularAutomata.ConditionCell[] {
                            new CellularAutomata.ConditionCell(new int[] {1, 1, 1}, 0),
                            new CellularAutomata.ConditionCell(new int[] {1, 1, 0}, 1),
                            new CellularAutomata.ConditionCell(new int[] {1, 0, 1}, 0),
                            new CellularAutomata.ConditionCell(new int[] {1, 0, 0}, 1),
                            new CellularAutomata.ConditionCell(new int[] {0, 1, 1}, 1),
                            new CellularAutomata.ConditionCell(new int[] {0, 1, 0}, 0),
                            new CellularAutomata.ConditionCell(new int[] {0, 0, 1}, 1),
                            new CellularAutomata.ConditionCell(new int[] {0, 0, 0}, 0)
                    });
        }

        @Override
        protected void onDraw(Canvas canvas) {
            if (this.automatas == null) {
                this.automatas = createAutomatas(getWidth(), getHeight());
            }
            for (CellularAutomata automata : this.automatas) {
                automata.draw(canvas, paint);
                if (!automata.update()) {
                    automata.reset();
                }
            }
            invalidate();
//            ca.draw(canvas, paint);
//            if (!ca.update()) {
//                ca.reset();
//            }
//            invalidate();
        }

        private CellularAutomata[] createAutomatas(float width, float height) {
            return new CellularAutomata[] {
                    new CellularAutomata(
                            0,
                            width / 2,
                            0,
                            height / 3,
                            5,
                            new CellularAutomata.ConditionCell[] {
                                    new CellularAutomata.ConditionCell(new int[] {1, 1, 1}, 0),
                                    new CellularAutomata.ConditionCell(new int[] {1, 1, 0}, 1),
                                    new CellularAutomata.ConditionCell(new int[] {1, 0, 1}, 0),
                                    new CellularAutomata.ConditionCell(new int[] {1, 0, 0}, 1),
                                    new CellularAutomata.ConditionCell(new int[] {0, 1, 1}, 1),
                                    new CellularAutomata.ConditionCell(new int[] {0, 1, 0}, 0),
                                    new CellularAutomata.ConditionCell(new int[] {0, 0, 1}, 1),
                                    new CellularAutomata.ConditionCell(new int[] {0, 0, 0}, 0)
                            }),
                    new CellularAutomata(
                            width / 2,
                            width,
                            0,
                            height / 3,
                            10,
                            new CellularAutomata.ConditionCell[] {
                                    new CellularAutomata.ConditionCell(new int[] {1, 1, 1}, 1),
                                    new CellularAutomata.ConditionCell(new int[] {1, 1, 0}, 0),
                                    new CellularAutomata.ConditionCell(new int[] {1, 0, 1}, 0),
                                    new CellularAutomata.ConditionCell(new int[] {1, 0, 0}, 1),
                                    new CellularAutomata.ConditionCell(new int[] {0, 1, 1}, 0),
                                    new CellularAutomata.ConditionCell(new int[] {0, 1, 0}, 1),
                                    new CellularAutomata.ConditionCell(new int[] {0, 0, 1}, 1),
                                    new CellularAutomata.ConditionCell(new int[] {0, 0, 0}, 0)
                            }),
                    new CellularAutomata(
                            0,
                            width / 2,
                            height / 3,
                            height / 3 * 2,
                            5,
                            new CellularAutomata.ConditionCell[] {
                                    new CellularAutomata.ConditionCell(new int[] {1, 1, 1}, 0),
                                    new CellularAutomata.ConditionCell(new int[] {1, 1, 0}, 0),
                                    new CellularAutomata.ConditionCell(new int[] {1, 0, 1}, 0),
                                    new CellularAutomata.ConditionCell(new int[] {1, 0, 0}, 1),
                                    new CellularAutomata.ConditionCell(new int[] {0, 1, 1}, 1),
                                    new CellularAutomata.ConditionCell(new int[] {0, 1, 0}, 1),
                                    new CellularAutomata.ConditionCell(new int[] {0, 0, 1}, 1),
                                    new CellularAutomata.ConditionCell(new int[] {0, 0, 0}, 0)
                            }),
                    new CellularAutomata(
                            width / 2,
                            width,
                            height / 3,
                            height / 3 * 2,
                            4,
                            new CellularAutomata.ConditionCell[] {
                                    new CellularAutomata.ConditionCell(new int[] {1, 1, 1}, 0),
                                    new CellularAutomata.ConditionCell(new int[] {1, 1, 0}, 1),
                                    new CellularAutomata.ConditionCell(new int[] {1, 0, 1}, 1),
                                    new CellularAutomata.ConditionCell(new int[] {1, 0, 0}, 0),
                                    new CellularAutomata.ConditionCell(new int[] {0, 1, 1}, 1),
                                    new CellularAutomata.ConditionCell(new int[] {0, 1, 0}, 0),
                                    new CellularAutomata.ConditionCell(new int[] {0, 0, 1}, 0),
                                    new CellularAutomata.ConditionCell(new int[] {0, 0, 0}, 1)
                            }),
                    new CellularAutomata(
                            0,
                            width / 2,
                            height / 3 * 2,
                            height,
                            4,
                            new CellularAutomata.ConditionCell[] {
                                    new CellularAutomata.ConditionCell(new int[] {1, 1, 1}, 0),
                                    new CellularAutomata.ConditionCell(new int[] {1, 1, 0}, 1),
                                    new CellularAutomata.ConditionCell(new int[] {1, 0, 1}, 1),
                                    new CellularAutomata.ConditionCell(new int[] {1, 0, 0}, 0),
                                    new CellularAutomata.ConditionCell(new int[] {0, 1, 1}, 1),
                                    new CellularAutomata.ConditionCell(new int[] {0, 1, 0}, 1),
                                    new CellularAutomata.ConditionCell(new int[] {0, 0, 1}, 1),
                                    new CellularAutomata.ConditionCell(new int[] {0, 0, 0}, 0)
                            }),
                    new CellularAutomata(
                            width / 2,
                            width,
                            height / 3 * 2,
                            height,
                            3,
                            new CellularAutomata.ConditionCell[] {
                                    new CellularAutomata.ConditionCell(new int[] {1, 1, 1}, 0),
                                    new CellularAutomata.ConditionCell(new int[] {1, 1, 0}, 0),
                                    new CellularAutomata.ConditionCell(new int[] {1, 0, 1}, 1),
                                    new CellularAutomata.ConditionCell(new int[] {1, 0, 0}, 1),
                                    new CellularAutomata.ConditionCell(new int[] {0, 1, 1}, 1),
                                    new CellularAutomata.ConditionCell(new int[] {0, 1, 0}, 0),
                                    new CellularAutomata.ConditionCell(new int[] {0, 0, 1}, 0),
                                    new CellularAutomata.ConditionCell(new int[] {0, 0, 0}, 1)
                            })
            };
        }
    }
}