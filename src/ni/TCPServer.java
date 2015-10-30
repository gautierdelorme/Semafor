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
    private boolean canRun;
    public final static int LISTEN_PORT = 8046;

    public TCPServer(ChatNI chatNI) {
        this.canRun = true;
        this.chatNI = chatNI;
        try {
            serverSocket = new ServerSocket(LISTEN_PORT);
        } catch (IOException ex) {
            System.out.println("Exception TCP server cannot listen on the port " + LISTEN_PORT + " : " + ex);
        }
    }

    @Override
    public void run() {
        while (canRun) {
            try {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected to Server");
                (new TCPReceiver(clientSocket, chatNI)).start();
            } catch (IOException e) {
                System.out.println("TCP server accept fail : " + e);
            }
        }
    }

    public void close() {
        this.canRun = false;
        try {
            this.serverSocket.close();
            System.out.println("TCPServer closed");
        } catch (IOException e) {
            System.out.println("Exception when closing tcp server socket : " + e);
        }
    }
}
