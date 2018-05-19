package com.example.user.driverapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.user.driverapp.adapter.RecyclerAdapter;
import com.example.user.driverapp.model.job_list_model;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

public class TravelList extends AppCompatActivity {
private RecyclerView jobList;
    List<job_list_model> data=fill_with_data();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_list);



        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.my_recycel);
        RecyclerAdapter recyclerAdapter=new RecyclerAdapter(data,this,this);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private List<job_list_model> fill_with_data() {
        List<job_list_model> data =new ArrayList<>();
        data.add(new job_list_model("ونک","شهرک غرب",new LatLng( 35.735670 ,51.614763),new LatLng( 35.683363, 51.408084)));
        data.add(new job_list_model("رودکی","شادمان",new LatLng( 35.736532, 51.441817),new LatLng( 35.705164, 51.412795)));
        data.add(new job_list_model("حکیمیه","سعادت آباد",new LatLng( 35.699721, 51.3380543),new LatLng( 35.728272, 51.371245)));
return data;
    }
}
