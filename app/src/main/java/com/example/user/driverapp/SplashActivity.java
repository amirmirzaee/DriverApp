package com.example.user.driverapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

//        ProgressBar spinner = new android.widget.ProgressBar(
//                this,
//                null,
//                android.R.attr.progressBarStyle);
//        spinner.getIndeterminateDrawable().setColorFilter(Color.parseColor("#0cac92"), android.graphics.PorterDuff.Mode.MULTIPLY);

        ProgressBar downloadProgressBar = (ProgressBar)findViewById(R.id.progressBar);
        downloadProgressBar.getIndeterminateDrawable().setColorFilter(Color.parseColor("#0cac92"),android.graphics.PorterDuff.Mode.MULTIPLY);


            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent=new Intent(SplashActivity.this,LoginActivity.class);
                    startActivity(intent);
                    finish();

                }
            },2000);
    }
}
