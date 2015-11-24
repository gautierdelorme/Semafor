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
 * @author gautier
 */
public interface UIToCtrl {
    public void performConnect(String nickname);
    public void performDisconnect();
    public void performSendMessage(String message, User user);
    public void performSendFile(File file, User user);
}
