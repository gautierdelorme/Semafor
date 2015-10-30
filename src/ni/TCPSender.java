/*
 * Semafor - MIT License
 *
 * A peer-to-peer chat system
 *
 * Copyright 2015 Gautier Delorme and Arthur Papailhau.
 *
 */
package ni;

import java.io.*;
import java.net.*;

/**
 *
 * @author gautier
 */
public class TCPSender extends Thread {
    
    private Socket socket;
    private String message;
    
    public TCPSender(String ip, String message) {
        this.message = message;
        this.socket = null;
        try {
            this.socket = new Socket(ip, TCPServer.LISTEN_PORT);
        } catch (UnknownHostException e) {
            System.out.println("Could not find the dest : " + e);
        } catch (IOException e) {
            System.out.println("Exception when building TCPSender : " + e);
        }
    }

    @Override
    public void run() {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(this.socket.getOutputStream());
            outputStream.writeUTF(message);
            outputStream.flush();
            this.socket.close();
        } catch (IOException e) {
            System.out.println("Exception when sending the tcp packet : " + e);
        }
    }
}