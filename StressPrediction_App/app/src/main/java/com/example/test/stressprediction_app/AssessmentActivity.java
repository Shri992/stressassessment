package com.example.test.stressprediction_app;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.test.stressprediction_app.Model.Assessment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AssessmentActivity extends AppCompatActivity {

    ApiInterface apiInterface;
    ProgressDialog progressDialog;
    Retrofit retrofit;
    TextView txtQues,txtIndex;
    RadioButton rbOpt1,rbOpt2,rbOpt3,rbOpt4;
    Button btnSubmit;
    int i=0;
    List<Assessment> mAssessmentList;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    int correct = 0;
    int wrong = 0;
    AlertDialog.Builder alertDialog;
    int index = 0;
    AlertDialog.Builder builder;
    int Stress,Anxiety,Depression;
    TextView txtResult;
    String StressRange,AnxietyRange,DepressionRange;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment);

        txtQues = findViewById(R.id.txtQues);
        txtIndex = findViewById(R.id.txtIndex);
        rbOpt1 = findViewById(R.id.rbOpt1);
        rbOpt2 = findViewById(R.id.rbOpt2);
        rbOpt3 = findViewById(R.id.rbOpt3);
        rbOpt4 = findViewById(R.id.rbOpt4);
        btnSubmit = findViewById(R.id.btnSubmit);
        radioGroup = findViewById(R.id.radioGroup);
        txtResult = new TextView(this);

        alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Alert");

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Alert");
        progressDialog.setMessage("Please wait...");

        retrofit = ApiClient.getClient();
        apiInterface = retrofit.create(ApiInterface.class);



        if (!ApiClient.checkNetworkAvailable(AssessmentActivity.this))
            Toast.makeText(AssessmentActivity.this,R.string.con_msg,Toast.LENGTH_LONG).show();
        else
            getQuestions();

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!rbOpt1.isChecked() && !rbOpt2.isChecked() && !rbOpt3.isChecked() &&
                        !rbOpt4.isChecked())
                    Toast.makeText(getApplicationContext(), "Please select one option", Toast.LENGTH_LONG).show();
                else {
                    if (i <= mAssessmentList.size()) {

                        int QuestionTypeID = mAssessmentList.get(i).getQuestionTypeID();
                        int selectedId = radioGroup.getCheckedRadioButtonId();
                        radioButton = findViewById(selectedId);
                        //Toast.makeText(getApplicationContext(),String.valueOf(selectedId),Toast.LENGTH_LONG).show();

                        String userAns = radioButton.getText().toString();
                        int Score;

                        if(userAns.equalsIgnoreCase("Never"))
                            Score = 0;
                        else if (userAns.equalsIgnoreCase("Sometimes"))
                            Score = 1;
                        else if (userAns.equalsIgnoreCase("Often"))
                            Score = 2;
                        else
                            Score = 3;


                        if (QuestionTypeID == 1)
                            Stress += Score;
                        else if (QuestionTypeID == 2)
                            Anxiety += Score;
                        else
                            Depression += Score;

                        //rbOpt1.setChecked(false);
                        //rbOpt2.setChecked(false);
                        //rbOpt3.setChecked(false);
                        //rbOpt4.setChecked(false);

                        i++;
                        showQuestion(i);
                    }
                    if (i == mAssessmentList.size()) {

                        Intent intent = new Intent(AssessmentActivity.this,ResultActivity.class);
                        intent.putExtra("stress",Stress);
                        intent.putExtra("anxiety",Anxiety);
                        intent.putExtra("depression",Depression);
                        finish();
                        startActivity(intent);

//                        buildAlertDialog("Stress: "+StressRange+"\n" +
//                                "Anxiety: "+AnxietyRange+"\n" +
//                                "Depression: "+DepressionRange);         // make custom alert dialog
//                        AlertDialog alert = builder.create();
//                        alert.show();
                    }
                }
            }
        });

    }

    private void getQuestions() {
        progressDialog.show();
        Call<List<Assessment>> call = apiInterface.getQuestions();
        call.enqueue(new Callback<List<Assessment>>() {
            @Override
            public void onResponse(Call<List<Assessment>> call, Response<List<Assessment>> response) {
                if (response.isSuccessful()){
                    mAssessmentList = response.body();
                    if (!mAssessmentList.isEmpty())
                        showQuestion(i);
                    else
                        Toast.makeText(AssessmentActivity.this,"No data found",Toast.LENGTH_LONG).show();
                }
                if (progressDialog.isShowing())
                    progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<List<Assessment>> call, Throwable t) {
                Toast.makeText(AssessmentActivity.this,t.toString(),Toast.LENGTH_LONG).show();
                if (progressDialog.isShowing())
                    progressDialog.dismiss();
            }
        });
    }

    private void showQuestion(int i) {
        //Toast.makeText(getApplicationContext(),String.valueOf(i),Toast.LENGTH_LONG).show();

        if (i < mAssessmentList.size()) {
            txtIndex.setText("Q."+String.valueOf(i+1));
            txtQues.setText(mAssessmentList.get(i).getQuestion());
            rbOpt1.setText(mAssessmentList.get(i).getOption1());
            rbOpt2.setText(mAssessmentList.get(i).getOption2());
            rbOpt3.setText(mAssessmentList.get(i).getOption3());
            rbOpt4.setText(mAssessmentList.get(i).getOption4());
        }
    }

    void buildAlertDialog(String message)
    {
        final int Score = Stress + Anxiety + Depression;
        txtResult.setText(message);
        builder = new AlertDialog.Builder(this);
        builder.setTitle("Result")
                .setView(txtResult)
                .setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //Intent intent = new Intent(AssessmentActivity.this,);
                        //intent.putExtra("score",String.valueOf(Score));
                        //startActivity(intent);
                    }
                });
    }
}
