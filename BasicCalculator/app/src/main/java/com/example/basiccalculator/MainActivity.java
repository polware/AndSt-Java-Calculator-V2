package com.example.basiccalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {

    private EditText operation;
    private Button btnadd;
    private Button btnsub;
    private Button btnmul;
    private Button btndiv;
    private Button btncalc;
    private Button btnclear;
    private TextView txtresult;
    private String signOperation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        operation = findViewById(R.id.editOperation);
        btnadd = findViewById(R.id.btnadd);
        btnsub = findViewById(R.id.btnsub);
        btnmul = findViewById(R.id.btnmul);
        btndiv = findViewById(R.id.btndiv);
        btnclear = findViewById(R.id.btnclr);
        btncalc = findViewById(R.id.calculate);
        txtresult= findViewById(R.id.result);

        // Addition
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOperation = operation.getText().toString();
                StringBuilder str = new StringBuilder(signOperation);
                str.append("+");
                operation.setText(str);
                operation.setSelection(operation.getText().length());
            }
        });

        //Subtraction
        btnsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOperation = operation.getText().toString();
                StringBuilder str = new StringBuilder(signOperation);
                str.append("-");
                operation.setText(str);
                operation.setSelection(operation.getText().length());
            }
        });

        // Multiplication
        btnmul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOperation = operation.getText().toString();
                StringBuilder str = new StringBuilder(signOperation);
                str.append("*");
                operation.setText(str);
                operation.setSelection(operation.getText().length());
            }
        });

        // Division
        btndiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOperation = operation.getText().toString();
                StringBuilder str = new StringBuilder(signOperation);
                str.append("/");
                operation.setText(str);
                operation.setSelection(operation.getText().length());
            }
        });

        // Calc Operation
        btncalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String signString = operation.getText().toString();
                String textCopy = operation.getText().toString();
                if(textCopy.length() == 0){
                    Toast toast = Toast.makeText(MainActivity.this,"Por favor ingrese los valores.",Toast.LENGTH_LONG);
                    toast.show();
                }
                else{
                    String sign = signString.replaceAll("[0-9]", "");
                    StringTokenizer tokens = new StringTokenizer(textCopy, sign);
                    String value1 = tokens.nextToken();
                    String value2 = tokens.nextToken();
                    if(sign.equals("+")){
                        double operator1 = Double.parseDouble(value1);
                        double operator2 = Double.parseDouble(value2);
                        double result = operator1 + operator2;
                        txtresult.setText(Double.toString(result));
                    }
                    else if(sign.equals("-")){
                        double operator1 = Double.parseDouble(value1);
                        double operator2 = Double.parseDouble(value2);
                        double result = operator1 - operator2;
                        txtresult.setText(Double.toString(result));
                    }
                    else if(sign.equals("*")){
                        double operator1 = Double.parseDouble(value1);
                        double operator2 = Double.parseDouble(value2);
                        double result = operator1 * operator2;
                        txtresult.setText(Double.toString(result));
                    }
                    else if(sign.equals("/") && !value2.equals("0")){
                        double operator1 = Double.parseDouble(value1);
                        double operator2 = Double.parseDouble(value2);
                        double result = operator1 / operator2;
                        txtresult.setText(Double.toString(result));
                    }
                    else{
                        Toast toast = Toast.makeText(MainActivity.this,"OPERACIÓN INVÁLIDA: no se puede dividir por cero.",Toast.LENGTH_LONG);
                        toast.show();
                    }
                }
            }
        });

        // Reset Feilds
        btnclear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operation.setText("");
                txtresult.setText("0.00");
                operation.requestFocus();
            }
        });

    }
}