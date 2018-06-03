package com.example.user.driverapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.CardView;
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
    public SupportMapFragment mapFragment;
    private GoogleMap gmap;
    private Context context;

    public TextView  distinationFrag, detail;
//    public Button navFrag, ditalFrag, startFrag;
    public CardView cardView;
    public com.example.user.driverapp.customView.NormalTextView sorseFrag;

    public View_Holder(View itemView, FragmentActivity context) {
        super(itemView);
        sorseFrag = itemView.findViewById(R.id.sorsfrag);
        distinationFrag = itemView.findViewById(R.id.distinationfrag);
        cardView = itemView.findViewById(R.id.card_view);

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

    }
}
