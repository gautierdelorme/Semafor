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
public class TCPServer extends Thread {
    
    private ServerSocket serverSocket;
    private final ChatNI chatNI;
    public final static int LISTEN_PORT = 8046;

    public TCPServer(ChatNI chatNI) {
        this.chatNI = chatNI;
        try {
            serverSocket = new ServerSocket(LISTEN_PORT);
        } catch (IOException ex) {
            System.out.println("Exception cannot listen on the port " + LISTEN_PORT + " : " + ex);
            System.exit(-1);
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                Socket clientSocket = serverSocket.accept();
                (new TCPReceiver(clientSocket, chatNI)).start();
                System.out.println("Server connected with : " + clientSocket.getInetAddress());
            } catch (Exception e) {
                System.out.println("Accept fail : " + e);
                System.exit(-1);
            }
        }
    }
}