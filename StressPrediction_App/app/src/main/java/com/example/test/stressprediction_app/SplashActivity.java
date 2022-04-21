package com.example.test.stressprediction_app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        try
        {
            Thread thread=new Thread()
            {
                public void run()
                {
                    try
                    {
                        sleep(3000);
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                    finally
                    {
                        getData();
                    }
                }
            };
            thread.start();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    void  getData()
    {
        SharedPreferences prefs = getSharedPreferences("user", MODE_PRIVATE);
        String id = prefs.getString("id", "");

        if(id.equals("")) {
            finish();
            startActivity(new Intent(SplashActivity.this, LoginActivity.class));
        }
        else {
            finish();
            startActivity(new Intent(SplashActivity.this, UserDashboardActivity.class));
        }
    }
}
