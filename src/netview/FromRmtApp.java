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
public interface FromRmtApp {

    /**
     * Receive a Hello packet
     * 
     * @param ip ip address of the sender
     * @param nickname nickname of the sender
     * @param reqReply true if the sender wants a response
     */
    public void hello(InetAddress ip, String nickname, boolean reqReply);

    /**
     * Receive a Bye packet
     * 
     * @param ip ip address of the sender
     */
    public void bye(InetAddress ip);

    /**
     * Receive a message
     * 
     * @param ip ip address of the sender
     * @param message message received
     */
    public void message(InetAddress ip, String message);

    /**
     * Receive a file
     * 
     * @param ip ip address of the sender
     * @param file file received
     */
    public void file(InetAddress ip, File file);

    /**
     * Receive a file request
     * 
     * @param ip ip address of the sender
     * @param name name of the file to received
     * @param timestamp unique id of the file
     */
    public void fileRequest(InetAddress ip, String name, int timestamp);

    /**
     *  Receive a file request response
     * 
     * @param ip ip address of the sender
     * @param ok true if the sender accept the file
     * @param timestamp unique id of the file
     */
    public void fileRequestResponse(InetAddress ip, boolean ok, int timestamp);
    
    /**
     *  Get the file matching with the ip address
     * 
     * @param ip ip address of the sender
     * @return the file matching with the ip address
     */
    public File getFileToReceived(InetAddress ip);
}
