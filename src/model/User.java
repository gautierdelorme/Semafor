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
    private final String nickname;
    private final InetAddress ip;

    protected User(String nickname, InetAddress ip) {
        this.nickname = nickname;
        this.ip = ip;
    }
    
    public String getNickname() {
        return this.nickname;
    }

    public InetAddress getIpAddress() {
        return this.ip;
    }
    
    @Override
    public String toString() {
        return nickname;
    }
}
