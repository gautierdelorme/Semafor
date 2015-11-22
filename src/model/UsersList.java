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
    
    public UsersList(){
        this.directory = new HashMap<>();
    }

    protected void addUser(InetAddress ip, String nickname) {
        User user = createUser(ip, nickname);
        directory.put(ip, user);
    }
    
    private User createUser(InetAddress ip, String nickname){
        return new User(nickname, ip);
    }

    protected void deleteUser(InetAddress ip) {
        directory.remove(ip);
    }

    protected String[] getNicknames(){
        return directory.values().stream().map(x -> x.getNickname()).toArray(String[]::new);
    }
}
