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

    /**
     * Receive a Hello packet
     * 
     * @param ip ip address of the sender
     * @param nickname nickname of the sender
     * @param reqReply true if the sender wants a response
     */
    public void receiveHello(InetAddress ip, String nickname, boolean reqReply);

    /**
     * Receive a Bye packet
     * 
     * @param ip ip address of the sender
     */
    public void receiveBye(InetAddress ip);

    /**
     * Receive a message
     * 
     * @param ip ip address of the sender
     * @param message message received
     */
    public void receiveMessage(InetAddress ip, String message);

    /**
     * Receive a file
     * 
     * @param ip ip address of the sender
     * @param file file received
     */
    public void receiveFile(InetAddress ip, File file);

    /**
     * Receive a file request
     * 
     * @param ip ip address of the sender
     * @param file file from the file request received
     */
    public void receiveFileRequest(InetAddress ip, File file);

    /**
     *  Receive a file request response
     * 
     * @param ip ip address of the sender
     * @param file file from the file request response received
     */
    public void receiveFileResponse(InetAddress ip, File file);
}
