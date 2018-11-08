package rosieblair.donationtracker.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import rosieblair.donationtracker.model.Item;


public class ItemDBHelper extends SQLiteOpenHelper {


    private static final int VERSION = 1;
    private static final String DB_NAME = "Item.db";
    private static final String ITEM_TABLE = "Item";

    private static final String ID_COL = "id";
    private static final String TIME_COL = "time";
    private static final String SHORT_COL = "short";
    private static final String FULL_COL = "full";
    private static final String VALUE_COL = "value";
    private static final String CATEGORY_COL = "category";
    private static final String LOC_COL = "locId";

    private final String CREATE_IT = "CREATE TABLE " + ITEM_TABLE + "(" + ID_COL
            + " INTEGER PRIMARY KEY AUTOINCREMENT," + TIME_COL + " TEXT,"
            + SHORT_COL + " TEXT," + FULL_COL + " TEXT," + VALUE_COL + " TEXT,"
            + CATEGORY_COL + " TEXT," + LOC_COL + " INTEGER" + ")";

    private final String DROP_IT = "DROP TABLE IF EXISTS " + ITEM_TABLE;

    public ItemDBHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_IT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_IT);
        onCreate(db);
    }

    /**
     * Adds item to database.
     *
     * @param item the item to add
     */
    public void addItem(Item item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TIME_COL, item.getTime());
        values.put(SHORT_COL, item.getShortDescription());
        values.put(FULL_COL, item.getFullDescription());
        values.put(VALUE_COL, item.getValue());
        values.put(CATEGORY_COL, item.getCategory());
        values.put(LOC_COL, item.getItemKey());
        db.insert(ITEM_TABLE, null, values);
        db.close();
    }

    /**
     * Removes the item from database.
     *
     * @param item the item to remove
     */
    public void removeItem(Item item) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(ITEM_TABLE, ID_COL + " = ?", new String[]{
                String.valueOf(item.getId())
        });
        db.close();
    }

    /**
     * Updates the item's information in the database.
     *
     * @param item the item to update
     */
    public void updateItem(Item item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TIME_COL, item.getTime());
        values.put(SHORT_COL, item.getShortDescription());
        values.put(FULL_COL, item.getFullDescription());
        values.put(VALUE_COL, item.getValue());
        values.put(CATEGORY_COL, item.getCategory());
        values.put(LOC_COL, item.getItemKey());
        db.update(ITEM_TABLE, values, ID_COL + " = ?", new String[]{
                String.valueOf(item.getId())
        });
        db.close();
    }

    /**
     * Checks if an item exists with inputted short description.
     *
     * @param shortD the short description of item to look for
     * @return true if exists, false otherwise
     */
    public boolean checkItem(String shortD) {
        if (shortD == null) {
            return false;
        }
        SQLiteDatabase db = this.getReadableDatabase();
        String[] cols = {ID_COL};
        String col = SHORT_COL + " = ?";
        String[] arg = {shortD};

        Cursor cursor = db.query(ITEM_TABLE, cols, col, arg, null,
                null, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();

        return (count > 0);
    }

    /**
     * Returns a  list of all of the items currently in database.
     *
     * @return list of all items in ascending order of id.
     */
    public List<Item> itemList() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Item> list = new ArrayList<>();

        String[] cols = {ID_COL, TIME_COL, SHORT_COL, FULL_COL, VALUE_COL, CATEGORY_COL, LOC_COL};
        String orderBy = ID_COL + " ASC";

        Cursor cursor = db.query(ITEM_TABLE, cols, null,
                null, null, null, orderBy);
        if (cursor.moveToFirst()) {
            do {
                Item item = new Item();
                item.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(ID_COL))));
                item.setTime(cursor.getString(cursor.getColumnIndex(TIME_COL)));
                item.setShortDescription(cursor.getString(cursor.getColumnIndex(SHORT_COL)));
                item.setFullDescription(cursor.getString(cursor.getColumnIndex(FULL_COL)));
                item.setValue(cursor.getString(cursor.getColumnIndex(VALUE_COL)));
                item.setCategory(cursor.getString(cursor.getColumnIndex(CATEGORY_COL)));
                item.setItemKey(Integer.parseInt(cursor.getString(cursor.getColumnIndex(LOC_COL))));
                list.add(item);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return list;
    }

    /**
     * Gets the inventory of the specified location (with key = locKey).
     * <p>
     * For any item, the key of the item will match the key of the location it is held at.
     * So all items that have matching key values will belong to the location having that key.
     *
     * @param locKey location's key
     * @return list of all items at the location (or empty list if none found)
     */
    public List<Item> getLocationInventory(int locKey) {
        List<Item> items = new ArrayList<>();
        List<Item> item_list = itemList();
        for (Item i : item_list) {
            if (i.getItemKey() == locKey) {
                items.add(i);
            }
        }
        if (items.isEmpty()) {
            System.out.print("here");
            return null;
            //Log.d("MYAPP", "Warning - No items found for location w/ key: " + locKey);
        } else {
            System.out.print("only here");
            return items;
        }
    }

    /**
     * Retrieves the item with given id from the database, given it exists.
     *
     * @param i the item we want with given id
     * @return the item with id = i if exists, null if no item found with id
     */
    public Item getItem(int i) {
        List<Item> items = itemList();
        for (Item item : items) {
            if (item.getId() == i) {
                return item;
            }
        }
        Log.d("MYAPP", "Warning - Failed to find item with id: " + i);
        return null;
    }

    /**
     * Gets list of any/all items with specified category value.
     *
     * @param category the item category
     * @return list of all items with category value, or empty list if none
     */
