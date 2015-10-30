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
public interface NIToCtrl {
    public void receiveHello(String ip, String nickname, boolean reqReply);
    public void receiveBye(String ip);
    public void ReceiveMessage(String ip, String message);
}
