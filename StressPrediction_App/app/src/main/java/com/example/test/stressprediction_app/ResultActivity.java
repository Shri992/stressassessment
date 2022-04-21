package com.example.test.stressprediction_app;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    int Stress ,Anxiety,Depression;
    String StressRange,AnxietyRange,DepressionRange;
    TextView txtStress,txtAnxiety,txtDepression;
    String StressHex,AnxietyHex,DepressionHex,StressLevel,AnxietyLevel,DepressionLevel;
    Button btnViewTips,btnStress,btnAnxiety,btnDepression;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent = getIntent();
        Stress = intent.getIntExtra("stress",0);
        Anxiety = intent.getIntExtra("anxiety",0);
        Depression = intent.getIntExtra("depression",0);
        btnViewTips = findViewById(R.id.btnViewTips);
        btnStress = findViewById(R.id.btnStress);
        btnAnxiety = findViewById(R.id.btnAnxiety);
        btnDepression = findViewById(R.id.btnDepression);

        //Stress = Stress * 2;
        //Anxiety = Anxiety * 2;
        //Depression = Depression *2;

        txtStress = findViewById(R.id.txtStressScore);
        txtAnxiety = findViewById(R.id.txtAnxScore);
        txtDepression = findViewById(R.id.txtDepScore);

        if (Stress >= 0 && Stress <= 7){
            StressRange = "Normal";
            StressHex = "#36fe00";  // green
        }
        else if (Stress >= 8 && Stress <= 9){
            StressRange = "Mild";
            StressHex = "#fead00"; // yellow
        }
        else if (Stress >= 10 && Stress <= 12){
            StressRange = "Moderate";
            StressHex = "#00dbfe"; //blue
        }
        else if (Stress >= 13 && Stress <= 16){
            StressRange = "Severe";
            StressHex = "#fe6c00"; // Orange
        }
        else if (Stress > 17 ){
            StressRange = "Extremely Severe";
            StressHex = "#fe2d00"; // red
        }

        if (Anxiety >= 0 && Anxiety <= 3){
            AnxietyRange = "Normal";
            AnxietyHex = "#36fe00";
        }
        else if (Anxiety >= 4 && Anxiety <= 5){
            AnxietyRange = "Mild";
            AnxietyHex = "#fead00";
        }
        else if (Anxiety >= 6 && Anxiety <= 7){
            AnxietyRange = "Moderate";
            AnxietyHex = "#00dbfe";
        }
        else if (Anxiety >= 8 && Anxiety <= 9){
            AnxietyRange = "Severe";
            AnxietyHex = "#fe6c00";
        }
        else if (Anxiety > 10 ){
            AnxietyRange = "Extremely Severe";
            AnxietyHex = "#fe2d00";
        }

        if (Depression >= 0 && Depression <= 4){
            DepressionRange = "Normal";
            DepressionHex = "#36fe00";
        }
        else if (Depression >= 5 && Depression <= 6){
            DepressionRange = "Mild";
            DepressionHex = "#fead00";
        }
        else if (Depression >= 7 && Depression <= 10){
            DepressionRange = "Moderate";
            DepressionHex = "#00dbfe";
        }
        else if (Depression >= 11 && Depression <= 13){
            DepressionRange = "Severe";
            DepressionHex = "#fe6c00";
        }
        else if (Depression > 14 ){
            DepressionRange = "Extremely Severe";
            DepressionHex = "#fe2d00";
        }

        if (Stress > 10){
            StressLevel = "High";
        }
        else{
            StressLevel = "Low";
        }

        if (Depression > 10){
            DepressionLevel = "High";
        }
        else{
            DepressionLevel = "Low";
        }

        if (Anxiety > 10){
            AnxietyLevel = "High";
        }
        else{
            AnxietyLevel = "Low";
        }


        txtStress.setText(String.valueOf(Stress));
        txtAnxiety.setText(String.valueOf(Anxiety));
        txtDepression.setText(String.valueOf(Depression));

        txtStress.setTextColor(Color.parseColor(StressHex));
        txtAnxiety.setTextColor(Color.parseColor(AnxietyHex));
        txtDepression.setTextColor(Color.parseColor(DepressionHex));

        btnViewTips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //finish();
                startActivity(new Intent(ResultActivity.this,TipsActivity.class));
            }
        });

        btnStress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this,VideoActivity.class);
                intent.putExtra("Type","Stress");
                intent.putExtra("Level",StressLevel);
                intent.putExtra("IsFromHome",false);
                startActivity(intent);
            }
        });

        btnAnxiety.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this,VideoActivity.class);
                intent.putExtra("Type","Anxiety");
                intent.putExtra("Level",AnxietyLevel);
                intent.putExtra("IsFromHome",false);
                startActivity(intent);
            }
        });

        btnDepression.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this,VideoActivity.class);
                intent.putExtra("Type","Depression");
                intent.putExtra("Level",DepressionLevel);
                intent.putExtra("IsFromHome",false);
                startActivity(intent);
            }
        });
    }
}
