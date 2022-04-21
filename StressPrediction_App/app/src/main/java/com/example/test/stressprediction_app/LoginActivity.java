package com.example.test.stressprediction_app;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.test.stressprediction_app.Model.ResponseLogin;
import com.example.test.stressprediction_app.Model.ResponseMessage;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {

    Button btnLogin;
    EditText etEmail,etPass;
    ApiInterface apiInterface;
    Retrofit retrofit;
    TextView goToSignup,txtForgot;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        etEmail = findViewById(R.id.etLogEmail);
        etPass = findViewById(R.id.etLogPass);
        goToSignup = findViewById(R.id.tvGotoSignup);
        btnLogin = findViewById(R.id.btnLogin);
        txtForgot = findViewById(R.id.txtForgot);

        retrofit = ApiClient.getClient();
        apiInterface = retrofit.create(ApiInterface.class);


        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Alert");
        progressDialog.setMessage("Please wait...");

        goToSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,SignupActivity.class));
            }
        });

        txtForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String EmailID = etEmail.getText().toString().trim();
                if(EmailID.equalsIgnoreCase("")){
                    Toast.makeText(LoginActivity.this,"Please enter Email ID",Toast.LENGTH_LONG).show();
                }
                else{
                    progressDialog.show();
                    Call<ResponseMessage> call = apiInterface.forgotPassword(EmailID);
                    call.enqueue(new Callback<ResponseMessage>() {
                        @Override
                        public void onResponse(Call<ResponseMessage> call, Response<ResponseMessage> response) {
                            if (response.isSuccessful()){
                                String message = response.body().getMessage();
                                Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
                            }
                            if (progressDialog.isShowing())
                                progressDialog.dismiss();
                        }

                        @Override
                        public void onFailure(Call<ResponseMessage> call, Throwable t) {
                            Toast.makeText(getApplicationContext(),t.toString(),Toast.LENGTH_LONG).show();
                            if (progressDialog.isShowing())
                                progressDialog.dismiss();
                        }
                    });
                }
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString().trim();
                String password = etPass.getText().toString().trim();

                if(!ApiClient.checkNetworkAvailable(LoginActivity.this))
                    Toast.makeText(LoginActivity.this,R.string.con_msg,Toast.LENGTH_LONG).show();
                else if(email.equalsIgnoreCase("") || password.equalsIgnoreCase("")){
                    Toast.makeText(LoginActivity.this,"Please fill all the fields",Toast.LENGTH_LONG).show();
                }
                else{
                    login(email,password);
                }
            }
        });
    }

    private void login(String email, String password) {
        progressDialog.show();
        Call<ResponseLogin> call = apiInterface.login(email,password);
        call.enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                if (response.isSuccessful()){
                    int status = response.body().getStatus();
                    String message = response.body().getMessage();
                    Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
                    if (status == 200){
                        String LoginUserID = String.valueOf(response.body().getLoginUserID());
                        storeDetails(LoginUserID);
                    }
                    if (progressDialog.isShowing())
                        progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.toString(),Toast.LENGTH_LONG).show();
                if (progressDialog.isShowing())
                    progressDialog.dismiss();
            }
        });
    }

    private void storeDetails(String u_id) {
        SharedPreferences.Editor editor = getSharedPreferences("user", MODE_PRIVATE).edit();
        editor.putString("id",String.valueOf(u_id));
        editor.apply();
        editor.commit();
        finish();
        startActivity(new Intent(LoginActivity.this, UserDashboardActivity.class));
    }
}
