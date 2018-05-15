package com.example.user.driverapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Address;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class searchCityAdapter extends BaseAdapter {
    private Context mcontext;
    private List<Address> cityList;

    public searchCityAdapter() {
        this.mcontext = mcontext;
        this.cityList = cityList;
    }

    public searchCityAdapter(Context mcontext, List<Address> cityList) {
        this.mcontext = mcontext;
        this.cityList = cityList;
    }

    @Override
    public int getCount() {
        return cityList.size();
    }

    @Override
    public Object getItem(int i) {
        return cityList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        @SuppressLint("ViewHolder") View rowView = LayoutInflater.from(mcontext).inflate(R.layout.search_city_layout, parent, false);
        TextView cityName,cityDitale;
        cityName=(TextView)rowView.findViewById(R.id.cityName);
        cityDitale=(TextView)rowView.findViewById(R.id.cityDitail);
        Address add =cityList.get(i);
        if(add!=null) {
        cityName.setText(add.getFeatureName());
            cityDitale.setText(add.getAddressLine(0));
            Log.d("cityyyy", add.toString());
            Log.d("cityyyy", "1");
        }
        return rowView;
    }
}
