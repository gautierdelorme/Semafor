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
    private File file;
    
    public TCPSender(InetAddress ip, File file) {
        this.file = file;
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
            System.out.println("I start sending file");
            DataOutputStream outputStream = new DataOutputStream(this.socket.getOutputStream());
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] buffer = new byte[1];
            while (fileInputStream.read(buffer) > 0) {
                outputStream.write(buffer);
            }
            fileInputStream.close();
            outputStream.flush();
            System.out.println("I stop sending file");
            /*this.socket.close();
            ObjectOutputStream outputStream = new ObjectOutputStream(this.socket.getOutputStream());
            outputStream.writeUTF(message);
            outputStream.flush();*/
        } catch (IOException e) {
            System.out.println("Exception when sending the tcp packet : " + e);
        }
    }
}