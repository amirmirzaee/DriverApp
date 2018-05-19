package com.example.user.driverapp;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.speech.RecognizerIntent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SearchActivity extends AppCompatActivity {

    EditText searchLocation;
    ListView resultlist;
    private final int REQ_CODE_SPEECH_INPUT = 100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        findView();
        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.speek);
        final FloatingActionButton searc = (FloatingActionButton) findViewById(R.id.searchbtnn);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                promptSpeechInput();
            }
        });

        searc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txt =searchLocation.getText().toString();
//                setLocation(txt);
                searchCityAdapter adapter=new searchCityAdapter(SearchActivity.this,gotolocation(txt));
                Log.d("cityyyy", String.valueOf(gotolocation(txt).size()));
                resultlist.setAdapter(adapter);
            }
        });


    }
    /**
     * Showing google speech input dialog
     * */
    private void promptSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "fa");
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                "Hi speak something");
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {

        }
    }

    /**
     * Receiving speech input
     * */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
//                    searchLocation.setText(result.get(0));
                    setLocation(result.get(0));


                }
                break;
            }

        }
    }
private void setLocation (String city){
    Intent intent =new Intent(SearchActivity.this, MainActivity.class);
    intent.putExtra("locationText",city);
    startActivity(intent);
    finish();
}

    private void findView(){
        searchLocation=(EditText)findViewById(R.id.searchLocation);
        resultlist=(ListView)findViewById(R.id.resultlist);

    }

    private List<Address> gotolocation(String searchString){
        Geocoder gc=new Geocoder(this);
        List<Address> list=null;
        try {
            list=gc.getFromLocationName(searchString,5);
            if (list.size()>0){

//                Address add =list.get(0);
//                Log.d("cityyyy",list.get(0).toString());
                return list;
            }
            else
                return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
