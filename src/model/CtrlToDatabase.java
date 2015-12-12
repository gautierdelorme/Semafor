/*
 * Semafor - MIT License
 *
 * A peer-to-peer chat system
 *
 * Copyright 2015 Gautier Delorme and Arthur Papailhau.
 *
 */
package model;

import java.net.InetAddress;

/**
 *
 * @author arthurpapailhau
 */
public interface CtrlToDatabase {

    /**
     * Return the current user
     * 
     * @return current user
     */
    public User getCurrentUser();

    /**
     * Set the current user
     * 
     * @param nickname nickname of the user
     */
    public void setCurrentUser(String nickname);

    /**
     * Remove the current user
     */
    public void removeCurrentUser();

    /**
     *  Create an empty userList
     */
    public void removeUserList();

    /**
     * Create a user if it doesn't exist, or update the nickname if it already exists
     * 
     * @param ip ip address of the user
     * @param nickname nickname of the user
     * @return the new user
     */
    public User addUser(InetAddress ip, String nickname);

    /**
     * Delete the user form the database, and return it
     * 
     * @param ip address of the user
     * @return the removed user
     */ 
    public User deleteUser(InetAddress ip);

    /**
     * Get a user from an ip address
     * 
     * @param ip ip address of the user
     * @return the user matching with the param ip address.
     */ 
    public User getUserWithIP(InetAddress ip);
}
