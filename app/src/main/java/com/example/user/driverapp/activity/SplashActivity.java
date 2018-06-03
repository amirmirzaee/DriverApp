package com.example.user.driverapp.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.example.user.driverapp.R;

public class SplashActivity extends AppCompatActivity {
    private String tok="tok";
    private String islogin="islogin";

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

            ProgressBar downloadProgressBar = (ProgressBar) findViewById(R.id.progressBar);
            downloadProgressBar.getIndeterminateDrawable().setColorFilter(getResources().getColor(R.color.progressBar_color), android.graphics.PorterDuff.Mode.MULTIPLY);


            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    SharedPreferences sharedPreferences = getSharedPreferences(tok, MODE_PRIVATE);
                    if (sharedPreferences.getBoolean(islogin,false)) {
                        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else {
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                    }

                }
            }, 2000);
        }
    }
