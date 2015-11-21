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
public interface FromUser {
    public void connect(String nickname);
    public void disconnect();
    public void selectUsers();
    public void selectFile();
    public void sendMessage(String message);
}
