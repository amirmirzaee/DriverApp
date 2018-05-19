package com.example.user.driverapp;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MainActivity extends RuntimePermissionsActivity implements OnMapReadyCallback, LocationListener, NavigationView.OnNavigationItemSelectedListener {

    private GoogleMap mMap;
    private static final int ACCESS_PERMISSEN_LOCATION = 1;
    private DrawerLayout drawer;
    private int onServies, showTraffic, moonSun;
    private LocationManager locationManager;
    private static final long MIN_TIME = 400;
    private static final float MIN_DISTANCE = 1000;
    private static final int Time_Between_Two_Back = 2000;
    private long TimeBackPressed;
    private boolean loop_started;
    private Handler handler = new Handler();
    private EditText searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        makeNav();
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        onServies = 0;
        showTraffic = 0;
        moonSun = 0;


        searchView=(EditText)findViewById(R.id.citysearch);

        searchView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
//
                Intent intent=new Intent(MainActivity.this,SearchActivity3.class);
                startActivity(intent);
                return false;
            }
        });

            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
//        getLocation();


        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, MIN_TIME, MIN_DISTANCE, this); //You can also use LocationManager.GPS_PROVIDER and LocationManager.PASSIVE_PROVIDER

//        loopStart();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        MainActivity.super.requestAppPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, ACCESS_PERMISSEN_LOCATION);


        String  str;
        Bundle mbundle= getIntent().getExtras();
        if (mbundle != null) {
            str = mbundle.getString("locationText");
            setMark(str);
        }


        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                mMap.clear();
                mMap.addMarker(new MarkerOptions().position(latLng).title("Marker in Sydney"));
            }
        });
    }

    public void onPermissionsGranted(int requestCode) {
        if (requestCode == ACCESS_PERMISSEN_LOCATION)
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                return;
            }
        mMap.setMyLocationEnabled(true);

    }

    @Override
    public void onPermissionsDeny(int requestCode) {
        requestPermission();
    }

    public void requestPermission() {
        LocationManager locationManager = (LocationManager)
                getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();

        assert locationManager != null;

        boolean statusOfGPS = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (statusOfGPS) {
//            try {
//                //set time in mili
//                Thread.sleep(1000);
//
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//            Toast.makeText(this, "sssssssss", Toast.LENGTH_SHORT).show();
//
//            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//                // TODO: Consider calling
//                //    ActivityCompat#requestPermissions
//                // here to request the missing permissions, and then overriding
//                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                //                                          int[] grantResults)
//                // to handle the case where the user grants the permission. See the documentation
//                // for ActivityCompat#requestPermissions for more details.
//                return;
//            }
//            Location location = locationManager.getLastKnownLocation(locationManager
//                    .getBestProvider(criteria, false));
//            double latitude = location.getLatitude();
//            double longitude = location.getLongitude();
//            Log.d("pos2",latitude+"   "+longitude);
        } else {
            AlertDialog.Builder alter = new AlertDialog.Builder(MainActivity.this);
            alter.setTitle("GPS");
            alter.setMessage("please turn on GPS");
            alter.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent gpsOptionsIntent = new Intent(
                            android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivity(gpsOptionsIntent);
                }
            });
            alter.setNegativeButton("Calcel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            alter.show();

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        requestPermission();





    }


    @Override
    public void onLocationChanged(Location location) {

        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 15);
        mMap.animateCamera(cameraUpdate);
        locationManager.removeUpdates(this);
