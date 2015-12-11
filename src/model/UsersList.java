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
        User u = directory.get(ip);
        if (u == null) {
            u = User.createUser(nickname, ip);
            directory.put(ip, u);
        } else {
            u.setNickname(nickname);
        }
        return u;
    }

    protected User deleteUser(InetAddress ip) {
        return directory.remove(ip);
    }
    
    public User getUserWithIP(InetAddress ip) {
        return directory.get(ip);
    }
}