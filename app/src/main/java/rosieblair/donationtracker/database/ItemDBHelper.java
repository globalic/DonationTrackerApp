package rosieblair.donationtracker.database;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import rosieblair.donationtracker.model.Item;

public class ItemDBHelper {

    private List<Item> items;
    //    public static final ItemDatabase INSTANCE = new ItemDatabase();
    public List<Item> getItems() {
        return items;
    }
    public ItemDBHelper() {
        items = new ArrayList<>();
    }

    //    public Item findItemsAtLocation(String location) {
//        for (Item d : items) {
//            if (d.getLocation().equals(location)) return d;
//        }
//        Log.d("MYAPP", "Warning - Failed to find items for: " + location);
//        return null;
//    }
    public ArrayList<Item> findItemsAtLocation(String location) {
        ArrayList<Item> itemsAtLoc = new ArrayList<>();
        for (Item d : items) {
            if (d.getLocation().equals(location)) itemsAtLoc.add(d);
        }
        Log.d("MYAPP", "Warning - Failed to find items for: " + location);
        return itemsAtLoc;
    }
    public Item getItem(int index) {
        return items.get(index);
    }
    public Item findItemsByKey(int key) {
        for (Item d : items) {
            if (d.getKey() == key) return d;
        }
        Log.d("MYAPP", "Warning - Failed to find items for: " + key);
        return null;
    }

    public ArrayList<Item> findItemsByCategory(String category) {
        ArrayList<Item> itemsInCat = new ArrayList<>();
        for (Item d : items) {
            if (d.getCategory().equals(category)) itemsInCat.add(d);
        }
        Log.d("MYAPP", "Warning - Failed to find items for: " + category);
        return itemsInCat;
    }

    public ArrayList<Item> findItemsByName(String name) {
        ArrayList<Item> itemsName = new ArrayList<>();
        for (Item d : items) {
            if (d.getShortDescription().equals(name)) itemsName.add(d);
        }
        Log.d("MYAPP", "Warning - Failed to find items for: " + name);
        return itemsName;
    }

    public List<Item> itemList() {
        return new ArrayList<>();
    }
}
