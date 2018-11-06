package rosieblair.donationtracker.database;

import java.util.ArrayList;
import java.util.List;

import rosieblair.donationtracker.model.Location;

public class LocationDBHelper {

//    public static final LocationDBHelper INSTANCE = new LocationDBHelper();

    private List<Location> locations;

    public LocationDBHelper() {
        locations = new ArrayList<>();
    }


    public void addLocation(Location location) {
        locations.add(location);
    }

    public List<Location> getItems() {
        return locations;
    }

    //Below method may be needed in the future to search by location IDs
    public Location findLocationByKey(int key) {
        for (Location d : locations) {
            if (d.getKey() == key) return d;
        }
        Log.d("MYAPP", "Warning - Failed to find key: " + key);
        return null;
    }

    //method to search by location name
    public Location findLocationByName(String name) {
        for (Location d : locations) {
            if (d.getName().equals(name)) return d;
        }
        Log.d("MYAPP", "Warning - Failed to find name: " + name);
        return null;
    }
}
