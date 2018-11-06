package rosieblair.donationtracker.database;

import java.util.HashMap;

import rosieblair.donationtracker.model.User;

public class UserDBHelper {
    //stores username as key with associated User object as value
    private HashMap<String, User> users;

    //constructor to initialize database with empty hashmap
    public UserDBHelper() {
        users = new HashMap<>();
    }

    /**
     * Adds a new user to the database.
     *
     * @param user User to add to user database
     * @return true if user was added, false if user was not added
     * @throws IllegalArgumentException if user is null
     */
    public boolean addUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("Cannot add a null User.");
        }
        if (users.containsKey(user.getUsername())) {
            return false;
        } else {
            users.put(user.getUsername(), user);
            return true;
        }
    }

    /**
     * Removes an existing user from database.
     *
     * @param user User to remove from user database
     * @return the removed User value (or null)
     * @throws IllegalArgumentException if user is null or user doesn't exist
     */
    public User removeUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("Cannot use null argument.");
        } else if (!users.containsKey(user.getUsername())) {
            throw new IllegalArgumentException("Cannot remove nonexistant user.");
        }
        return users.remove(user.getUsername());
    }

    /**
     * Gets a user from the database.
     *
     * @param username the username of the user to fetch
     * @return the User object from our database
     * @throws IllegalArgumentException if username is null/user doesn't exist
     */
    public User getUser(String username) {
        if (username == null) {
            throw new IllegalArgumentException("Cannot use null argument.");
        } else if (users.containsKey(username)) {
            throw new IllegalArgumentException("User not found in system.");
        } else {
            return users.get(username);
        }
    }

    /**
     * Checks if a user w/ inputted username exists in our database.
     *
     * @param username the username to search the database for
     * @return true if user exists in database, false if no user exists
     */
    public boolean containsUser(String username) {
        return users.containsKey(username);
    }

    /**
     * Checks if the password input matches the password of the User input
     * @param password the password that may/may not match
     * @param user the user with password to compare input with
     * @return true if values of passwords match, false if they don't match
     * @throws IllegalArgumentException if null inputs/user doesn't exist
     */
    public boolean matchPassword(String password, User user) {
        if (user == null || password == null) {
            throw new IllegalArgumentException("Cannot have null arguments.");
        }
        if (!users.containsKey(user.getUsername())) {
            throw new IllegalArgumentException("User does not exist.");
        } else {
            User u = users.get(user.getUsername());
            return u.getPassword().equals(password);
        }
    }

    /**
     * Updates the user entry in database. If their username doesn't change, we
     * just replace its value with updatedUser. If username changes, we remove
     * the entry entirely because a new key is needed. Then, we print the
     * changes made to the user into the System.
     *
     * @param username the username of the user to be updated
     * @param updatedUser contains new data for user to be updated with
     * @throws IllegalArgumentException if null inputs/user doesn't exist
     */
    public void updateUser(String username, User updatedUser) {
        if (username == null || updatedUser == null) {
            throw new IllegalArgumentException("Cannot have null arguments.");
        } else if (!users.containsKey(username)) {
            throw new IllegalArgumentException("User does not exist.");
        }
        User oldUser;
        //if different username than before, remove key&value.
        if (!username.equals(updatedUser.getUsername())) {
            oldUser = users.remove(username);
            users.put(updatedUser.getUsername(), updatedUser);
        } else {
            oldUser = users.put(username, updatedUser);
        }
        System.out.println("Updated information:\n" + oldUser.toString() + "\n"
                + updatedUser.toString());

    }
}
