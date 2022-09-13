package com.example.calculatorv2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private Button button0, button1, button2, button3, button4, button5, button6, button7, button8, button9,
            buttonAC, buttonDelete, buttonDivide, buttonMultip, buttonSubst, buttonSum, buttonEquals, buttonDot;
    private TextView textViewOperation, textViewResult;
    private String number = null, status = null, operation, currentResult;
    private double firstNumber = 0, lastNumber = 0;
    private boolean operator = false, validateDot = true, ACstatus = true, equalStatus = false;
    private DecimalFormat myFormat = new DecimalFormat("######.######");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
        buttonsListener();
    }

    private void initialize() {
        button0 = findViewById(R.id.btn0);
        button1 = findViewById(R.id.btn1);
        button2 = findViewById(R.id.btn2);
        button3 = findViewById(R.id.btn3);
        button4 = findViewById(R.id.btn4);
        button5 = findViewById(R.id.btn5);
        button6 = findViewById(R.id.btn6);
        button7 = findViewById(R.id.btn7);
        button8 = findViewById(R.id.btn8);
        button9 = findViewById(R.id.btn9);
        buttonAC = findViewById(R.id.btnAC);
        buttonDelete = findViewById(R.id.btnDelete);
        buttonDivide = findViewById(R.id.btnDivide);
        buttonMultip = findViewById(R.id.btnMultip);
        buttonSubst = findViewById(R.id.btnSubtraction);
        buttonSum = findViewById(R.id.btnSum);
        buttonEquals = findViewById(R.id.btnEquals);
        buttonDot = findViewById(R.id.btnDot);
        textViewOperation = findViewById(R.id.textViewOperation);
        textViewResult = findViewById(R.id.textViewResult);
    }

    private void buttonsListener() {

        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberClicked("0");
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberClicked("1");
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberClicked("2");
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberClicked("3");
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberClicked("4");
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberClicked("5");
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberClicked("6");
            }
        });

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberClicked("7");
            }
        });

        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberClicked("8");
            }
        });

        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberClicked("9");
            }
        });

        buttonDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentResult = textViewResult.getText().toString();
                textViewOperation.setText(currentResult+"/");
                if(operator){
                    if(status == "multiplication")
                        multiply();
                    else if(status == "sum")
                        sum();
                    else if(status == "substraction")
                        substraction();
                    else
                        divide();
                }
                status = "division";
                operator = false;
                number = null;
            }
        });

        buttonMultip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentResult = textViewResult.getText().toString();
                textViewOperation.setText(currentResult+"x");
                if(operator){
                    if(status == "sum")
                        sum();
                    else if(status == "division")
                        divide();
                    else if(status == "substraction")
                        substraction();
                    else
                        multiply();
                }
                status = "multiplication";
                operator = false;
                number = null;
            }
        });

        buttonSubst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentResult = textViewResult.getText().toString();
                textViewOperation.setText(currentResult+"-");
                if(operator){
                    if(status == "multiplication")
                        multiply();
                    else if(status == "division")
                        divide();
                    else if(status == "sum")
                        sum();
                    else
                        substraction();
                }
                status = "substraction";
                operator = false;
                number = null;
            }
        });

        buttonSum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //operation = textViewOperation.getText().toString();
                //currentResult = textViewResult.getText().toString();
                //textViewOperation.setText(operation+currentResult+"+");
                currentResult = textViewResult.getText().toString();
                textViewOperation.setText(currentResult+"+");
                if(operator){
                    if(status == "multiplication")
                        multiply();
                    else if(status == "division")
                        divide();
                    else if(status == "substracttion")
                        substraction();
                    else
                        sum();
                }
                status = "sum";
                operator = false;
                number = null;
            }
        });

        buttonAC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number = null;
                status = null;
                textViewResult.setText("0");
                textViewOperation.setText("");
                firstNumber = 0;
                lastNumber = 0;
                validateDot = true;
                ACstatus = true;
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ACstatus) {
                    textViewResult.setText("0");
                }
                else {
                    number = number.substring(0, number.length()-1);
                    if(number.length() == 0)
                        buttonDelete.setClickable(false);
                    else if(number.contains("."))
                        validateDot = false;
                    else
                        validateDot = true;

                    textViewResult.setText(number);
                }
            }
        });

        buttonEquals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(operator){
                    if(status == "sum"){
                        sum();
                        textViewOperation.setText(currentResult+"+"+myFormat.format(lastNumber));
                    }
                    else if(status == "substraction"){
                        substraction();
                        textViewOperation.setText(currentResult+"-"+myFormat.format(lastNumber));
                    }
                    else if(status == "multiplication"){
                        multiply();
                        textViewOperation.setText(currentResult+"x"+myFormat.format(lastNumber));
                    }
                    else if(status == "division"){
                        divide();
                        textViewOperation.setText(currentResult+"/"+myFormat.format(lastNumber));
                    }
                    else
                        firstNumber = Double.parseDouble(textViewResult.getText().toString());
                }
                operator = false;
                equalStatus = true;
            }
        });

        buttonDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validateDot) {
                    if(number == null){
                        number = "0.";
                    }
                    else {
                        number = number + ".";
                    }
                }
                textViewResult.setText(number);
                validateDot = false;
            }
        });
    }

    private void numberClicked(String value){
        if(number == null){
            number = value;
        }
        else if(equalStatus) {
            firstNumber = 0;
            lastNumber = 0;
            number = value;
        }
        else {
            number = number + value;
        }
        textViewResult.setText(number);
        operator = true;
        ACstatus = false;
        buttonDelete.setClickable(true);
        equalStatus = false;
    }

    public void sum(){
        lastNumber = Double.parseDouble(textViewResult.getText().toString());
        firstNumber = firstNumber + lastNumber;
        textViewResult.setText(myFormat.format(firstNumber));
        validateDot = true;
    }

    public void substraction(){
        if(firstNumber == 0){
            firstNumber = Double.parseDouble(textViewResult.getText().toString());
        }
        else {
            lastNumber = Double.parseDouble(textViewResult.getText().toString());
            firstNumber = firstNumber - lastNumber;
        }
        textViewResult.setText(myFormat.format(firstNumber));
        validateDot = true;
    }

    public void multiply(){
        if(firstNumber == 0){
            firstNumber = 1;
            lastNumber = Double.parseDouble(textViewResult.getText().toString());
            firstNumber = firstNumber * lastNumber;
        }
        else {
            lastNumber = Double.parseDouble(textViewResult.getText().toString());
            firstNumber = firstNumber * lastNumber;
        }
        textViewResult.setText(myFormat.format(firstNumber));
        validateDot = true;
    }

    public void divide(){
        if(firstNumber == 0){
            lastNumber = Double.parseDouble(textViewResult.getText().toString());
            firstNumber = lastNumber / 1;
        }
        else {
            lastNumber = Double.parseDouble(textViewResult.getText().toString());
            firstNumber = firstNumber / lastNumber;
        }
        textViewResult.setText(myFormat.format(firstNumber));
        validateDot = true;
    }

}