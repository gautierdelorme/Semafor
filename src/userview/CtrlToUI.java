/*
 * Semafor - MIT License
 *
 * A peer-to-peer chat system
 *
 * Copyright 2015 Gautier Delorme and Arthur Papailhau.
 *
 */
package userview;

import java.io.File;
import model.User;

/**
 *
 * @author arthurpapailhau
 */
public interface CtrlToUI {
    public void displayMessage(String message, User user);
    public void displayFile(File file, User user);
    public void displayFileRequest(File file, User user);
    public void addUser(User user);
    public void removeUser(User user);
}
