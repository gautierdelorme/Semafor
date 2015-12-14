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
 * Send files
 *
 * @author gautier
 */
class TCPSender extends Thread {
    
    private Socket socket;
    private File file;
    
    protected TCPSender(InetAddress ip, File file) {
        this.file = file;
        this.socket = null;
        try {
            this.socket = new Socket(ip, UDPReceiver.RECEIVING_PORT);
        } catch (UnknownHostException e) {
            System.out.println("Could not find the dest : " + e);
        } catch (IOException e) {
            System.out.println("Exception when building TCPSender : " + e);
        }
    }

    @Override
    public void run() {
        try {
            System.out.println("[TCP] Start Sending "+file.getName()+" to "+this.socket.getInetAddress());
            DataOutputStream outputStream = new DataOutputStream(this.socket.getOutputStream());
            try (FileInputStream fileInputStream = new FileInputStream(file)) {
                byte[] buffer = new byte[1];
                while (fileInputStream.read(buffer) > -1) {
                    this.socket.getOutputStream().write(buffer);
                }
            }
            outputStream.flush();
            System.out.println("[TCP] Stop Sending "+file.getName()+" to "+this.socket.getInetAddress());
            this.socket.close();
        } catch (IOException e) {
            System.out.println("Exception when sending the tcp packet : " + e);
        }
    }
}