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
    
    protected static User createUser(String nickname, InetAddress ip) {
        return new User(nickname, ip);
    }

    private User(String nickname, InetAddress ip) {
        this.nickname = nickname;
        this.ip = ip;
    }
    
    public String getNickname() {
        return this.nickname;
    }
    
    protected void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public InetAddress getIpAddress() {
        return this.ip;
    }
    
    @Override
    public String toString() {
        return nickname;
    }
}
