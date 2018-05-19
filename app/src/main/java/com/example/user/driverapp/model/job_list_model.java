package com.example.user.driverapp.model;

import com.google.android.gms.maps.model.LatLng;

public class job_list_model {
    public String sorse;
    public String distin;
    public LatLng sourcePos;
    public LatLng disPos;

    public job_list_model(String sorse, String distin, LatLng sourcePos, LatLng disPos) {
        this.sorse = sorse;
        this.distin = distin;
        this.sourcePos = sourcePos;
        this.disPos = disPos;
    }

    public job_list_model() {
    }

    public String getSorse() {
        return sorse;
    }

    public void setSorse(String sorse) {
        this.sorse = sorse;
    }

    public String getDistin() {
        return distin;
    }

    public void setDistin(String distin) {
        this.distin = distin;
    }

    public LatLng getSourcePos() {
        return sourcePos;
    }

    public void setSourcePos(LatLng sourcePos) {
        this.sourcePos = sourcePos;
    }

    public LatLng getDisPos() {
        return disPos;
    }

    public void setDisPos(LatLng disPos) {
        this.disPos = disPos;
    }
}
