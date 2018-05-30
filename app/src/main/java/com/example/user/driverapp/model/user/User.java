package com.example.user.driverapp.model.user;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class User {


    @SerializedName("accessToken")
    @Expose
    private String accessToken;
    @SerializedName("refreshToken")
    @Expose
    private String refreshToken;
    @SerializedName("personType")
    @Expose
    private String personType;
    @SerializedName("roles")
    @Expose
    private List<String> roles = null;
    @SerializedName("manageView")
    @Expose
    private Boolean manageView;
    @SerializedName("firstname")
    @Expose
    private String firstname;
    @SerializedName("lastname")
    @Expose
    private String lastname;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("totalCredit")
    @Expose
    private Integer totalCredit;
    @SerializedName("username")
    @Expose
    private String userName;
    @SerializedName("validationCode")
    @Expose
    private String validationCode;

    private String password;


    public final static Parcelable.Creator<Object> CREATOR = new Parcelable.Creator<Object>() {


        @SuppressWarnings({
                "unchecked"
        })
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        public User[] newArray(int size) {
            return (new User[size]);
        }

    }
            ;

    protected User(Parcel in) {
        this.accessToken = ((String) in.readValue((String.class.getClassLoader())));
        this.refreshToken = ((String) in.readValue((String.class.getClassLoader())));
        this.personType = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.roles, (java.lang.String.class.getClassLoader()));
        this.manageView = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.firstname = ((String) in.readValue((String.class.getClassLoader())));
        this.lastname = ((String) in.readValue((String.class.getClassLoader())));
        this.mobile = ((String) in.readValue((String.class.getClassLoader())));
        this.email = ((String) in.readValue((String.class.getClassLoader())));
        this.totalCredit = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.userName = ((String) in.readValue((String.class.getClassLoader())));
        this.validationCode = ((String) in.readValue((String.class.getClassLoader())));
        this.password = ((String) in.readValue((String.class.getClassLoader())));
    }

    public User() {
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getPersonType() {
        return personType;
    }

    public void setPersonType(String personType) {
        this.personType = personType;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public Boolean getManageView() {
        return manageView;
    }

    public void setManageView(Boolean manageView) {
        this.manageView = manageView;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getValidationCode() {
        return validationCode;
    }

    public void setValidationCode(String validationCode) {
        this.validationCode = validationCode;
    }

    public Integer getTotalCredit() {
        return totalCredit;
    }

    public void setTotalCredit(Integer totalCredit) {
        this.totalCredit = totalCredit;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(accessToken);
        dest.writeValue(refreshToken);
        dest.writeValue(personType);
        dest.writeList(roles);
        dest.writeValue(manageView);
        dest.writeValue(firstname);
        dest.writeValue(lastname);
        dest.writeValue(mobile);
        dest.writeValue(email);
        dest.writeValue(totalCredit);
        dest.writeValue(userName);
        dest.writeValue(validationCode);
        dest.writeValue(password);
    }

    public int describeContents() {
        return 0;
    }

}
