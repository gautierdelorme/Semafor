/*
 * Semafor - MIT License
 *
 * A peer-to-peer chat system
 *
 * Copyright 2015 Gautier Delorme and Arthur Papailhau.
 *
 */
package netview;

/**
 *
 * @author gautier
 */
public interface CtrlToNI {
    public void sendHello(String nickname, boolean reqReply);
    public void sendBye();
    public void sendMessage(String message, String ip);
}
