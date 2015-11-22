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
import java.util.*;


/**
 *
 * @author arthurpapailhau
 */
public class UsersList implements CtrlToDatabase{
    
    private HashMap<InetAddress, User> directory;
    
    public UsersList(){
        this.directory = new HashMap<InetAddress, User>();
    }

    @Override
    public void addUser(InetAddress ip, String nickname) {
        User user = createUser(ip, nickname);
        directory.put(ip, user);
        //System.out.println("I add "+nickname+" in the database [ip: "+ip+"]");
    }
    
    private User createUser(InetAddress ip, String nickname){
        //System.out.println("I create "+nickname+" [ip: "+ip+"]");
        return new User(nickname, ip);
    }

    @Override
    public void deleteUser(InetAddress ip) {
        directory.remove(ip);
        //System.out.println("I delete "+ip+" from the database");
    }

    @Override
    public void printUsers() {
        System.out.println("Users:");
        System.out.println("-----------------------------------------");
        for (Map.Entry<InetAddress, User> entrySet : directory.entrySet()) {
            InetAddress ip = entrySet.getKey();
            User user = entrySet.getValue();
            System.out.println(" | Key:\n |     Ip : "+ip+"\n | Value:\n"+user);
        }
        System.out.println("-----------------------------------------");
    }
    
}
