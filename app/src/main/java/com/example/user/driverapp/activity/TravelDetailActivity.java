package com.example.user.driverapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.user.driverapp.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class TravelDetailActivity extends AppCompatActivity  implements OnMapReadyCallback {

    private TextView sorsAddress,disAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_detail);


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.maplite);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

        GoogleMap mMap = googleMap;
        Bundle bundle = getIntent().getParcelableExtra("position");
        if (bundle != null) {

        // Add a marker in Sydney and move the camera
        LatLng sourceLocat = bundle.getParcelable("latlangsaddress");
        LatLng disLocat = bundle.getParcelable("latlangdaddress");



            assert sourceLocat != null;
            mMap.addMarker(new MarkerOptions().position(sourceLocat).title("Marker  "));
            assert disLocat != null;
            mMap.addMarker(new MarkerOptions().position(disLocat).title("Marker  "));
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,15));
            LatLngBounds bounds = new LatLngBounds.Builder()
                    .include(sourceLocat)
                    .include(disLocat)
                    .build();
            mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 50));
        }
    }
}
