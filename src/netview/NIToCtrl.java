/*
 * Semafor - MIT License
 *
 * A peer-to-peer chat system
 *
 * Copyright 2015 Gautier Delorme and Arthur Papailhau.
 *
 */
package netview;

import java.io.File;

/**
 *
 * @author gautier
 */
public interface NIToCtrl {
    public void receiveHello(String ip, String nickname, boolean reqReply);
    public void receiveBye(String ip);
    public void receiveMessage(String ip, String message);
    public void receiveFile(String ip, File file);
}