//        loopStart();
        Log.d("currentpos", "onLocationChanged: " + location.getLatitude());

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


    public void makeNav() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("");


        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onServies == 0) {
//                fab.setImageResource(R.drawable.power_on);
                    fab.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#7fd769")));
                    Snackbar.make(view, "آماده برای انجام سرویس", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    onServies = 1;
                } else {
//                    fab.setImageResource(R.drawable.power_off);
                    fab.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ff4a42")));
                    Snackbar.make(view, "غیر فعال", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    onServies = 0;
                }
            }
        });


        final FloatingActionButton traf = (FloatingActionButton) findViewById(R.id.traffic);
        traf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (showTraffic == 0) {
//                fab.setImageResource(R.drawable.power_on);
                    traf.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#4a4ce6")));
                    Snackbar.make(view, "نمایش ترافیک", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    showTraffic = 1;
                    mMap.setTrafficEnabled(true);
                } else {
//                    fab.setImageResource(R.drawable.power_off);
                    traf.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#e1e0e0")));
                    Snackbar.make(view, "عدم نمایش ترافیک", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    showTraffic = 0;
                    mMap.setTrafficEnabled(false);
                }
            }
        });


        final FloatingActionButton msun = (FloatingActionButton) findViewById(R.id.nightmod);
        msun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (moonSun == 0) {
//                    msun.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#4a4ce6")));
                    msun.setImageResource(R.drawable.ic_brightness_3_black_24dp);
//                    Snackbar.make(view, "نمایش ترافیک", Snackbar.LENGTH_LONG)
//                            .setAction("Action", null).show();
                    moonSun = 1;

                    boolean success = mMap.setMapStyle(new MapStyleOptions(getResources()
                            .getString(R.string.style_json)));

                    if (!success) {
                        Log.e("TAG", "Style parsing failed.");
                    }
                } else {
                    msun.setImageResource(R.drawable.ic_wb_sunny_black_24dp);
//                    Snackbar.make(view, "عدم نمایش ترافیک", Snackbar.LENGTH_LONG)
//                            .setAction("Action", null).show();
                    moonSun = 0;


                    boolean success = mMap.setMapStyle(new MapStyleOptions(getResources()
                            .getString(R.string.style2_json)));

                    if (!success) {
                        Log.e("TAG", "Style parsing failed.");
                    }


                }
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.addDrawerListener(toggle);
//        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {

        if (drawer.isDrawerOpen(GravityCompat.END)) {
            drawer.closeDrawer(GravityCompat.END);
        } else {
            if (TimeBackPressed + Time_Between_Two_Back > System.currentTimeMillis()) {
                super.onBackPressed();
                finish();
                return;
            } else {
                Toast.makeText(getBaseContext(), "به منظور خروج دوباره کلیک کنید", Toast.LENGTH_SHORT).show();
            }
            TimeBackPressed = System.currentTimeMillis();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            drawer.openDrawer(GravityCompat.END);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            Intent intent = new Intent(MainActivity.this, TravelList.class);
            startActivity(intent);
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {
            Intent intent = new Intent(MainActivity.this, ManageActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_share) {
            Intent intent = new Intent(MainActivity.this, TravelList.class);
            startActivity(intent);

        } else if (id == R.id.nav_send) {

        }

        drawer.closeDrawer(GravityCompat.END);
        return true;
    }

    private void sendLocation() {

        double lat = 0;
        double lon=0;
        Location loc;
        LocationManager mlocManager;
        String provider;

        mlocManager =
                (LocationManager) getSystemService(Context.LOCATION_SERVICE);


        Criteria criteria = new Criteria();
        //criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setAccuracy(Criteria.ACCURACY_COARSE); //Try this instead
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);
        criteria.setCostAllowed(true);
        criteria.setPowerRequirement(Criteria.POWER_LOW);
        provider = mlocManager.getBestProvider(criteria, true);
        if (provider != null) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            loc = mlocManager.getLastKnownLocation(provider);

            lat = loc.getLatitude();
            lon = loc.getLongitude();
        }
        Toast.makeText(MainActivity.this,lat+"***"+lon, Toast.LENGTH_SHORT).show();
    }


    public void loopStart(){
        handler.postDelayed(new Runnable() {
            public void run() {
                sendLocation();
                handler.postDelayed(this, 5000);
            }
        }, 5000);
    }



//    private final LocationListener mLocationListener = new LocationListener() {
//        @Override
//        public void onLocationChanged(final Location location) {
//
//
//        }
//
//        @Override
//        public void onStatusChanged(String provider, int status, Bundle extras) {
//
//        }
//
//        @Override
//        public void onProviderEnabled(String provider) {
//
//        }
//
//        @Override
//        public void onProviderDisabled(String provider) {
//
//        }
//    };
//
//    void getLocation() {
//
//        try {
//            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5, this);
//
//        }
//        catch(SecurityException e) {
//            e.printStackTrace();
//        }
//
//    }

private Address gotolocation(String searchString){
    Geocoder gc=new Geocoder(this);
    List<Address> list=null;
    try {
        list=gc.getFromLocationName(searchString,1);
        if (list.size()>0){

            Address add =list.get(0);
            Log.d("cityyyy",list.get(0).toString());
            return add;
        }
        else
            return null;
    } catch (IOException e) {
        e.printStackTrace();
        return null;
    }
}

private void setMark(String city){

    Address add=gotolocation(city);
if (add!=null){
    Log.d("cityyyy",add.getAddressLine(0));
                LatLng distination =new LatLng(add.getLatitude(),add.getLongitude());
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(distination,15));
                mMap.addMarker(new MarkerOptions().position(distination).title(city));
}

}
}
