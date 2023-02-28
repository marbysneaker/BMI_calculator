package com.example.bmicalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {



    RadioButton maleButton;
    RadioButton femaleButton;
    EditText ageText;
    EditText feetText;
    EditText inchesText;
    Button calculateButton;
    TextView messageText;

    EditText weightText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        setupButtonClickListener();
    }

    private void findViews() {


        maleButton = findViewById(R.id.radio_male_button);
        femaleButton = findViewById(R.id.radio_female_button);
        ageText = findViewById(R.id.text_age);
        feetText = findViewById(R.id.text_feet);
        inchesText = findViewById(R.id.text_inches);
        calculateButton = findViewById(R.id.calculate_button);
        messageText = findViewById(R.id.text_message);
        weightText = findViewById(R.id.text_weight);
    }
    private void setupButtonClickListener() {
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double bmiResult = calculateBmi();

                String ageT = ageText.getText().toString();
                int age = Integer.parseInt(ageT);
                if(age>=18) {
                    displayResult(bmiResult);
                }
                else{
                    displayGuidance(bmiResult);
                }
            }
        });
    }



    private double calculateBmi() {

        String feetT = feetText.getText().toString();
        String inchesT = inchesText.getText().toString();
        String weightT = weightText.getText().toString();


        int feet = Integer.parseInt(feetT);
        int inches = Integer.parseInt(inchesT);
        int weight = Integer.parseInt(weightT);

        int totalInches = feet * 12 + inches;
        double heightInMeters = totalInches * 0.0254;
        double Bmi = weight / (heightInMeters * heightInMeters);




        return Bmi;

    }

    private void displayResult(double Bmi){
        DecimalFormat myDecimalFormatter = new DecimalFormat("0.00");
        String bmiText = myDecimalFormatter.format(Bmi);

        String fullResultString;

        if(Bmi < 18.5){
            fullResultString = bmiText + " You are underweight";
        }
        else if(Bmi>25){
            fullResultString = bmiText + " You are overweight";
        }
        else{
            fullResultString = bmiText + " You are healthy";
        }
        messageText.setText(fullResultString);
    }
    private void displayGuidance(double bmiResult) {

        DecimalFormat myDecimalFormatter = new DecimalFormat("0.00");
        String bmiText = myDecimalFormatter.format(bmiResult);

        String fullResultString;
        if(maleButton.isChecked()){
            fullResultString = bmiText + " As you are under 18 contact your doctor for healthy range for boys";
        }
        else if(femaleButton.isChecked()){
            fullResultString = bmiText + " As you are under 18 contact your doctor for healthy range for girls";

        }
        else{
            fullResultString = bmiText + " As you are under 18 contact your doctor for healthy range";

        }
        messageText.setText(fullResultString);
    }
}