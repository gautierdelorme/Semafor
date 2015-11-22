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
public class UsersList implements CtrlToDatabase{
    
    public static UsersList buildUsersList(){
        UsersList usersList = new UsersList();
        return usersList;
    }
    
    private UsersList(){
    }

    @Override
    public void addUser(InetAddress ip, String nickname) {
        System.out.println("I add "+nickname+" in the database [ip: "+ip+"]");
    }
    
}
