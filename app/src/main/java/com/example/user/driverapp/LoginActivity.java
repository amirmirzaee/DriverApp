package com.example.user.driverapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.user.driverapp.model.user.UserResponse;
import com.example.user.driverapp.webService.ApiClient;
import com.example.user.driverapp.webService.ApiInterface;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

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

                loginRequset();
            }
        });
    }


    public void bindObject(){
        tellNumber=(EditText)findViewById(R.id.UserName);
        pass=(EditText)findViewById(R.id.Pass);
        loginbtn=(Button) findViewById(R.id.loginbtn);
    }
    public Map<String ,Object> userPass(){
        HashMap mapget=new HashMap();
        mapget.put("username",tellNumber.getText().toString());
        mapget.put("password",pass.getText().toString());
        return mapget;
    }
    private void loginRequset(){

        SharedPreferences sharedPreferences = null;
        ApiInterface apiInterface= ApiClient.getClient(sharedPreferences).create(ApiInterface.class);

        Call<UserResponse> call=apiInterface.getLogin(userPass());
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {

                if (response.body().getError()==null){
                   Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                   startActivity(intent);
               }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "error"+t, Toast.LENGTH_SHORT).show();
            }
        });

    }
}
