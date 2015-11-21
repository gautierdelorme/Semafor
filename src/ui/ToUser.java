/*
 * Semafor - MIT License
 *
 * A peer-to-peer chat system
 *
 * Copyright 2015 Gautier Delorme and Arthur Papailhau.
 *
 */
package ui;

/**
 *
 * @author gautier
 */
public interface ToUser {
    public void connected(boolean ok);
    public void disconnected(boolean ok);
    public void userlist();
    public void messageReceived(String message, String nickname);
}
