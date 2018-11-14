package rosieblair.donationtracker.model;


import java.util.Arrays;
import java.util.List;

/**
 * Model class for a location
 */
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

//    /*************
//     * Getters & setters for all of Location's attributes
//     */

    /**
     * Gets the location id
     * @return the location id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the location id
     * @param id a location id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the location key
     * @return the location key
     */
    public int getKey() {
        return key;
    }

    /**
     * Sets the location key
     * @param key a location key
     */
    public void setKey(int key) {
        this.key = key;
    }

    /**
     * Gets the location name
     * @return the location name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the location name
     * @param name a location name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the location latitude
     * @return the location latitude
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     * Sets the location latitude
     * @param latitude a latitude
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    /**
     * Gets the location longitude
     * @return the location longitude
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     * Sets the location longitude
     * @param longitude a longitude
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    /**
     * Gets the location address
     * @return the location address
     */
    public String getStreetAddress() {
        return streetAddress;
    }

    /**
     * Sets the location address
     * @param streetAddress an address
     */
    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    /**
     * Gets the location's city
     * @return the location's city
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the location's city
     * @param city a city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Gets the location's state
     * @return the location's state
     */
    public String getState() {
        return state;
    }

    /**
     * Sets the location's state
     * @param state a state
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Gets the location zip code
     * @return the location zip code
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * Sets the location zip code
     * @param zipCode a zip code
     */
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * Gets the location type
     * @return the location type
     */
    public String getLocType() {
        return locType;
    }

    /**
     * Sets the location type
     * @param locType a location type
     */
    public void setLocType(String locType) {
        this.locType = locType;
    }

    /**
     * Gets the location phone number
     * @return the location phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the location phone number
     * @param phoneNumber a phone number
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Gets the location website url
     * @return the location website url
     */
    public String getWebsite() {
        return website;
    }

    /**
     * Sets the location website url
     * @param website a website url
     */
    public void setWebsite(String website) {
        this.website = website;
    }

}
