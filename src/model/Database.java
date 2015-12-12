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
import java.net.UnknownHostException;

/**
 *
 * @author arthurpapailhau
 */
public class Database implements CtrlToDatabase {
    
    private UsersList userList;
    private User currentUser;
    
    /**
     * Create a new Database
     */
    public Database(){
        this.userList = new UsersList();
    }
    
    @Override
    public void removeUserList() {
        this.userList = new UsersList();
    }
    
    @Override
    public User addUser(InetAddress ip, String nickname) {
        return userList.addUser(ip, nickname);
    }
    
    @Override
    public User deleteUser(InetAddress ip) {
        return userList.deleteUser(ip);
    }

    @Override
    public User getUserWithIP(InetAddress ip) {
        return userList.getUserWithIP(ip);
    }

    @Override
    public User getCurrentUser() {
        return this.currentUser;
    }

    @Override
    public void setCurrentUser(String nickname) {
        try {
            this.currentUser = User.createUser(nickname, InetAddress.getLocalHost());
        } catch (UnknownHostException e) {
            System.out.println("Error when creating the currentUser");
        }
    }
    
    @Override
    public void removeCurrentUser() {
        this.currentUser = null;
    }
}
