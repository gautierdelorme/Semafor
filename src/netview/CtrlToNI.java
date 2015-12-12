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

    /**
     * Send a broadcast Hello packet
     * 
     * @param nickname nickname of the user
     * @param reqReply  true if the user wants a response from others
     */
    public void sendHello(String nickname, boolean reqReply);

    /**
     * Send a Hello packet to a specific ip
     * 
     * @param ip ip address of the user
     * @param nickname nickname of the user
     * @param reqReply true if the user wants a response from others
     */
    public void sendHelloTo(InetAddress ip, String nickname, boolean reqReply);

    /**
     * Send a broadcast Bye packet
     */
    public void sendBye();

    /**
     * Send a message to a specific user
     * 
     * @param message message to send
     * @param ip ip address of the user
     */
    public void sendMessage(String message, InetAddress ip);

    /**
     * Send a file to a specific user
     * 
     * @param file file to send
     * @param ip ip address of the user
     */
    public void sendFile(File file, InetAddress ip);

    /**
     * Send a file request response to a specific user
     * 
     * @param ok boolean to know if the remote user has accepted the file
     * @param file file to send
     */
    public void sendFileRequestResponse(boolean ok, File file);
}
