package com.example.navigation;

import com.google.android.gms.common.util.Strings;

public class User {

    private String usertId;
    private String Name;
    private double longitude;
    private double laltitude;


    public User(){
        //this constructor is required
    }

    public User(String userid, String userN, double longt, double lalt) {

        this.Name = userN;
        this.longitude = longt;
        this.laltitude = lalt ;
        this.usertId = userid ;
    }
    public String getArtistId() {
        return usertId;
    }

    public String getUserName() {
        return Name;
    }

    public double getUserlongitude() {
        return longitude;
    }

    public double getUserlaltitude() {
        return laltitude;
    }
}