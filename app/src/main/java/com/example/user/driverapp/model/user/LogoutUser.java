package com.example.user.driverapp.model.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LogoutUser {

    @SerializedName("object")
    @Expose
    private String object;
    @SerializedName("error")
    @Expose
    private String error;
    @SerializedName("description")
    @Expose
    private String description;

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
