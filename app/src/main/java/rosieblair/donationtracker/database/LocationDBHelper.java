package rosieblair.donationtracker.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import rosieblair.donationtracker.R;
import rosieblair.donationtracker.activities.EmployeeAppScreen;
import rosieblair.donationtracker.activities.MainActivity;
import rosieblair.donationtracker.model.Location;

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

public class LocationDBHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DB_NAME = "Location.db";
    private static final String LOCN_TABLE = "Location";

    private static final String ID_COL = "id";
    private static final String KEY_COL = "locKey";
    private static final String NAME_COL = "name";
    private static final String LAT_COL = "latitude";
    private static final String LON_COL = "longitude";
    private static final String ADDR_COL = "address";
    private static final String CITY_COL = "city";
    private static final String STATE_COL = "state";
    private static final String ZIP_COL = "zip";
    private static final String TYPE_COL = "type";
    private static final String PHONE_COL = "phone";
    private static final String WEB_COL = "website";

    private String CREATE_LT = "CREATE TABLE " + LOCN_TABLE + "(" + ID_COL
            + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_COL + " INTEGER,"
            + NAME_COL + " TEXT," + LAT_COL + " TEXT," + LON_COL + " TEXT,"
            + ADDR_COL + " TEXT," + CITY_COL + " TEXT," + STATE_COL + " TEXT,"
            + ZIP_COL + " TEXT," + TYPE_COL + " TEXT," + PHONE_COL + " TEXT,"
            + WEB_COL + " TEXT" + ")";

    private String DROP_LT = "DROP TABLE IF EXISTS " + LOCN_TABLE;

    /**
     * Constructor to create a new LocationDBHelper object.
     *
     * @param context
     */
    public LocationDBHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    /**
     * The method to create the SQLite database table for location.
     *
     * @param db the database object to be used
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_LT);
    }

    /**
     * The method to update the SQLite database table for location.
     *
     * @param db the database object to be used
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_LT);
        onCreate(db);
    }

    /**
     * Method that adds a location object to the database.
     *
     * @param loc the location being added
     */
    public void addLocation(Location loc) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_COL, loc.getKey());
        values.put(NAME_COL, loc.getName());
        values.put(LAT_COL, loc.getLatitude());
        values.put(LON_COL, loc.getLongitude());
        values.put(ADDR_COL, loc.getStreetAddress());
        values.put(CITY_COL, loc.getCity());
        values.put(STATE_COL, loc.getState());
        values.put(ZIP_COL, loc.getZipCode());
        values.put(TYPE_COL, loc.getLocType());
        values.put(PHONE_COL, loc.getPhoneNumber());
        values.put(WEB_COL, loc.getWebsite());
        db.insert(LOCN_TABLE, null, values);
        db.close();
    }

    /**
     * Method to remove a location object from the database.
     *
     * @param loc the location being removed
     */
    public void removeLocation(Location loc) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(LOCN_TABLE, ID_COL + " = ?", new String[]{
                String.valueOf(loc.getId())
        });
        db.close();
    }

    /**
     * Method to update a location in the database.
     *
     * @param loc the location being updated
     */
    public void updateLocation(Location loc) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_COL, loc.getKey());
        values.put(NAME_COL, loc.getName());
        values.put(LAT_COL, loc.getLatitude());
        values.put(LON_COL, loc.getLongitude());
        values.put(ADDR_COL, loc.getStreetAddress());
        values.put(CITY_COL, loc.getCity());
        values.put(STATE_COL, loc.getState());
        values.put(ZIP_COL, loc.getZipCode());
        values.put(TYPE_COL, loc.getLocType());
        values.put(PHONE_COL, loc.getPhoneNumber());
        values.put(WEB_COL, loc.getWebsite());
        db.update(LOCN_TABLE, values, ID_COL + " = ?", new String[]{
                String.valueOf(loc.getId())
        });
        db.close();
    }

    /**
     * Checks if a location exists in database with the inputted name.
     *
     * @param name the name of location to look for
     * @return true if exists, false otherwise
     */
    public boolean checkLocation(String name) {
        if (name == null) {
            return false;
        }
        SQLiteDatabase db = this.getReadableDatabase();
        String[] cols = {ID_COL};
        String col = NAME_COL + " = ?";
        String[] arg = {name};

        Cursor cursor = db.query(LOCN_TABLE, cols, col, arg, null,
                null, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();

        return (count > 0);
    }

    /**
     * Returns a list of all locations that currently exist in the database.
     *
     * @return the list of locations
     */
    public List<Location> locationList() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Location> list = new ArrayList<>();

        String[] cols = {ID_COL, KEY_COL, NAME_COL, LAT_COL, LON_COL, ADDR_COL, CITY_COL, STATE_COL,
                ZIP_COL, TYPE_COL, PHONE_COL, WEB_COL};
        String orderBy = KEY_COL + " ASC";

        Cursor cursor = db.query(LOCN_TABLE, cols, null,
                null, null, null, orderBy);
        if (cursor.moveToFirst()) {
            do {
                Location loc = new Location();
                loc.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(ID_COL))));
                loc.setKey(Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_COL))));
                loc.setName(cursor.getString(cursor.getColumnIndex(NAME_COL)));
                loc.setLatitude(cursor.getString(cursor.getColumnIndex(LAT_COL)));
                loc.setLongitude(cursor.getString(cursor.getColumnIndex(LON_COL)));
                loc.setStreetAddress(cursor.getString(cursor.getColumnIndex(ADDR_COL)));
                loc.setCity(cursor.getString(cursor.getColumnIndex(CITY_COL)));
                loc.setState(cursor.getString(cursor.getColumnIndex(STATE_COL)));
                loc.setZipCode(cursor.getString(cursor.getColumnIndex(ZIP_COL)));
                loc.setLocType(cursor.getString(cursor.getColumnIndex(TYPE_COL)));
                loc.setPhoneNumber(cursor.getString(cursor.getColumnIndex(PHONE_COL)));
                loc.setWebsite(cursor.getString(cursor.getColumnIndex(WEB_COL)));
                list.add(loc);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return list;
    }

    /**
     * Gets the location with key value matching locKey.
     *
     * @param locKey the location key to check for
     * @return the location with matching key or null if none found.
     */
    public Location getLocationByKey(int locKey) {
        if (locKey <= 0) {
            return null;
        }
        List<Location> _list = locationList();
        for (Location l : _list) {
            if (l.getKey() == locKey) {
                return l;
            }
        }
        return null;
    }

    /**
     * Gets the location object with name matching the String passed in.
     *
     * @param name the name to look for
     * @return the location with name, or null if none found
     */
    public Location getLocationByName(String name) {
        if (name == null || !checkLocation(name)) {
            Log.d("LocationByName", "name of location is null");
            return null;
        }
        List<Location> _list = locationList();
        for (Location l : _list) {
            if (l.getName().equalsIgnoreCase(name)) {
                return l;
            }
        }
        Log.d("LocationByName", "no location matched in list");
        return null;
    }
}