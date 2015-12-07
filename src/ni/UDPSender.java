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
import java.nio.charset.Charset;

/**
 *
 * @author gautier
 */
public class UDPSender {

    private final DatagramSocket socket;
    private final static String BROADCAST_ADDRESS = "255.255.255.255";

    public UDPSender(DatagramSocket socket) {
        this.socket = socket;
    }
    
    public void sendTo(InetAddress ip, String message) {
        System.out.println("SEND MESSAGE UDP");
        sendMessage(ip, message);
    }
    
    public void sendToAll(String message) {
        System.out.println("SEND BROADCAST UDP");
        try {
            sendMessage(InetAddress.getByName(BROADCAST_ADDRESS), message);
        } catch (UnknownHostException e) {
            System.out.println("Exception when sendToAll : "+e); 
        }
    }
    
    private void sendMessage(InetAddress ip, String message) {
        try {
            byte[] sendData = message.getBytes(Charset.forName("UTF-8"));
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ip, UDPReceiver.RECEIVING_PORT);
            socket.send(sendPacket);
        } catch (IOException e) {
            System.out.println("Exception when sending the packet : " + e);
        }
    }
}