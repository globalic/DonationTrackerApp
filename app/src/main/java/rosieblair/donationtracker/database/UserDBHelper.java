package rosieblair.donationtracker.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import rosieblair.donationtracker.model.User;


public class UserDBHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DB_NAME = "User.db";
    private static final String USER_TABLE = "User";

    private static final String ID_COL = "id";
    private static final String USERNAME_COL = "username";
    private static final String PASSWORD_COL = "password";
    private static final String EMAIL_COL = "email";
    private static final String LOCKED_COL = "locked";
    private static final String TYPE_COL = "type";
    private static final String LOC_COL = "locId";

    private String CREATE_UT = "CREATE TABLE " + USER_TABLE + "("
            + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT," + USERNAME_COL
            + " TEXT," + PASSWORD_COL + " TEXT," + EMAIL_COL + " TEXT,"
            + LOCKED_COL + " TEXT," + TYPE_COL + " TEXT," + LOC_COL + " INTEGER" + ")";

    private String DROP_UT = "DROP TABLE IF EXISTS " + USER_TABLE;

    public UserDBHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_UT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(DROP_UT);
        onCreate(db);
    }

    /**
     * Add the inputted user's info to the user database.
     *
     * @param user to add
     */
    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USERNAME_COL, user.getUsername());
        values.put(EMAIL_COL, user.getEmail());
        values.put(PASSWORD_COL, user.getPassword());
        String state = (user.getLocked() ? "true" : "false");
        values.put(LOCKED_COL, state);
        values.put(TYPE_COL, user.getType());
        values.put(LOC_COL, user.getEmpId());
        //add user info, put in table as new row
        db.insert(USER_TABLE, null, values);
        db.close();
    }

    /**
     * Removes the user from the user database.
     *
     * @param user to remove
     */
    public void removeUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(USER_TABLE, ID_COL + " = ?", new String[] {
                String.valueOf(user.getId())
        });
        db.close();
    }

    /**
     * Updates this user's info in the database.
     * @param user to update
     */
    public void updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USERNAME_COL, user.getUsername());
        values.put(EMAIL_COL, user.getEmail());
        values.put(PASSWORD_COL, user.getPassword());
        String state = (user.getLocked() ? "true" : "false");
        values.put(LOCKED_COL, state);
        values.put(TYPE_COL, user.getType());
        values.put(LOC_COL, user.getEmpId());
        db.update(USER_TABLE, values, ID_COL + " = ?", new String[] {
                String.valueOf(user.getId())
        });
        db.close();

    }

    /**
     * Checks if the username exists in database.
     *
     * @param username the string username to check for
     * @return true if username exists, false if no such username exists
     */
    public boolean checkUsername(String username) {
        if (username == null) {
            return false;
        }
        SQLiteDatabase db = this.getReadableDatabase();
        String[] cols = { ID_COL };
        String col = USERNAME_COL + " = ?";
        String[] arg = { username };

        Cursor cursor = db.query(USER_TABLE, cols, col, arg, null,
                null, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();

        return (count > 0);
    }

    /**
     * Checks if the email exists in the database.
     *
     * @param email the string email to check for
     * @return true if email exists, false if no such email exists
     */
    public boolean checkEmail(String email) {
        if (email == null) {
            return false;
        }
        SQLiteDatabase db = this.getReadableDatabase();
        String[] cols = { ID_COL };
        String col = EMAIL_COL + " = ?";
        String[] arg = { email };

        Cursor cursor = db.query(USER_TABLE, cols, col, arg, null,
                null, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();

        return (count > 0);
    }

    /**
     * Checks if username and password exist for same user in database.
     *
     * @param username to check for
     * @param password to match with username
     * @return true if the combo exists and matches, false otherwise
     */
    public boolean checkUserPass(String username, String password) {
        if (username == null || password == null) {
            return false;
        }
        SQLiteDatabase db = this.getReadableDatabase();
        String[] cols = { ID_COL };
        String col = USERNAME_COL + " = ?" + " AND " + PASSWORD_COL + " = ?";
        String[] arg = { username, password };

        Cursor cursor = db.query(USER_TABLE, cols, col, arg, null,
                null, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();

        return (count > 0);
    }

    /**
     * Checks if user with inputted username is a location employee
     *
     * @param username to check for
     * @return true if this user's type = employee, false otherwise
     */
    public boolean checkIfEmployee(String username) {
        if (username == null) {
            return false;
        }
        String empStr = "EMPLOYEE";
        SQLiteDatabase db = this.getReadableDatabase();
        String[] cols = { ID_COL };
        String col = USERNAME_COL + " = ?" + " AND " + TYPE_COL + " = ?";
        String[] arg = { username, empStr };

        Cursor cursor = db.query(USER_TABLE, cols, col, arg, null,
                null, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();

        return (count > 0);
    }



    /**
     * Returns an alphabetically sorted list of all User objects stored in the database.
     *
     * @return list of all users
     */
    public List<User> userList() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<User> list = new ArrayList<>();

        String[] cols = { ID_COL, USERNAME_COL, EMAIL_COL, PASSWORD_COL, LOCKED_COL, TYPE_COL,
                LOC_COL };
        String orderBy = USERNAME_COL + " ASC";

        Cursor cursor = db.query(USER_TABLE, cols, null,
                null, null, null, orderBy);
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(ID_COL))));
                user.setUsername(cursor.getString(cursor.getColumnIndex(USERNAME_COL)));
                user.setEmail(cursor.getString(cursor.getColumnIndex(EMAIL_COL)));
                user.setPassword(cursor.getString(cursor.getColumnIndex(PASSWORD_COL)));
                String lock = (cursor.getString(cursor.getColumnIndex(LOCKED_COL)));
                user.setLock(lock.equals("true"));
                user.setType(cursor.getString(cursor.getColumnIndex(TYPE_COL)));
                user.setEmpId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(LOC_COL))));
                list.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return list;
    }

    public User findUserByUsername(String username) {
        List<User> list = userList();
        for (User u : list) {
            if (u.getUsername().equals(username)) {
                return u;
            }
        }
        return null;
    }

