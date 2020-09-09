package com.example.first_app;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private final String operatorPlus = "+";
    private final String operatorMinus = "-";
    private final String operatorMultiply = "*";
    private final String operatorDivide = "/";
    private final String operatorEquals = "=";
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
        String operatorString = "";
        if (this.operator.length() > 0) {
            operatorString = " " + this.operator + " ";
        }
        this.resultTextView.setText(this.firstNumber + operatorString + this.secondNumber);
    }

    public void onClick0(View view) {
        onIncomeNumber(((Button)view).getText().toString());
    }

    public void onClick1(View view) {
        onIncomeNumber(((Button)view).getText().toString());
    }

    public void onClick2(View view) {
        onIncomeNumber(((Button)view).getText().toString());
    }

    public void onClick3(View view) {
        onIncomeNumber(((Button)view).getText().toString());
    }

    public void onClick4(View view) {
        onIncomeNumber(((Button)view).getText().toString());
    }

    public void onClick5(View view) {
        onIncomeNumber(((Button)view).getText().toString());
    }

    public void onClick6(View view) {
        onIncomeNumber(((Button)view).getText().toString());
    }

    public void onClick7(View view) {
        onIncomeNumber(((Button)view).getText().toString());
    }

    public void onClick8(View view) {
        onIncomeNumber(((Button)view).getText().toString());
    }

    public void onClick9(View view) {
        onIncomeNumber(((Button)view).getText().toString());
    }

    public void onPlusClick(View view) {
        this.onIncomeOperator(((Button)view).getText().toString());
    }

    public void onMinusClick(View view) {
        this.onIncomeOperator(((Button)view).getText().toString());
    }

    public void onMultClick(View view) {
        this.onIncomeOperator(((Button)view).getText().toString());
    }

    public void onDivideCLick(View view) {
        this.onIncomeOperator(((Button)view).getText().toString());
    }

    public void onEqualsClick(View view) {
        try {
            float firstNum = Float.parseFloat(this.firstNumber);
            float secondNum = Float.parseFloat(this.secondNumber);
            float result = performOperation(firstNum, secondNum, this.operator);
            this.clearText();
            String resultString = String.valueOf(result);
            this.resultTextView.setText(resultString);
            this.firstNumber = resultString;
        } catch (Exception e) {
            // ignore
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
            case operatorPlus: return (a + b);
            case operatorMinus: return (a - b);
            case operatorDivide: return (a / b);
            case operatorMultiply: return (a * b);
//            default: throw new error, ignore for now
        }
        return 0.0f;
    }


}