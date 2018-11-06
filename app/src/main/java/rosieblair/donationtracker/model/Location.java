package rosieblair.donationtracker.model;

import java.util.ArrayList;
import java.util.List;

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

    private List<Item> inventory;
    private List<User> employeeList;

    public Location(int key, String name, String latitude, String longitude,
                    String streetAddress, String city, String state, String zipCode,
                    String locType, String phoneNumber, String website) {

        this.key = key;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.locType = locType;
        this.phoneNumber = phoneNumber;
        this.website = website;

        this.inventory = new ArrayList<>();
        this.employeeList = new ArrayList<>();
    }


}
