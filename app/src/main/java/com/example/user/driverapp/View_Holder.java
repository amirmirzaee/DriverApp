package com.example.user.driverapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.user.driverapp.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import java.util.zip.Inflater;


public class View_Holder extends RecyclerView.ViewHolder implements OnMapReadyCallback {
    public  SupportMapFragment mapFragment;
    private GoogleMap gmap;
    private Context context;

    public TextView sorseFrag, distinationFrag;
    public Button navFrag, ditalFrag, startFrag;

    public View_Holder(View itemView,FragmentActivity context) {
        super(itemView);
        sorseFrag = (TextView) itemView.findViewById(R.id.sorsfrag);
        distinationFrag = (TextView) itemView.findViewById(R.id.distinationfrag);
        navFrag = (Button) itemView.findViewById(R.id.navFrag);
        ditalFrag = (Button) itemView.findViewById(R.id.ditalFrag);
        startFrag = (Button) itemView.findViewById(R.id.startFrag);


    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

    }
}
