package com.imakeanapp.imapmovies.Splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.imakeanapp.imapmovies.R;
import com.imakeanapp.imapmovies.SignupAndLogin.LoginPage;

public class SplashActivity extends AppCompatActivity {

    private Intent myintent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        myintent = new Intent(SplashActivity.this, LoginPage.class);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(myintent);
                finish();
            }
        }, 2500);
    }
}
