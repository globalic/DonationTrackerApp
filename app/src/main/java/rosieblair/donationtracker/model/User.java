package rosieblair.donationtracker.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class User implements Serializable {

    private int id;
    private int empId;
    private String username;
    private String password;
    private String email;
    private String type;
    private boolean locked;

    public static final List<String> types = Arrays.asList("USER", "EMPLOYEE", "MANAGER", "ADMIN");

//    /**
//     * Create a new user w/o email, set type to default "User".
//     *
//     * @param username
//     * @param password
//     */
//    public User(String username, String password) {
//        this(username, password, null, types.get(0));
//    }
//
//    /**
//     * Create new user, set type to default "User".
//     *
//     * @param username the username of this user's account
//     * @param password the password of this user's account
//     * @param email the user's email to be used for contact
//     */
//    public User(String username, String password, String email) {
//        this(username, password, email, types.get(0));
//    }
//
//    /**
//     * Constructor that initializes data for new User object.
//     * Newly registered user accounts are 'unlocked' by default.
//     *
//     * @param username the username of this user's account
//     * @param password the password of this user's account
//     * @param email    the user's email to be used for contact
//     */
//    public User(String username, String password, String email, String type) {
//        this.empId = -1;
//        this.username = username;
//        this.password = password;
//        this.email = email;
//        this.type = types.contains(type) ? type : types.get(0);
//        this.locked = false;
//    }

    /**
     * Getters/setters for this User's attributes
     */
    public String getUsername() { return username; }
    public void setUsername(String _username) { username = _username; }

    public String getPassword() { return password; }
    public void setPassword(String _password) { password = _password; }

    public String getEmail() { return email; }
    public void setEmail(String _email) { email = _email; }

    public String getType() { return type; }
    public void setType(String _type) { type = (types.contains(_type) ? _type : types.get(0)); }

    public boolean getLocked() { return locked; }
    public void setLock(boolean _locked) { locked = _locked; }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getEmpId() { return empId; }
    public void setEmpId(int key) { this.empId = key; }


    /**
     *
     * @return String representation of this user's data.
     */
    @Override
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
     * Method to test if this user is equal to some other object.
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

    public static String findTypeByString(String str) {
        for (String t : types) {
            if (str.equalsIgnoreCase(t)) {
                return t;
            }
        }
        return null;
    }

}