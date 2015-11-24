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
public interface CtrlToDatabase {
    public User addUser(InetAddress ip, String nickname);
    public void deleteUser(InetAddress ip);
    public User getUserWithIP(InetAddress ip);
    public User[] getUsers();
}
