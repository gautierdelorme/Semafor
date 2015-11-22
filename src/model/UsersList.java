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
public class UsersList {
    
    private HashMap<InetAddress, User> directory;
    
    public UsersList(){
        this.directory = new HashMap<InetAddress, User>();
    }

    protected void addUser(InetAddress ip, String nickname) {
        User user = createUser(ip, nickname);
        directory.put(ip, user);
        //System.out.println("I add "+nickname+" in the database [ip: "+ip+"]");
    }
    
    private User createUser(InetAddress ip, String nickname){
        //System.out.println("I create "+nickname+" [ip: "+ip+"]");
        return new User(nickname, ip);
    }

    protected void deleteUser(InetAddress ip) {
        directory.remove(ip);
        //System.out.println("I delete "+ip+" from the database");
    }

    protected String[] getNicknames(){
        System.out.println("Nickname list asked");
        int index = 0;
        String[] nicknamesFake = {"Arthur", "Papay0", "Frate", "Jaquie"};
        String[] nicknames = new String[directory.size()];
        for (Map.Entry<InetAddress, User> entrySet : directory.entrySet()) {
            nicknames[index] = entrySet.getValue().getNickname();
            index++;
        }
        return nicknamesFake;
    }

    protected void printUsers() {
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
