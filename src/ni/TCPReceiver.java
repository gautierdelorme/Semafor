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
        try {
            DataInputStream inputStream = new DataInputStream(this.socket.getInputStream());
            //FileInputStream fileInputStream = new FileInputStream();
            
            /*ObjectInputStream inputStream = new ObjectInputStream(this.socket.getInputStream());
            String result = inputStream.readUTF();
            this.chatNI.message(this.socket.getInetAddress().toString(), result);
            this.socket.close();*/
        } catch (IOException e) {
            System.out.println("Exception when receiving the tcp packet : " + e);
        }
    }
}