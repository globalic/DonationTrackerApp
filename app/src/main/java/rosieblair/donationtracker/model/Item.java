package rosieblair.donationtracker.model;

import android.support.annotation.NonNull;

import java.util.Arrays;
import java.util.List;

/**
 * Model class for an item
 */
public class Item {

    private int id;
    private String time;
    private String shortDescription;
    private String fullDescription;
    private String value;
    private String category;
    private String comments;
    private int itemKey;

    public static int keyCounter;

    /**
     * A list of all possible categories defined for an item.
     */
    private static final List<String> itemCategories = Arrays.asList("Clothing", "Hat", "Kitchen",
            "Electronics", "Household", "Other");

    /**
     * No-Arg Constructor to create an Item object (for use with activities only).
     */
    public Item() {
        this(null, null, null, null,
                itemCategories.get(0), null, 1);
    }

    /**
     * Constructor to make a new Item Object
     *
     * @param time             the item's donation time
     * @param shortDescription a short description of the item
     * @param fullDescription  a full (longer) description of the item
     * @param value            the item's value
     * @param category         the item's category
     * @param itemKey          the key that links the item to a location
     */
    private Item(String time, String shortDescription, String fullDescription, String value,
            String category, String comments, int itemKey) {
        this.time = time;
        this.shortDescription = shortDescription;
        this.fullDescription = fullDescription;
        this.value = value;
        this.category = category;
        this.itemKey = itemKey;
        this.comments = comments;
    }

//    /*******************************************
//     * Getters & Setters for all of Item's attributes
//     *
//     * @return an int or a string for getters, void for setters
//     */

    /**
     * Gets the item id
     * @return the item id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the item id
     * @param id an item id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the item donation time
     * @return the item donation time
     */
    public String getTime() {
        return time;
    }

    /**
     * Sets the item donation time
     * @param time a donation time
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * Gets the short item description
     * @return the short item description
     */
    public String getShortDescription() {
        return shortDescription;
    }

    /**
     * Sets the short item description
     * @param shortDescription a short item description
     */
    public void setShortDescription(String shortDescription) {
        this.shortDescription =
                shortDescription;
    }

    /**
     * Gets the full item description
     * @return the full item description
     */
    public String getFullDescription() {
        return fullDescription;
    }

    /**
     * Sets the full item description
     * @param fullDescription a detailed item description
     */
    public void setFullDescription(String fullDescription) {
        this.fullDescription =
                fullDescription;
    }

    /**
     * Gets the item dollar value
     * @return the item dollar value
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the item dollar value
     * @param value a dollar value
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Gets the item category
     * @return the item category
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets the item category
     * @param category an item category
     */
    public void setCategory(String category) {
        this.category = category;
    }

    public String getComments() { return comments; }

    public void setComments(String comments) { this.comments = comments; }

    /**
     * Gets the item key value
     * @return the item key value
     */
    public int getItemKey() {
        return itemKey;
    }

    /**
     * Sets the item key value
     * @param itemKey a key value
     */
    public void setItemKey(int itemKey) {
        this.itemKey = itemKey;
    }

    /**
     * Returns a string representation of this item's attributes.
     *
     * @return the String description of this item
     */
    @NonNull
    public String toString() {
        return "Time: " + time + " Short Description: " + shortDescription + " Full Description: "
                + fullDescription + " Value: " + value + " Category: " + category;
    }


}
