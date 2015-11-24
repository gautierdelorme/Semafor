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
    
    private final HashMap<InetAddress, User> directory;
    
    protected UsersList(){
        this.directory = new HashMap<>();
    }

    protected User addUser(InetAddress ip, String nickname) {
        User user = createUser(ip, nickname);
        directory.put(ip, user);
        return user;
    }
    
    private User createUser(InetAddress ip, String nickname){
        return new User(nickname, ip);
    }

    protected void deleteUser(InetAddress ip) {
        directory.remove(ip);
    }

    protected User[] getUsers(){
        return directory.values().stream().toArray(User[]::new);
    }
    
    public User getUserWithIP(InetAddress ip) {
        return directory.get(ip);
    }
}
