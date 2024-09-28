/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package troyrenaudgr12pat;

/**
 * This class represents a User object with ID, username, password, and role.
 */
public class User {
    private int userID;
    private String username;
    private String password;
    private String role;

    /**
     * Constructor for User class.
     * 
     * @param userID the unique ID of the user.
     * @param username the username of the user.
     * @param password the password of the user.
     * @param role the role of the user.
     */
    public User(int userID, String username, String password, String role) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    /**
     * Constructor for User class without userID.
     * 
     * @param username the username of the user.
     * @param password the password of the user.
     * @param role the role of the user.
     */
    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    /**
     * Gets the role of the user.
     * 
     * @return the role as a String.
     */
    public String getRole() {
        return role;
    }

    /**
     * Gets the ID of the user.
     * 
     * @return the user ID as an integer.
     */
    public int getUserID() {
        return userID;
    }

    /**
     * Gets the username of the user.
     * 
     * @return the username as a String.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets the password of the user.
     * 
     * @return the password as a String.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Provides a string representation of the User object.
     * 
     * @return a string containing user details
     */
    @Override
    public String toString() {
        return "User{" + "userID=" + userID + ", username=" + username + ", password=" + password + ", role=" + role + '}';
    }
}
