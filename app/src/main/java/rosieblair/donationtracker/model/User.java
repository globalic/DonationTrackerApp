package rosieblair.donationtracker.model;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * Model class for a User
 */
public class User implements Serializable {

    private int id;
    private int empId;
    private String username;
    private String password;
    private String email;
    private String type;
    private boolean locked;

    /**
     * A list of all possible types a User may be defined as.
     */
    public static final List<String> types = Arrays.asList("USER", "EMPLOYEE", "MANAGER", "ADMIN");

//    /**
//     * Getters & setters for all of User's attributes
//     * @return String for getters, void for setters
//     */

    /**
     * Gets the username
     * @return the username
     */
    public String getUsername() { return username; }

    /**
     * Sets the username
     * @param _username a username
     */
    public void setUsername(String _username) { username = _username; }

    /**
     * Gets the password
     * @return the password
     */
    public String getPassword() { return password; }

    /**
     * Sets the password
     * @param _password a password
     */
    public void setPassword(String _password) { password = _password; }

    /**
     * Gets the email
     * @return the email
     */
    public String getEmail() { return email; }

    /**
     * Sets the email
     * @param _email an email
     */
    public void setEmail(String _email) { email = _email; }


    /**
     * Gets the user type
     * @return the user type
     */
    public String getType() { return type; }

    /**
     * Sets the user type
     * @param _type a user type
     */
    public void setType(String _type) { type = (types.contains(_type) ? _type : types.get(0)); }


    /**
     * Gets the account state
     * @return true if locked account
     */
    public boolean getLocked() { return locked; }

    /**
     * Sets the account state
     * @param _locked true if account is locked
     */
    public void setLock(boolean _locked) { locked = _locked; }

    /**
     * Gets the user id
     * @return the user id
     */
    public int getId() { return id; }

    /**
     * Sets the user id
     * @param id a user id
     */
    public void setId(int id) { this.id = id; }

    /**
     * Gets the employee id
     * @return the employee id
     */
    public int getEmpId() { return empId; }

    /**
     * Sets the employee id
     * @param key an employee id
     */
    public void setEmpId(int key) { this.empId = key; }


    /**
     * @return String representation of this user's data.
     */
    @Override
    @NonNull
    public String toString() {
        String state = (locked) ? "LOCKED" : "UNLOCKED";
        return "User: " + username + ", Email: " + email
                + ", Type: " + type + ", Account state: " + state;
    }

    /**
     * @return the int representation of this user's account
     */
    @Override
    public int hashCode() {
        int result = 17;
        result = ((31 * result) + username.hashCode());
        result = ((31 * result) + password.hashCode());
        result = ((31 * result) + email.hashCode());
        result = ((31 * result) + type.hashCode());
        return result;
    }

    /**
     * Method to test if a user is equal to some other object.
     *
     * @param other the object to test equality with
     * @return true if objects are equal, false if not equal
     */
    @Override
    public boolean equals(Object other) {
        if (null == other) { return false; }
        if (this == other) { return true; }
        if (!(other instanceof User)) { return false; }
        User that = (User) other;
        return this.username.equals(that.getUsername())
                && this.password.equals(that.getPassword())
                && this.email.equals(that.getEmail())
                && this.type.equals(that.getType());
    }

    /**
     * Method to link a string representation to the listed legal types.
     * @param str passed in type string from spinner
     * @return String of the user's type
     */
    public static String findTypeByString(String str) {
        for (String t : types) {
            if (str.equalsIgnoreCase(t)) {
                return t;
            }
        }
        return null;
    }

}