package rosieblair.donationtracker.model;

public class Item {

    private int id;
    private String time;
    private String location;
    private String shortDescription;
    private String fullDescription;
    private String value;
    private String category;
    private int itemKey;

    public static int keyCounter;
    private int key;

    public Item() {
        this("Time", "Location", "Short Description", "fullDescription", "0", "category",1);
    }

    public Item(String time, String location, String shortDescription, String fullDescription, String value, String category, int keyCounter) {
        this.time = time;
        this.location = location; //replace usage with item key value
        this.shortDescription = shortDescription;
        this.fullDescription = fullDescription;
        this.value = value;
        this.category = category;
        this.key = keyCounter;
    }

    public String getTime() { return time; }
    public void setTime(String time) { this.time = time; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getShortDescription() { return shortDescription; }
    public void setShortDescription(String shortDescription) {this.shortDescription = shortDescription; }

    public String getFullDescription() { return fullDescription; }
    public void setFullDescription(String fullDescription) { this.fullDescription = fullDescription; }

    public String getValue() { return value; }
    public void setValue(String value) { this.value = value; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public int getKey() {
        return key;
    }
    public void setKey(int key) {
        this.key = key;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    //add equals method

    public int getItemKey() { return itemKey; }
    public void setItemKey(int itemKey) { this.itemKey = itemKey; }

    public String toString() {
        return "Time: " + time + " Location: " + location + " Short Description: " + shortDescription
                + " Full Description: " + fullDescription + " Value: " + value + " Category: " + category;
    }


}
