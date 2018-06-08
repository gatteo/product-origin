package com.mikwee.barcodezxingembedded.entities;
public class CountryCode {
    private String contry_name;
    private String fly;
    private int id;
    private String latitude;
    private String longitude;
    private int marge;

    public CountryCode(){}


    public CountryCode(int id, String contry_name, int marge, String fly) {
        this.id = id;
        this.contry_name = contry_name;
        this.marge = marge;
        this.fly = fly;
    }

    public CountryCode(int id, String contry_name, int marge, String fly, String longitude, String latitude) {
        this.id = id;
        this.contry_name = contry_name;
        this.marge = marge;
        this.fly = fly;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContry_name() {
        return this.contry_name;
    }

    public void setContry_name(String contry_name) {
        this.contry_name = contry_name;
    }

    public int getMarge() {
        return this.marge;
    }

    public void setMarge(int marge) {
        this.marge = marge;
    }

    public String getFly() {
        return this.fly;
    }

    public void setFly(String fly) {
        this.fly = fly;
    }

    public String getLatitude() {
        return this.latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return this.longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String toString() {
        return "CountyCode{id=" + this.id + ", contry_name='" + this.contry_name + '\'' + ", marge=" + this.marge + ", fly='" + this.fly + '\'' + '}';
    }
}
