package com.example.first_app;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Array;

public class MainActivity extends AppCompatActivity {

    private final String OPERATOR_PLUS = "+";
    private final String OPERATOR_MINUS = "-";
    private final String OPERATOR_MULTIPLY = "*";
    private final String OPERATOR_DIVIDE = "/";
    private final String OPERATOR_EQUALS = "=";

    private final double EPSILON = 1e-6;


    private Spinner spinner;
    private TextView resultText;
    private EditText firstNumInput;
    private EditText secondNumInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        this.decorateDropdown();
        resultText = (TextView) findViewById(R.id.resultText);
        spinner = (Spinner) findViewById(R.id.operatorSpinner);
        firstNumInput = (EditText) findViewById(R.id.firstNumberInput);
        secondNumInput = (EditText) findViewById(R.id.secondNumberInput);
    }


    public void onCalculateClick(View view) {
        try {
            float firstNumber = Float.parseFloat(firstNumInput.getText().toString());
            float secondNumber = Float.parseFloat(secondNumInput.getText().toString());
            float result = this.performOperation(firstNumber, secondNumber, spinner.getSelectedItem().toString());
            this.updateResult(result);
        } catch (Exception e) {
            // ignore for now
        }
    }

    private void updateResult(float res) {
        String resultString = String.valueOf(res);
        String trimmedResult =  floatIsLikeInteger(res) ?
                // had to do such a trick because splitting by "." didn't work -__-
                resultString.replace(".", ",").split(",")[0] :
                resultString;
        this.resultText.setText(trimmedResult);
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

    private void decorateDropdown() {
//        this.spinner = (Spinner)findViewById(R.id.operatorSpinner);
//        ArrayAdapter operatorsAdapter = ArrayAdapter.createFromResource(this, R.array.operators, R.layout.spinner_text);
//        operatorsAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
//        this.spinner.setAdapter(operatorsAdapter);
    }

    private boolean floatIsLikeInteger(float num) {
        return Math.abs(num - Math.round(num)) < EPSILON;
    }
}