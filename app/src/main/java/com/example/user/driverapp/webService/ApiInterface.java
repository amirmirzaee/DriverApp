package com.example.user.driverapp.webService;


import com.example.user.driverapp.model.user.UserResponse;


import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface ApiInterface {



    @POST("rest/public/login")
    Call<UserResponse> getLogin(@Body Map<String,Object> map);
}
