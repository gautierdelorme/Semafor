/*
 * Semafor - MIT License
 *
 * A peer-to-peer chat system
 *
 * Copyright 2015 Gautier Delorme and Arthur Papailhau.
 *
 */
package ui;

import java.io.File;
import java.util.List;
import model.User;

/**
 *
 * @author gautier & Arthur
 */
public interface FromUser {
    public void connect(String nickname);
    public void disconnect();
    public void sendFile(File file, List<User> users);
    public void sendMessage(String message, List<User> users);
    public User getCurrentUser();
}
