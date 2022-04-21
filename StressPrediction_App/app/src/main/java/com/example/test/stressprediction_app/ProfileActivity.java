package com.example.test.stressprediction_app;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.test.stressprediction_app.Model.ResponseMessage;
import com.example.test.stressprediction_app.Model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProfileActivity extends AppCompatActivity {

    Button btnProfile;
    EditText etEmail,etMobileNo,etPass,etCpass,etUsername;
    Spinner spType;
    ImageView imgProfile;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ApiInterface apiInterface;
    ProgressDialog progressDialog;
    AlertDialog alertDialog;
    Retrofit retrofit;
    String mobilePattern = "[6-9][0-9]{9}";
    String UserID;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        etEmail = findViewById(R.id.etEmail);
        etMobileNo = findViewById(R.id.etContact);
        etPass = findViewById(R.id.etPass);
        etCpass = findViewById(R.id.etCPass);
        btnProfile = findViewById(R.id.btnProfile);
        etUsername = findViewById(R.id.etName);
        etEmail.setEnabled(false);

        retrofit = ApiClient.getClient();
        apiInterface = retrofit.create(ApiInterface.class);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Alert");
        progressDialog.setMessage("Please wait...");
        progressDialog.setCancelable(false);

        if (!ApiClient.checkNetworkAvailable(ProfileActivity.this))
            Toast.makeText(ProfileActivity.this,R.string.con_msg,Toast.LENGTH_LONG).show();
        else
            getProfileData();
        

        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString().trim();
                String mobileno = etMobileNo.getText().toString().trim();
                String pass = etPass.getText().toString().trim();
                String cpass = etCpass.getText().toString().trim();
                String name = etUsername.getText().toString().trim();

                if (!ApiClient.checkNetworkAvailable(ProfileActivity.this)) {
                    Toast.makeText(ProfileActivity.this, R.string.con_msg, Toast.LENGTH_LONG).show();
                } else if (name.length() == 0 || email.length() == 0 || mobileno.length() == 0 ||
                        pass.length() == 0 || cpass.length() == 0) {
                    Toast.makeText(ProfileActivity.this, R.string.fill_msg, Toast.LENGTH_LONG).show();
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
                    u.setUserID(UserID);
                    u.setName(name);
                    u.setEmailID(email);
                    u.setPassword(pass);
                    u.setMobileNo(mobileno);
                    updateProfile(u);
                }
            }
        });
    }

    private void getProfileData() {
        final SharedPreferences prefs = getSharedPreferences("user", MODE_PRIVATE);
        String id = prefs.getString("id", "");

        progressDialog.show();
        Call<List<User>> call = apiInterface.getUserProfile(id);
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful()){
                    List<User> mUserList = response.body();
                    if (!mUserList.isEmpty()){
                        bindValues(mUserList);
                    }
                    else {
                        Toast.makeText(ProfileActivity.this, "No data found", Toast.LENGTH_LONG).show();
                        finish();
                        startActivity(new Intent(ProfileActivity.this,UserDashboardActivity.class));
                    }
                }
                if (progressDialog.isShowing())
                    progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast.makeText(ProfileActivity.this,t.toString(),Toast.LENGTH_LONG).show();
                if (progressDialog.isShowing())
                    progressDialog.dismiss();
            }
        });
    }

    private void bindValues(List<User> mUserList) {
        UserID = mUserList.get(0).getUserID();
        etUsername.setText(mUserList.get(0).getName());
        etEmail.setText(mUserList.get(0).getEmailID());
        etPass.setText(mUserList.get(0).getPassword());
        etCpass.setText(mUserList.get(0).getPassword());
        etMobileNo.setText(mUserList.get(0).getMobileNo());
    }

    private void updateProfile(User u) {
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
                        startActivity(new Intent(ProfileActivity.this,LoginActivity.class));
                    }
                }
                if (progressDialog.isShowing())
                    progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<ResponseMessage> call, Throwable t) {
                Toast.makeText(ProfileActivity.this,t.toString(),Toast.LENGTH_LONG).show();
                if (progressDialog.isShowing())
                    progressDialog.dismiss();
            }
        });
    }
}
