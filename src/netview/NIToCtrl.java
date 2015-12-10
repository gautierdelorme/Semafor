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
import java.net.InetAddress;

/**
 *
 * @author gautier
 */
public interface NIToCtrl {
    public void receiveHello(InetAddress ip, String nickname, boolean reqReply);
    public void receiveBye(InetAddress ip);
    public void receiveMessage(InetAddress ip, String message);
    public void receiveFile(InetAddress ip, File file);
    public void receiveFileRequest(InetAddress ip, File file);
}
