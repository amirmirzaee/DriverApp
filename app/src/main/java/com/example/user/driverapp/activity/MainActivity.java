package com.example.user.driverapp.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.user.driverapp.BarbanetApplication;
import com.example.user.driverapp.LocationService;
import com.example.user.driverapp.ManageActivity;
import com.example.user.driverapp.MyConstants;
import com.example.user.driverapp.R;
import com.example.user.driverapp.RuntimePermissionsActivity;
import com.example.user.driverapp.adapter.RecyclerAdapter;
import com.example.user.driverapp.model.job_list_model;
import com.example.user.driverapp.model.user.LogoutUser;
import com.example.user.driverapp.webService.ApiInterface;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;

import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    private Handler handler = new Handler();
    private String TAG = "man";
    private ImageView imageView;
    private TextView dname, credit;
    List<job_list_model> data=fill_with_data();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        makeNav();
        makeList();


        drawer = findViewById(R.id.drawer_layout);
        onServies = 0;
        showTraffic = 0;
        moonSun = 0;


//        imageView = findViewById(R.id.imSearch);
//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                searchPanel();
//            }
//        });


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        getLocation();


        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, MIN_TIME, MIN_DISTANCE, this); //You can also use LocationManager.GPS_PROVIDER and LocationManager.PASSIVE_PROVIDER

    }

    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        MainActivity.super.requestAppPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, ACCESS_PERMISSEN_LOCATION);


/**
 * get location string from search activity
 **/
//        String  str;
//        Bundle mbundle= getIntent().getExtras();
//        if (mbundle != null) {
//            str = mbundle.getString("locationText");
//            setMark(str);
//        }


        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                mMap.clear();
                mMap.addMarker(new MarkerOptions().position(latLng).title(""));
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


    /**
     * if GPS is not enabled open setting
     */
    public void requestPermission() {


        NETDialog();

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


    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    public void makeNav() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        CollapsingToolbarLayout collapsingToolbar =  findViewById(R.id.collapsing_toolbar_layout);
        final SharedPreferences sharedPreferences = getSharedPreferences(MyConstants.tok, MODE_PRIVATE);

        final FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onServies == 0) {
                    fab.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.enableService)));
                    Snackbar.make(view, getResources().getString(R.string.redy_to_job), Snackbar.LENGTH_LONG)
                            .setAction(getResources().getString(R.string.Action), null).show();
                    onServies = 1;
                } else {
//                    fab.setImageResource(R.drawable.power_off);
                    fab.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.disableService)));
                    Snackbar.make(view, getResources().getString(R.string.disable), Snackbar.LENGTH_LONG)
                            .setAction(getResources().getString(R.string.Action), null).show();
                    onServies = 0;
                }
            }
        });


        final FloatingActionButton traf = findViewById(R.id.traffic);
        traf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (showTraffic == 0) {
//                fab.setImageResource(R.drawable.power_on);
                    traf.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.show_Traffic)));
                    Snackbar.make(view, getResources().getString(R.string.show_Traffic), Snackbar.LENGTH_LONG)
                            .setAction(getResources().getString(R.string.Action), null).show();
                    showTraffic = 1;
                    mMap.setTrafficEnabled(true);
                } else {
//                    fab.setImageResource(R.drawable.power_off);
                    traf.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.hide_traffic)));
                    Snackbar.make(view, getResources().getString(R.string.hide_traffic), Snackbar.LENGTH_LONG)
                            .setAction(getResources().getString(R.string.Action), null).show();
                    showTraffic = 0;
                    mMap.setTrafficEnabled(false);
                }
            }
        });


        final FloatingActionButton msun = findViewById(R.id.nightmod);
        msun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (moonSun == 0) {
                    msun.setImageResource(R.drawable.ic_brightness_3_black_24dp);
                    moonSun = 1;

                    boolean success = mMap.setMapStyle(new MapStyleOptions(getResources()
                            .getString(R.string.style_json)));

                    if (!success) {
                        Toast.makeText(MainActivity.this, getResources().getString(R.string.error), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    msun.setImageResource(R.drawable.ic_wb_sunny_black_24dp);
                    moonSun = 0;


                    boolean success = mMap.setMapStyle(new MapStyleOptions(getResources()
                            .getString(R.string.style2_json)));

//                    if (!success) {
//
//                    }


                }
            }
        });

