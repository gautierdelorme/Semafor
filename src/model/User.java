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
 * @author gautier
 */
public class User {
    private String nickname;
    private final InetAddress ip;
    
    /**
     * Create a new user
     * 
     * @param nickname nickname of the new user
     * @param ip ip address of the new user
     * @return the new user
     */
    protected static User createUser(String nickname, InetAddress ip) {
        return new User(nickname, ip);
    }

    private User(String nickname, InetAddress ip) {
        this.nickname = nickname;
        this.ip = ip;
    }
    
    /**
     * Get the nickname of the user
     * 
     * @return nickname of the user
     */
    public String getNickname() {
        return this.nickname;
    }
    
    /**
     * Set nickname of the user
     * 
     * @param nickname nickname of the user
     */
    protected void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * Get the ip address of the user
     * 
     * @return ip address of the user
     */
    public InetAddress getIpAddress() {
        return this.ip;
    }
    
    @Override
    public String toString() {
        return nickname;
    }
}
