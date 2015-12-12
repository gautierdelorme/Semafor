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
import netview.FromRmtApp;

/**
 *
 * @author gautier
 */
public class TCPServer extends Thread {

    private ServerSocket serverSocket;
    private final FromRmtApp chatNI;
    private boolean canRun;

    public TCPServer(ChatNI chatNI) {
        this.canRun = true;
        this.chatNI = chatNI;
        try {
            serverSocket = new ServerSocket(UDPReceiver.RECEIVING_PORT);
        } catch (IOException ex) {
            System.out.println("Exception TCP server cannot listen on the port " + UDPReceiver.RECEIVING_PORT + " : " + ex);
        }
    }

    @Override
    public void run() {
        while (canRun) {
            try {
                Socket clientSocket = serverSocket.accept();
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
        } catch (IOException e) {
            System.out.println("Exception when closing tcp server socket : " + e);
        }
    }
}
