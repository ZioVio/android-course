package com.example.first_app;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private final String OPERATOR_PLUS = "+";
    private final String OPERATOR_MINUS = "-";
    private final String OPERATOR_MULTIPLY = "*";
    private final String OPERATOR_DIVIDE = "/";
    private final String OPERATOR_EQUALS = "=";

    private final double EPSILON = 1e-6;

    private String operator = "";
    private String firstNumber = "";
    private String secondNumber = "";
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.resultTextView = findViewById(R.id.calcValueView);
    }

    public void onNumberClick(View view) {
        String btnText = ((Button)view).getText().toString();
        onIncomeNumber(btnText);
    }

    public void onOperatorClick(View view) {
        String btnText = ((Button)view).getText().toString();
        this.onIncomeOperator(btnText);
    }

    private void onIncomeNumber(String num) {
        if (this.operator.isEmpty() && !(this.firstNumber.isEmpty() && num.equals("0"))) {
            this.firstNumber += num;
        } else if (!(this.secondNumber.isEmpty() && num.equals("0"))) {
            this.secondNumber += num;
        }
        this.updateCalcText();
    }

    private void onIncomeOperator(String op) {
        this.operator = op;
        this.updateCalcText();
    }

    private void updateCalcText() {
        if (this.firstNumber.isEmpty()) {
            this.operator = "";
            this.secondNumber = "";
        }

        String operatorString = "";
        if (this.operator.length() > 0) {
            operatorString = " " + this.operator + " ";
        }
        this.resultTextView.setText(String.format("%s%s%s", this.firstNumber, operatorString, this.secondNumber));
    }

    public void onEqualsClick(View view) {
        try {
            float firstNum = Float.parseFloat(this.firstNumber);
            float secondNum = Float.parseFloat(this.secondNumber);
            float result = performOperation(firstNum, secondNum, this.operator);
            this.clearText();
            String resultString = String.valueOf(result);
            String trimmedResult =  floatIsLikeInteger(result) ?
                    // had to do such a trick because spliting by "." didn't work -_-
                    resultString.replace(".", ",").split(",")[0] :
                    resultString;

            this.firstNumber = trimmedResult;
            this.resultTextView.setText(trimmedResult);
        } catch (Exception e) {
            // ignore float parsing errors
        }
    }

    private void clearText() {
        this.firstNumber = "";
        this.secondNumber = "";
        this.operator = "";
        this.updateCalcText();
    }

    public void onClearClick(View view) {
        this.clearText();
    }

    private float performOperation(float a, float b, String operation) {
        switch (operation) {
            case OPERATOR_PLUS: return (a + b);
            case OPERATOR_MINUS: return (a - b);
            case OPERATOR_DIVIDE: return (a / b);
            case OPERATOR_MULTIPLY: return (a * b);
//            default: throw error, ignore for now
        }
        return 0.0f;
    }

    private boolean floatIsLikeInteger(float num) {
        return Math.abs(num - Math.round(num)) < EPSILON;
    }
}