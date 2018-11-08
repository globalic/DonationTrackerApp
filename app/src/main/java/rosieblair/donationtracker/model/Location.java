package rosieblair.donationtracker.model;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import rosieblair.donationtracker.R;

import static rosieblair.donationtracker.activities.EmployeeAppScreen.CITY_POSITION;
import static rosieblair.donationtracker.activities.EmployeeAppScreen.LATITUDE_POSITION;
import static rosieblair.donationtracker.activities.EmployeeAppScreen.LONGITUDE_POSITION;
import static rosieblair.donationtracker.activities.EmployeeAppScreen.NAME_POSITION;
import static rosieblair.donationtracker.activities.EmployeeAppScreen.PHONE_NUMBER_POSITION;
import static rosieblair.donationtracker.activities.EmployeeAppScreen.STATE_POSITION;
import static rosieblair.donationtracker.activities.EmployeeAppScreen.STREET_ADDRESS_POSITION;
import static rosieblair.donationtracker.activities.EmployeeAppScreen.TYPE_POSITION;
import static rosieblair.donationtracker.activities.EmployeeAppScreen.WEBSITE_POSITION;
import static rosieblair.donationtracker.activities.EmployeeAppScreen.ZIP_CODE_POSITION;

public class Location {

    private int id;
    private int key;
    private String name;
    private String latitude;
    private String longitude;
    private String streetAddress;
    private String city;
    private String state;
    private String zipCode;
    private String locType;
    private String phoneNumber;
    private String website;

    /**
     * A list of possible types a location can be defined as.
     */
    public static final List<String> locTypes = Arrays.asList("DROPOFF", "STORE", "WAREHOUSE");

    /*************
     * Getters & setters for all of Location's attributes
     */
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getKey() {
        return key;
    }
    public void setKey(int key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getLatitude() {
        return latitude;
    }
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getStreetAddress() {
        return streetAddress;
    }
    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getLocType() {
        return locType;
    }
    public void setLocType(String locType) {
        this.locType = locType;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getWebsite() {
        return website;
    }
    public void setWebsite(String website) {
        this.website = website;
    }

}