//        DrawerLayout drawer = findViewById(R.id.drawer_layout);


        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header = navigationView.getHeaderView(0);
        dname = header.findViewById(R.id.dname);
        credit = header.findViewById(R.id.credit);
        DecimalFormat priceFormatter = new DecimalFormat("#,###");
        double t = Double.parseDouble(sharedPreferences.getString(MyConstants.totalCredit, ""));
        dname.setText(sharedPreferences.getString(MyConstants.fname, "") + " " + sharedPreferences.getString(MyConstants.lname, ""));
        credit.setText(priceFormatter.format(t));


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
                Toast.makeText(getBaseContext(), getResources().getString(R.string.double_click), Toast.LENGTH_SHORT).show();
            }
            TimeBackPressed = System.currentTimeMillis();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_settings) {
            drawer.openDrawer(GravityCompat.END);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            Intent intent = new Intent(MainActivity.this, TravelList.class);
            startActivity(intent);
        } else if (id == R.id.nav_gallery) {
            stopService(new Intent(MainActivity.this, LocationService.class));

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {
            Intent intent = new Intent(MainActivity.this, ManageActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_share) {
            Intent intent = new Intent(MainActivity.this, TravelList.class);
            startActivity(intent);

        } else if (id == R.id.nav_send) {

            ApiInterface apiInterface = ((BarbanetApplication) getApplication()).getRetrofitClient();
            Call<LogoutUser> call = apiInterface.logoutUser();
            call.enqueue(new Callback<LogoutUser>() {
                @Override
                public void onResponse(Call<LogoutUser> call, Response<LogoutUser> response) {
                    if (response.body().getObject() == "success") ;
                    {
                        final SharedPreferences sharedPreferences = getSharedPreferences(MyConstants.tok, MODE_PRIVATE);
                        SharedPreferences.Editor sedite = sharedPreferences.edit();
                        sedite.putString(MyConstants.tok, "");
                        sedite.putBoolean(MyConstants.islogin, false);
                        sedite.apply();
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                }

                @Override
                public void onFailure(Call<LogoutUser> call, Throwable t) {
                    Toast.makeText(MainActivity.this, getResources().getString(R.string.error), Toast.LENGTH_SHORT).show();
                }
            });
        }

        drawer.closeDrawer(GravityCompat.END);
        return true;
    }

//    private void sendLocation() {
//
//        double lat = 0;
//        double lon=0;
//        Location loc;
//        LocationManager mlocManager;
//        String provider;
//
//        mlocManager =
//                (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//
//
//        Criteria criteria = new Criteria();
//        //criteria.setAccuracy(Criteria.ACCURACY_FINE);
//        criteria.setAccuracy(Criteria.ACCURACY_COARSE); //Try this instead
//        criteria.setAltitudeRequired(false);
//        criteria.setBearingRequired(false);
//        criteria.setCostAllowed(true);
//        criteria.setPowerRequirement(Criteria.POWER_LOW);
//        provider = mlocManager.getBestProvider(criteria, true);
//        if (provider != null) {
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
//            loc = mlocManager.getLastKnownLocation(provider);
//
//            lat = loc.getLatitude();
//            lon = loc.getLongitude();
//        }
//        Toast.makeText(MainActivity.this,lat+"***"+lon, Toast.LENGTH_SHORT).show();
//    }


//    public void loopStart(){
//        handler.postDelayed(new Runnable() {
//            public void run() {
//                sendLocation();
//                handler.postDelayed(this, 5000);
//            }
//        }, 5000);
//    }


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
//    }defe

    /**
     * get the city name and convert to latlang
     */

    private Address gotolocation(String searchString) {
        Geocoder gc = new Geocoder(this);
        List<Address> list;
        try {
            list = gc.getFromLocationName(searchString, 1);
            if (list.size() > 0) {

                Address add = list.get(0);
                return add;
            } else
                return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * get the city name searched and set marker
     */
    private void setMark(String city) {

        Address add = gotolocation(city);
        if (add != null) {
            LatLng distination = new LatLng(add.getLatitude(), add.getLongitude());
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(distination, 15));
            mMap.addMarker(new MarkerOptions().position(distination).title(city));
        }

    }

//    private void searchPanel() {
//        final PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
//                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);
//
//
//        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
//
//
//            @Override
//            public void onPlaceSelected(Place place) {
//
//                setMark(place.getName().toString());
//
//            }
//
//            @Override
//            public void onError(Status status) {
//                // TODO: Handle the error.
//            }
//        });
//
//        try {
//            autocompleteFragment.setBoundsBias(new LatLngBounds(
//                    new LatLng(35.735670, 51.614763),
//                    new LatLng(35.821442, 50.918683)));
//
//            AutocompleteFilter typeFilter = new AutocompleteFilter.Builder()
//                    .setCountry("IR")
//                    .build();
//            autocompleteFragment.setFilter(typeFilter);
//
//            Intent intent =
//                    new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_OVERLAY)
//                            .setFilter(typeFilter)
//                            .build(this);
//
//
//            int PLACE_AUTOCOMPLETE_REQUEST_CODE = 1;
//            startActivityForResult(intent, PLACE_AUTOCOMPLETE_REQUEST_CODE);
//        } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e) {
//            // TODO: Handle the error.
//        }
//    }

    /**
     * call the update location function in the loop
     */
//    public void loopStart() {
//        handler.postDelayed(new Runnable() {
//            public void run() {
//                getLocation();   // this method will contain your almost-finished HTTP calls
////                handler.postDelayed(this, 5000);
//            }
//        }, 5000);
//    }

    /**
     * get current location and sed to server at 5000 milli Second
     */
    void getLocation() {


        try {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            assert locationManager != null;
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {

                    Toast.makeText(MainActivity.this, String.valueOf(location.getLatitude()), Toast.LENGTH_SHORT).show();
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
        } catch (SecurityException e) {
            e.printStackTrace();
        }

    }

    private void GpsDialog() {
        LocationManager locationManager = (LocationManager)
                getSystemService(Context.LOCATION_SERVICE);


        assert locationManager != null;

        final boolean statusOfGPS = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (statusOfGPS) {
            Intent intent = new Intent(this, LocationService.class);
            startService(intent);

        } else {
            AlertDialog.Builder alter = new AlertDialog.Builder(MainActivity.this);
            setFinishOnTouchOutside(false);
            alter.setTitle(getResources().getString(R.string.gps));
            alter.setMessage(getResources().getString(R.string.enable_gps));
            alter.setPositiveButton(getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    LocationManager locationManager1 = (LocationManager)
                            getSystemService(Context.LOCATION_SERVICE);
                    assert locationManager1 != null;
                    boolean statusOfGPS1 = locationManager1.isProviderEnabled(LocationManager.GPS_PROVIDER);
                    if (!statusOfGPS1) {
                        Intent gpsOptionsIntent = new Intent(
                                android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivity(gpsOptionsIntent);
                    }

                }
            });
            alter.setNegativeButton(getResources().getString(R.string.cancel), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    LocationManager locationManager2 = (LocationManager)
                            getSystemService(Context.LOCATION_SERVICE);
                    assert locationManager2 != null;
                    boolean statusOfGPS2 = locationManager2.isProviderEnabled(LocationManager.GPS_PROVIDER);
                    if (!statusOfGPS2)
                        finish();
                }
            });
            alter.show();


        }

    }

    private void NETDialog() {
        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        final boolean netchek = (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED);
        if (netchek) {
            GpsDialog();
            //we are connected to a network
            connected = true;
        } else {
            AlertDialog.Builder alter = new AlertDialog.Builder(MainActivity.this);
            setFinishOnTouchOutside(false);
            alter.setTitle(getResources().getString(R.string.internet));
            alter.setMessage(getResources().getString(R.string.enable_internet));
            alter.setPositiveButton("setting", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (!netchek) {
                        Intent netIntent = new Intent(Intent.ACTION_MAIN);
                        netIntent.setClassName("com.android.settings", "com.android.settings.wifi.WifiSettings");
                        startActivity(netIntent);
                    }
                }
            });
            alter.setNegativeButton(getResources().getString(R.string.cancel), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (!netchek)
                        finish();
                }
            });
            alter.show();
            connected = false;
        }

    }

    private void makeList(){

        RecyclerView recyclerView=findViewById(R.id.my_recycel);
        RecyclerAdapter recyclerAdapter=new RecyclerAdapter(fill_with_data(),this,this);
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
