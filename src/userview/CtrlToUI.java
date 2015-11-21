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

/**
 *
 * @author arthurpapailhau
 */
public interface CtrlToUI {
    public void refreshUsersList();
    public void displayMessage(String message, String nickname);
    public void displayFile(File file, String nickname);
}
