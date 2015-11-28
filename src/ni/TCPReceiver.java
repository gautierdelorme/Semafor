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
public class TCPReceiver extends Thread {

    private final Socket socket;
    private final ChatNI chatNI;

    public TCPReceiver(Socket socket, ChatNI chatNI) {
        this.socket = socket;
        this.chatNI = chatNI;
    }

    @Override
    public void run() {
            try (FileOutputStream fileOutputStream = new FileOutputStream(chatNI.getFileToReceived(socket.getInetAddress()).getName())) {
                byte[] buffer = new byte[1];
                while (this.socket.getInputStream().read(buffer) >= 0) {
                    fileOutputStream.write(buffer);
                }
                this.chatNI.file(this.socket.getInetAddress(), chatNI.getFileToReceived(socket.getInetAddress()));
                this.socket.close();
            } catch (IOException e) {
            System.out.println("Exception when receiving the tcp packet : " + e);
        }
    }
}
