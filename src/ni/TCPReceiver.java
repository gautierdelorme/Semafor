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
 * Handle files reception
 *
 * @author gautier
 */
class TCPReceiver extends Thread {

    private final Socket socket;
    private final FromRmtApp chatNI;

    protected TCPReceiver(Socket socket, FromRmtApp chatNI) {
        this.socket = socket;
        this.chatNI = chatNI;
    }

    @Override
    public void run() {
        File file = chatNI.getFileToReceived(socket.getInetAddress());
        System.out.println("[TCP] Start Receiving "+file.getName()+" from "+this.socket.getInetAddress());
        try (FileOutputStream fileOutputStream = new FileOutputStream(file.getName())) {
            byte[] buffer = new byte[1];
            while (this.socket.getInputStream().read(buffer) >= 0) {
                fileOutputStream.write(buffer);
            }
            System.out.println("[TCP] Stop Receiving "+file.getName()+" from "+this.socket.getInetAddress());
            this.chatNI.file(this.socket.getInetAddress(), file);
            this.socket.close();
        } catch (IOException e) {
            System.out.println("Exception when receiving the tcp packet : " + e);
        }
    }
}
