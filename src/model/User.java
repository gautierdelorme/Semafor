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
    private InetAddress ip;

    public User(String nickname, InetAddress ip) {
        this.nickname = nickname;
        this.ip = ip;
    }
}
