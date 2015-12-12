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
import java.util.List;
import model.User;

/**
 *
 * @author gautier
 */
public interface UIToCtrl {

    /**
     * Connect the current user
     * 
     * @param nickname nickname of the current user
     */
    public void performConnect(String nickname);

    /**
     * Disconnect the current user
     */
    public void performDisconnect();

    /**
     * Send a message
     * 
     * @param message the message to send
     * @param users the users who will receive the message
     */
    public void performSendMessage(String message, List<User> users);

    /**
     * Send a file
     * 
     * @param file the file to send
     * @param users the users who will receive the file
     */
    public void performSendFile(File file, List<User> users);

    /**
     * Send a file request response
     * 
     * @param ok true if the user accept the file
     * @param file the accepted file
     */
    public void performSendFileRequestResponse(boolean ok, File file);

    /**
     * Get the current user
     * 
     * @return the current user
     */
    public User getCurrentUser();
}
