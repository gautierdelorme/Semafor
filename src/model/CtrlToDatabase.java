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
    public User getCurrentUser();
    public void setCurrentUser(String nickname);
    public void removeCurrentUser();
    public void removeUserList();
    public User addUser(InetAddress ip, String nickname);
    public User deleteUser(InetAddress ip);
    public User getUserWithIP(InetAddress ip);
    public boolean canAddUser(InetAddress ip, String nickname);
}
