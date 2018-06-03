package com.example.user.driverapp.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.driverapp.BarbanetApplication;
import com.example.user.driverapp.MyConstants;
import com.example.user.driverapp.R;
import com.example.user.driverapp.model.user.UserResponse;
import com.example.user.driverapp.webService.ApiInterface;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
//    private ImageView barbanetIcon;
    private EditText tellNumber,pass;
    private Button loginbtn;
    private SharedPreferences shper;



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
        tellNumber=findViewById(R.id.UserName);
        pass=findViewById(R.id.Pass);
        loginbtn=findViewById(R.id.loginbtn);
    }
    public Map<String ,Object> userPass(){
        HashMap<String, Object> mapget=new HashMap<String, Object>();
        mapget.put("username",tellNumber.getText().toString());
        mapget.put("password",pass.getText().toString());
        return mapget;
    }
    private void loginRequset(){

        shper=getSharedPreferences(MyConstants.tok,MODE_PRIVATE);

        ApiInterface apiInterface= ((BarbanetApplication)getApplication()).getRetrofitClient();
        Call<UserResponse> call=apiInterface.getLogin(userPass());
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {

                if (Objects.requireNonNull(response.body()).getError()==null){

                   Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                   SharedPreferences.Editor shedit=shper.edit();

                    shedit.putString(MyConstants.tok, Objects.requireNonNull(response.body()).getUser().getAccessToken());
                   shedit.putString(MyConstants.fname, Objects.requireNonNull(response.body()).getUser().getFirstname());
                   shedit.putString(MyConstants.lname, Objects.requireNonNull(response.body()).getUser().getLastname());
                   shedit.putString(MyConstants.memail, Objects.requireNonNull(response.body()).getUser().getEmail());
                   shedit.putString(MyConstants.mobile, Objects.requireNonNull(response.body()).getUser().getMobile());
                   shedit.putString(MyConstants.refreshToken, Objects.requireNonNull(response.body()).getUser().getRefreshToken());
                   shedit.putString(MyConstants.totalCredit, Objects.requireNonNull(response.body()).getUser().getTotalCredit().toString());
                    shedit.putBoolean(MyConstants.islogin,true);
                   shedit.apply();
                    startActivity(intent);
               }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "error", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
