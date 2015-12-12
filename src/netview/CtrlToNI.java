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
     *
     * @param nickname nickname of the user
     * @param reqReply  
     */
    public void sendHello(String nickname, boolean reqReply);

    /**
     *
     * @param ip ip address of the user
     * @param nickname nickname of the user
     * @param reqReply
     */
    public void sendHelloTo(InetAddress ip, String nickname, boolean reqReply);

    /**
     * send to all a ByePacket
     */
    public void sendBye();

    /**
     *
     * @param message message to send
     * @param ip ip address of the user
     */
    public void sendMessage(String message, InetAddress ip);

    /**
     *
     * @param file file to send
     * @param ip ip address of the user
     */
    public void sendFile(File file, InetAddress ip);

    /**
     *
     * @param ok boolean to know if the remote user has accepted the file
     * @param file file to send
     */
    public void sendFileRequestResponse(boolean ok, File file);
}