//    /**
//     * Gets either a list of all location employees when indicator argument is not
//     * equal to 1, or the list of location employees working at the location with key
//     * equal to lockKey (when indicator == 1).
//     *
//     * @param locKey the key of a location, if returning its employees only
//     * @param indicator determines which list to return
//     * @return a list of either all current location employees in database, or
//     * if the indicator == 1, then the list will contain only the employees of the
//     * location with key == locKey
//     */
//    public List<User> getEmpList(int locKey, int indicator) {
//        List<User> list = userList();
//        List<User> emp_list = new ArrayList<>();
//        List<User> all_list = new ArrayList<>();
//        for (User u : list) {
//            if (u.getType().equals("EMPLOYEE")) {
//                if (indicator == 1 && u.getEmpId() == locKey) {
//                    emp_list.add(u);
//                } else {
//                    all_list.add(u);
//                }
//            }
//        }
//        if (indicator == 1) {
//            return emp_list;
//        } else {
//            return all_list;
//        }
//    }



}



//    public String getUserType(String username) {
//        if (username == null) {
//            return null;
//        } else if (!checkUsername(username)) {
//            return null;
//        }
//        List<User> list = userList();
//        for (User u : list) {
//            if (u.getUsername().equals(username)) {
//                return u.getType();
//            }
//        }
//        return null;
//    }

  //  public boolean checkEmployee(String username, Location loc) {
//        if (username == null || loc == null) {
//            return false;
//        } else if (!checkUsername(username)) {
//            return false;
//        }
//        List<User> uList = userList();
//        for (User u : uList) {
//            if (u.getUsername().equals(username)) {
//                if (u.getType().equals("EMPLOYEE")) {
//                    return (loc.getKey() == u.getEmpId());
//                }
//            }
//        }
//        return false;
//    }
