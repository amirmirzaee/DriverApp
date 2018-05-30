package com.example.user.driverapp.model.user;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserResponse implements Parcelable
{


    @SerializedName("object")
    @Expose
    private User user;
    @SerializedName("error")
    @Expose
    private String error;
    @SerializedName("description")
    @Expose
    private String description;
    public final static Parcelable.Creator<UserResponse> CREATOR = new Creator<UserResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public UserResponse createFromParcel(Parcel in) {
            return new UserResponse(in);
        }

        public UserResponse[] newArray(int size) {
            return (new UserResponse[size]);
        }

    }
            ;

    protected UserResponse(Parcel in) {
        this.user = ((User) in.readValue((String.class.getClassLoader())));
        this.error = ((String) in.readValue((String.class.getClassLoader())));
        this.description = ((String) in.readValue((String.class.getClassLoader())));
    }

    public UserResponse() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(user);
        dest.writeValue(error);
        dest.writeValue(description);
    }

    public int describeContents() {
        return 0;
    }

}