//    public List<Item> getItemsWithCategory(String category) {
//        if (!Item.itemCategories.contains(category)) {
//            return new ArrayList<>();
//        }
//        List<Item> categoryItems = new ArrayList<>();
//        List<Item> items = itemList();
//        for (Item i : items) {
//            if (i.getCategory().equals(category)) {
//                categoryItems.add(i);
//            }
//        }
//        if (categoryItems.isEmpty()) {
//            Log.d("MYAPP", "Warning - Failed to find items for: " + category);
//        }
//        return categoryItems;
//    }

    /**
     *
     * @param category
     * @param locKey location key from location spinner on search screen
     * @return
     */
    public List<Item> findItemsByCategory(String category, int locKey) {
        List<Item> itemsInCat = new ArrayList<>();
        List<Item> items = itemList();
        if (locKey == -1) {
            for (Item d : items) {
                if (d.getCategory().equals(category)) itemsInCat.add(d);
            }
            Log.d("MYAPP", "Warning - Failed to find items for: " + category
                    + " at all locations");
        } else {
            List<Item> itemsAtLoc = getLocationInventory(locKey);
            for (Item d: itemsAtLoc) {
                if (d.getCategory().equals(category)) itemsInCat.add(d);
            }
            Log.d("MYAPP", "Warning - Failed to find items for: " + category);
        }
        return itemsInCat;
    }

    /**
     * Gets list of any/all items with specified short description value.
     *
     * @param shortD the item short description
     * @return list of all items with short description, or empty list if none
     */
//    public List<Item> getItemsWithName(String shortD) {
//        if (shortD == null) {
//            return new ArrayList<>();
//        }
//        List<Item> nameItems = new ArrayList<>();
//        List<Item> items = itemList();
//        for (Item i : items) {
//            if (i.getShortDescription().equals(shortD)) {
//                nameItems.add(i);
//            }
//        }
//        if (nameItems.isEmpty()) {
//            Log.d("MYAPP", "Warning - Failed to find items for: " + shortD);
//        }
//        return nameItems;
//    }

    /**
     *
     * @param name
     * @param locKey location key from location spinner on search screen
     * @return
     */
    public List<Item> findItemsByName(String name, int locKey) {
        List<Item> itemsName = new ArrayList<>();
        List<Item> items = itemList();
        if (locKey == -1) {
            for (Item d : items) {
                if (d.getShortDescription().contains(name)) itemsName.add(d);
            }
            Log.d("MYAPP", "Warning - Failed to find items for: " + name
                    + " at all locations");
        } else {
            List<Item> itemsAtLoc = getLocationInventory(locKey);
            for (Item d: itemsAtLoc) {
                if (d.getShortDescription().contains(name)) itemsName.add(d);
            }
            Log.d("MYAPP", "Warning - Failed to find items for: " + name);
        }
        return itemsName;
    }

    public List<Item> findItemsByName(String name) {
        List<Item> itemsName = new ArrayList<>();
        List<Item> items = itemList();
            for (Item d : items) {
                if (d.getShortDescription().contains(name)) itemsName.add(d);
            }
            Log.d("MYAPP", "Warning - Failed to find items for: " + name
                    + " at all locations");
        return itemsName;
    }

}



//    private List<Item> items;
//    //    public static final ItemDatabase INSTANCE = new ItemDatabase();
//    public List<Item> getItems() {
//        return items;
//    }
//    public ItemDBHelper() {
//        items = new ArrayList<>();
//    }
//
//    //    public Item findItemsAtLocation(String location) {
////        for (Item d : items) {
////            if (d.getLocation().equals(location)) return d;
////        }
////        Log.d("MYAPP", "Warning - Failed to find items for: " + location);
////        return null;
////    }
//    public ArrayList<Item> findItemsAtLocation(String location) {
//        ArrayList<Item> itemsAtLoc = new ArrayList<>();
//        for (Item d : items) {
//            if (d.getLocation().equals(location)) itemsAtLoc.add(d);
//        }
//        Log.d("MYAPP", "Warning - Failed to find items for: " + location);
//        return itemsAtLoc;
//    }
//    public Item getItem(int index) {
//        return items.get(index);
//    }
//    public Item findItemsByKey(int key) {
//        for (Item d : items) {
//            if (d.getKey() == key) return d;
//        }
//        Log.d("MYAPP", "Warning - Failed to find items for: " + key);
//        return null;
//    }
//
//    public ArrayList<Item> findItemsByCategory(String category) {
//        ArrayList<Item> itemsInCat = new ArrayList<>();
//        for (Item d : items) {
//            if (d.getCategory().equals(category)) itemsInCat.add(d);
//        }
//        Log.d("MYAPP", "Warning - Failed to find items for: " + category);
//        return itemsInCat;
//    }
//
//    public ArrayList<Item> findItemsByName(String name) {
//        ArrayList<Item> itemsName = new ArrayList<>();
//        for (Item d : items) {
//            if (d.getShortDescription().equals(name)) itemsName.add(d);
//        }
//        Log.d("MYAPP", "Warning - Failed to find items for: " + name);
//        return itemsName;
//    }
//
//    public List<Item> itemList() {
//        return new ArrayList<>();
//    }

