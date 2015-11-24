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
public class Database implements CtrlToDatabase{
    
    protected UsersList userList;
    
    public Database(){
        this.userList = new UsersList();
    }
    
    @Override
    public User addUser(InetAddress ip, String nickname) {
        return userList.addUser(ip, nickname);
    }

    @Override
    public void deleteUser(InetAddress ip) {
        userList.deleteUser(ip);
    }

    @Override
    public User[] getUsers() {
        return userList.getUsers();
    }

    @Override
    public User getUserWithIP(InetAddress ip) {
        return userList.getUserWithIP(ip);
    }
}
