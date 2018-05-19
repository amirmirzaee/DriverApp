package com.example.user.driverapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.user.driverapp.MainActivity;
import com.example.user.driverapp.R;
import com.example.user.driverapp.TravelDetailActivity;
import com.example.user.driverapp.TravelList;
import com.example.user.driverapp.View_Holder;
import com.example.user.driverapp.model.job_list_model;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import java.util.Collections;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<View_Holder> implements OnMapReadyCallback {

    List<job_list_model> list= Collections.emptyList();
    Context context;
    FragmentActivity fcontext;
    private GoogleMap mMap;
    private static View view1;

    public RecyclerAdapter(List<job_list_model> list, Context context, FragmentActivity fcontext) {
        this.list = list;
        this.context = context;
        this.fcontext=fcontext;
    }

    public RecyclerAdapter() {
    }

    @NonNull
    @Override
    public View_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.travel_list_item,parent,false);
        View_Holder holder = new View_Holder(v,fcontext);
        return holder;
    }



    @Override
    public void onBindViewHolder(@NonNull final View_Holder holder, final int position) {
        holder.sorseFrag.setText(list.get(position).sorse);
        holder.distinationFrag.setText(list.get(position).distin);
        holder.ditalFrag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TravelDetailActivity.class);
                intent.putExtra("sorsaddress",list.get(position).sorse);
                intent.putExtra("disaddress",list.get(position).distin);
                Bundle args = new Bundle();
                args.putParcelable("latlangsaddress", list.get(position).sourcePos);
                args.putParcelable("latlangdaddress", list.get(position).disPos);
                intent.putExtra("position",args);
               context. startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {

        return list.size();
    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    // Insert a new item to the RecyclerView on a predefined position
    public void insert(int position, job_list_model data) {
        list.add(position, data);
        notifyItemInserted(position);
    }

    // Remove a RecyclerView item containing a specified Data object
    public void remove(job_list_model data) {
        int position = list.indexOf(data);
        list.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
    }
}

