package com.example.user.driverapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class LoginActivity extends AppCompatActivity {
//    private ImageView barbanetIcon;
    private EditText tellNumber,pass;
    private Button loginbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        bindObject();

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }


    public void bindObject(){
        tellNumber=(EditText)findViewById(R.id.UserName);
        pass=(EditText)findViewById(R.id.Pass);
        loginbtn=(Button) findViewById(R.id.loginbtn);
    }
}
