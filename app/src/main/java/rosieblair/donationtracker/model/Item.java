package rosieblair.donationtracker.model;

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
    private int itemKey;

    /**
     * A list of all possible categories defined for an item.
     */
    public static final List<String> itemCategories = Arrays.asList("Clothing", "Hat", "Kitchen",
            "Electronics", "Household", "Other");

    /**
     * No-Arg Constructor to create an Item object (for use with activities only).
     */
    public Item() {
        this(null, null, null, null,
                itemCategories.get(0), 1);
    }

    /**
     * Constructor to make a new Item Object
     * @param time the item's donation time
     * @param shortDescription a short description of the item
     * @param fullDescription a full (longer) description of the item
     * @param value the item's value
     * @param category the item's category
     * @param itemKey the key that links the item to a location
     */
    public Item(String time, String shortDescription, String fullDescription, String value,
                String category, int itemKey) {
        this.time = time;
        this.shortDescription = shortDescription;
        this.fullDescription = fullDescription;
        this.value = value;
        this.category = category;
        this.itemKey = itemKey;
    }

    /*******************************************
     * Getters & Setters for all of Item's attributes
     * */
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTime() { return time; }
    public void setTime(String time) { this.time = time; }

    public String getShortDescription() { return shortDescription; }
    public void setShortDescription(String shortDescription) {
        this.shortDescription =
            shortDescription; }

    public String getFullDescription() { return fullDescription; }
    public void setFullDescription(String fullDescription) { this.fullDescription =
            fullDescription; }

    public String getValue() { return value; }
    public void setValue(String value) { this.value = value; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public int getItemKey() { return itemKey; }
    public void setItemKey(int itemKey) { this.itemKey = itemKey; }

    /**
     * Returns a string representation of this item's attributes.
     * @return the String description of this item
     */
    public String toString() {
        return "Time: " + time + " Short Description: " + shortDescription + " Full Description: "
                + fullDescription + " Value: " + value + " Category: " + category;
    }


}
