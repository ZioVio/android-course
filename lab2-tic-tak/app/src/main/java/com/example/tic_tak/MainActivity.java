package com.example.tic_tak;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ConstraintLayout selectComponent;
    private ArrayList<ArrayList<Button>> cells;
    private Button selectedCell;
    private String cellPlaceholderText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.initMembers();
        this.attachEventListeners();
        this.hideSelect();
    }

    public void onCellClick(View view) {
        this.showSelect();
    }

    private void attachEventListeners() {
        for (ArrayList<Button> row : cells) {
            for (Button cell : row) {
                cell.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Button btn = (Button) v;
                        if (selectedCell != null) {
                            return;
                        }
                        showSelect();
                        disableAllButtonsExceptOne(btn);
                        selectedCell = btn;
                    }
                });
            }
        }
    }

    private void resetAllCells() {
        for (ArrayList<Button> row : cells) {
            for (Button btn : row) {
                btn.setEnabled(true);
                btn.setText(cellPlaceholderText);
            }
        }
    }

    private void disableAllButtonsExceptOne(Button b) {
        for (ArrayList<Button> row : cells) {
            for (Button btn : row) {
                if (btn.getId() != b.getId()) {
                    btn.setEnabled(false);
                }
            }
        }
    }

    private void enableAllButtons() {
        for (ArrayList<Button> row : cells) {
            for (Button btn : row) {
                btn.setEnabled(true);
            }
        }
    }

    private void initMembers() {
        cellPlaceholderText = getResources().getString(R.string.tic_tac_placeholder);
        selectComponent = (ConstraintLayout) findViewById(R.id.select_component);
        cells = new ArrayList();
        TableLayout container = (TableLayout)findViewById(R.id.tableContainer);
        for (int i = 0; i < container.getChildCount(); i++) {
            TableRow row = (TableRow) container.getChildAt(i);
            ArrayList<Button> cellsRow = new ArrayList<Button>();
            for (int j = 0; j < row.getChildCount(); j++) {
                Button cell = (Button) row.getChildAt(j);
                cellsRow.add(cell);
            }
            this.cells.add(cellsRow);
        }
    }

    public void onTic(View view) {
        setCellTextOnSideResponse(R.string.tic);
    }

    public void onTac(View view) {
        setCellTextOnSideResponse(R.string.tac);
    }

    private void setCellTextOnSideResponse(int stringRecourseId) {
        if (selectedCell != null) {
            selectedCell.setText(stringRecourseId);
            this.selectedCell = null;
        }
        hideSelect();
        enableAllButtons();
        String winner = checkWinner();
        if (winner != null) {
            onWinner(winner);
        }
    }

    private void onWinner(String winner) {
        String message = getResources().getString(R.string.winner_modal_text) + " " + winner;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.winner_modal_title)
                .setMessage(message)
                .setPositiveButton(R.string.winner_modal_ok_btn, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        resetAllCells();
                    }
                });
        builder.setCancelable(true);
        builder.create().show();
    }

    /**
     * @returns `null` if no winner
     */
    private String checkWinner() {
        String winner = getWinnerInRows();
        if (winner == null) {
            winner = getWinnerInColumns();
        }
        if (winner == null) {
            winner = getWinnerInDiagonals();
        }

        return winner;
    }

    private String getWinnerInRows() {
        String winner = null;
        for (int i = 0; i < cells.size(); i++) {
            ArrayList<Button> row = cells.get(i);
            boolean rowIsWinner = buttonsHaveSameText(row);
            if (rowIsWinner) {
                winner = row.get(0).getText().toString();
                if (winner.equals(cellPlaceholderText)) {
                    winner = null;
                } else {
                    break;
                }
            }
        }

        return winner;
    }

    private String getWinnerInColumns() {
        String winner = null;
        for (int i = 0; i < cells.size(); i++) {
            ArrayList<Button> col = new ArrayList<Button>();
            for (int j = 0; j < cells.size(); j++) {
                col.add(cells.get(j).get(i));
            }
            boolean rowIsWinner = buttonsHaveSameText(col);
            if (rowIsWinner) {
                winner = col.get(0).getText().toString();
                if (winner.equals(cellPlaceholderText)) {
                    winner = null;
                } else {
                    break;
                }
            }
        }

        return winner;
    }

    private String getWinnerInDiagonals() {
        ArrayList<Button> primaryDiagonal = new ArrayList<Button>();
        ArrayList<Button> secondaryDiagonal = new ArrayList<Button>();

        for (int i = 0; i < cells.size(); i++) {
            primaryDiagonal.add(cells.get(i).get(i));
            secondaryDiagonal.add(cells.get(i).get(cells.size() - 1 - i));
        }

        String winner = null;
        if (buttonsHaveSameText(primaryDiagonal)) {
            winner = cells.get(0).get(0).getText().toString();
            if (winner == cellPlaceholderText) {
                winner = null;
            }
        }
        if (winner == null && buttonsHaveSameText(secondaryDiagonal)) {
            winner = cells.get(0).get(cells.size() - 1).getText().toString();
            if (winner == cellPlaceholderText) {
                winner = null;
            }
        }

        return winner;
    }

    private boolean buttonsHaveSameText(ArrayList<Button> btns) {
        String possibleSameText = btns.get(0).getText().toString();
        for (int i = 1; i < btns.size(); i++) {
            String currentText = btns.get(i).getText().toString();
            if (!currentText.equals(possibleSameText)) {
                return false;
            }
        }

        return true;
    }

    private void showSelect() {
        selectComponent.setVisibility(View.VISIBLE);
    }

    private void hideSelect() {
        selectComponent.setVisibility(View.INVISIBLE);
    }
}