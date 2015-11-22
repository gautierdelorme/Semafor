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

    public static Database buildDatabase(){
        Database database = new Database();
        database.userList = new UsersList();
        return database;
    }
    
    private Database(){}
    
    @Override
    public void addUser(InetAddress ip, String nickname) {
        userList.addUser(ip, nickname);
    }

    @Override
    public void deleteUser(InetAddress ip) {
        userList.deleteUser(ip);
    }

    @Override
    public void printUsers() {
        userList.printUsers();
    }

    @Override
    public String[] getNicknames() {
        return userList.getNicknames();
    }
    
}
