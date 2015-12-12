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

    /**
     * Display a message to the user interface
     * 
     * @param message message received
     * @param user user who sent the message
     */
    public void displayMessage(String message, User user);

    /**
     * Display a file to the user interface
     * 
     * @param file file received
     * @param user user who sent the file
     */
    public void displayFile(File file, User user);

    /**
     * Display a file request to the user interface
     * 
     * @param file file from the file request
     * @param user user who sent the file request
     */
    public void displayFileRequest(File file, User user);

    /**
     * Display a file request response to the user interface
     * 
     * @param file file from the file request response
     * @param user user who sent the file request response
     */
    public void displayFileResponse(File file, User user);

    /**
     * Add an user to the userslist
     * 
     * @param user user who joined the room
     */
    public void addUser(User user);

    /**
     * Remove an user to the userslist
     * 
     * @param user user who left the room
     */
    public void removeUser(User user);
}
