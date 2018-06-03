package com.example.user.driverapp;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

public class LocationService extends Service implements LocationListener {
    private Handler handler = new Handler();
    private LocationManager locationManager;

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this, "start", Toast.LENGTH_LONG).show();
        loopStart();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "stop", Toast.LENGTH_LONG).show();

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {


        return null;
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }


    public void loopStart() {
        handler.postDelayed(new Runnable() {
            public void run() {
                getLocation();   // this method will contain your almost-finished HTTP calls
//                handler.postDelayed(this, 5000);
            }
        }, 5000);
    }

    void getLocation() {


        try {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            assert locationManager != null;
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
//                    Log.d("currentpos", "onLocationChanged: " + location.getLatitude());
//
                    Toast.makeText(LocationService.this, String.valueOf(location.getLatitude()), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {

                }

                @Override
                public void onProviderEnabled(String provider) {

                }

                @Override
                public void onProviderDisabled(String provider) {

                }
            });
        }
        catch(SecurityException e) {
            e.printStackTrace();
        }

    }
}
