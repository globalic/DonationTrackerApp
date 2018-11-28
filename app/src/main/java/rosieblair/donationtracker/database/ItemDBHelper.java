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

/**
 * A database class to manage items
 */
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
    private static final String COMMENTS_COL = "comments";
    private static final String LOC_COL = "locId";

    /**
     * Constructor to create a new ItemDBHelper object.
     * @param context the context of activity passed into helper
     */
    public ItemDBHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    /**
     * The method to create the SQLite database table for item.
     * @param db the database object to be used
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        final String CREATE_IT = "CREATE TABLE " + ITEM_TABLE + "(" + ID_COL
                + " INTEGER PRIMARY KEY AUTOINCREMENT," + TIME_COL + " TEXT,"
                + SHORT_COL + " TEXT," + FULL_COL + " TEXT," + VALUE_COL + " TEXT,"
                + CATEGORY_COL + " TEXT," + COMMENTS_COL + " TEXT," + LOC_COL + " INTEGER" + ")";
        db.execSQL(CREATE_IT);
    }

    /**
     * The method to update the SQLite database table for item.
     * @param db the database object to be used
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        final String DROP_IT = "DROP TABLE IF EXISTS " + ITEM_TABLE;
        db.execSQL(DROP_IT);
        onCreate(db);
    }

    /**
     * Adds the item to database.
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
        values.put(COMMENTS_COL, item.getComments());
        values.put(LOC_COL, item.getItemKey());
        db.insert(ITEM_TABLE, null, values);
        db.close();
    }

    /**
     * Removes the item from database.
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
     * Checks if an item exists with matching short description to input.
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
     * Returns a list of all of the items currently in database.
     * @return list of all items in ascending order of id.
     */
    public List<Item> itemList() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Item> list = new ArrayList<>();

        String[] cols = {ID_COL, TIME_COL, SHORT_COL, FULL_COL, VALUE_COL, CATEGORY_COL, COMMENTS_COL, LOC_COL};
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
                item.setComments(cursor.getString(cursor.getColumnIndex(COMMENTS_COL)));
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
     * For any item, the key of the item will match the key of the location it is held at.
     * So all items that have matching key values will belong to the location having that key.
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
            Log.d("MYAPP", "Warning - No items found for location w/ key: " + locKey);
        }
        return items;
    }

    /**
     * Retrieves the item with given id from the database, given it exists.
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

    /*
      Gets list of any/all items with specified category value.
      @param category the item category
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
     * Find all items that have matching value as input for category.
     * @param category the category of item to check for
     * @param locKey location key from location spinner on search screen
     * @return list of items with selected category
     */
    public List<Item> findItemsByCategory(String category, int locKey) {
        List<Item> itemsInCat = new ArrayList<>();
        List<Item> items = itemList();
        if (locKey == -1) {
            for (Item d : items) {
                if (d.getCategory().equals(category)) {
                    itemsInCat.add(d);
                }
            } if (itemsInCat.isEmpty()) {
                Log.d("MYAPP", "Warning - Failed to find items for " + category
                        + " at all locations");
            } else {
                Log.d("MYAPP", "Showing items for " + category
                        + " at all locations");
            }
        } else {
            List<Item> itemsAtLoc = getLocationInventory(locKey);
            for (Item d: itemsAtLoc) {
                if (d.getCategory().equals(category)) {
                    itemsInCat.add(d);
                }
            }
            if (itemsAtLoc.isEmpty()) {
                Log.d("MYAPP", "Warning - Failed to find items for: " + category);
            } else {
                Log.d("MYAPP", "Showing this location's items for: " + category);
            }
        }
        return itemsInCat;
    }

    /*
      Gets list of any/all items with specified short description value.
      @param shortD the item short description
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
     * Fetches all items that have matching short descriptions as input.
     * @param name the name to check for
     * @param locKey location key from location spinner on search screen
     * @return the list of any/all items found
     */
    public List<Item> findItemsByName(String name, int locKey) {
        List<Item> itemsName = new ArrayList<>();
        List<Item> items = itemList();
        if (locKey == -1) {
            for (Item d : items) {
                if (d.getShortDescription().contains(name) || d.getFullDescription().contains(name)) {
                    itemsName.add(d);
                }
            } if (itemsName.isEmpty()) {
                Log.d("MYAPP", "Warning - Failed to find items containing " + name
                        + " at all locations");
            } else {
                Log.d("MYAPP", "Showing items containing " + name
                        + " at all locations");
            }
        } else {
            List<Item> itemsAtLoc = getLocationInventory(locKey);
            for (Item d: itemsAtLoc) {
                if (d.getShortDescription().contains(name) || d.getFullDescription().contains(name)) {
                    itemsName.add(d);
                }
            }
            if (itemsAtLoc.isEmpty()) {
                Log.d("MYAPP", "Warning - Failed to find items containing: " + name);
            } else {
                Log.d("MYAPP", "Showing this location's items containing: " + name);
            }
        }
        return itemsName;
    }

}
