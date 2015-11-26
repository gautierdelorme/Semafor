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
        try (DataInputStream inputStream = new DataInputStream(this.socket.getInputStream())) {
            try (FileOutputStream fileOutputStream = new FileOutputStream("test.txt")) {
                byte[] buffer = new byte[1];
                int a;
                while ((a = inputStream.read(buffer)) != 0) {
                    System.out.println(a);
                    fileOutputStream.write(buffer);
                }
                System.out.println("hooooooooooo");
                this.chatNI.file(this.socket.getInetAddress(), new File("test.png"));
            }
            System.out.println("eey");
        } catch (IOException e) {
            System.out.println("Exception when receiving the tcp packet : " + e);
        }
    }
}
