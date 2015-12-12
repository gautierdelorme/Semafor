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
     *
     * @return the current user
     */
    public User getCurrentUser();

    /**
     *
     * @param nickname
     *  Set the current user with param nickname
     */
    public void setCurrentUser(String nickname);

    /**
     * remove the current user
     */
    public void removeCurrentUser();

    /**
     *
     */
    public void removeUserList();

    /**
     *
     * @param ip
     * @param nickname
     * @return 
     *  Create a user if it doesn't exist, or update the nickname if it already exists
     */
    public User addUser(InetAddress ip, String nickname);

    /**
     *
     * @param ip
     * @return
     *  Delete the user form the HashMap, and return it
     *  The user is returned because it will disappear from the user list.
     */ 
    public User deleteUser(InetAddress ip);

    /**
     *
     * @param ip
     * @return the user matching with the param ip address.
     */ 
    public User getUserWithIP(InetAddress ip);
}
