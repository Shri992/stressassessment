package com.example.test.stressprediction_app;

import android.app.ProgressDialog;
import android.content.Intent;
import android.location.Location;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.test.stressprediction_app.Model.ResponseMessage;
import com.example.test.stressprediction_app.Model.User;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SignupActivity extends AppCompatActivity {

    Button btnSignup;
    EditText etEmail,etMobileNo,etPass,etCpass,etUsername;

    Spinner spType;
    ImageView imgProfile;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ApiInterface apiInterface;
    ProgressDialog progressDialog;
    AlertDialog alertDialog;
    Retrofit retrofit;
    String mobilePattern = "[6-9][0-9]{9}";
    TextView goToLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        etEmail = findViewById(R.id.etEmail);
        etMobileNo = findViewById(R.id.etContact);
        etPass = findViewById(R.id.etPass);
        etCpass = findViewById(R.id.etCPass);
        btnSignup = findViewById(R.id.btnSignup);
        etUsername = findViewById(R.id.etName);
        goToLogin = findViewById(R.id.txtLogin);

        retrofit = ApiClient.getClient();
        apiInterface = retrofit.create(ApiInterface.class);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Alert");
        progressDialog.setMessage("Please wait...");
        progressDialog.setCancelable(false);

        goToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignupActivity.this, LoginActivity.class));
            }
        });


        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString().trim();
                String mobileno = etMobileNo.getText().toString().trim();
                String pass = etPass.getText().toString().trim();
                String cpass = etCpass.getText().toString().trim();
                String name = etUsername.getText().toString().trim();

                if (!ApiClient.checkNetworkAvailable(SignupActivity.this)) {
                    Toast.makeText(SignupActivity.this, R.string.con_msg, Toast.LENGTH_LONG).show();
                } else if (name.length() == 0 || email.length() == 0 || mobileno.length() == 0 ||
                        pass.length() == 0 || cpass.length() == 0) {
                    Toast.makeText(SignupActivity.this, R.string.fill_msg, Toast.LENGTH_LONG).show();
                } else if (!email.matches(emailPattern)) {
                    etEmail.setError("Invalid email");
                } else if (pass.length() < 6) {
                    etPass.setError("Password must contain atleast 6 characters");
                } else if (!pass.equals(cpass)) {
                    etCpass.setError("Password's do not match");
                } else if (!mobileno.matches(mobilePattern)) {
                    etMobileNo.setError("Invalid mobile no");
                }
                else {
                    User u = new User();
                    u.setUserID("0");
                    u.setName(name);
                    u.setEmailID(email);
                    u.setPassword(pass);
                    u.setMobileNo(mobileno);
                    registerUser(u);
                }
            }
        });
    }

    private void registerUser(User u) {
        progressDialog.show();
        Call<ResponseMessage> call = apiInterface.registerUser(u);
        call.enqueue(new Callback<ResponseMessage>() {
            @Override
            public void onResponse(Call<ResponseMessage> call, Response<ResponseMessage> response) {
                if (response.isSuccessful()){
                    int status = response.body().getStatus();
                    String message = response.body().getMessage();
                    Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
                    if(status == 200){
                        finish();
                        startActivity(new Intent(SignupActivity.this,LoginActivity.class));
                    }
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
