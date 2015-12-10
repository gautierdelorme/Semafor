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
public interface CtrlToNI {
    public void sendHello(String nickname, boolean reqReply);
    public void sendHelloTo(InetAddress ip, String nickname, boolean reqReply);
    public void sendBye();
    public void sendMessage(String message, InetAddress ip);
    public void sendFile(File file, InetAddress ip);
    public void sendFileRequestResponse(boolean ok, File file);
}
