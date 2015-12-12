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
class UDPSender {

    private final DatagramSocket socket;
    private final static String BROADCAST_ADDRESS = "255.255.255.255";

    protected UDPSender(DatagramSocket socket) {
        this.socket = socket;
    }
    
    protected void sendTo(InetAddress ip, String message) {
        System.out.println("[UDP] Send packet to "+ip);
        sendMessage(ip, message);
    }
    
    protected void sendToAll(String message) {
        System.out.println("[UDP] Send packet BROADCAST");
        try {
            sendMessage(InetAddress.getByName(BROADCAST_ADDRESS), message);
        } catch (UnknownHostException e) {
            System.out.println("Exception when sendToAll : "+e); 
        }
    }
    
    protected void sendMessage(InetAddress ip, String message) {
        try {
            byte[] sendData = message.getBytes(Charset.forName("UTF-8"));
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ip, UDPReceiver.RECEIVING_PORT);
            socket.send(sendPacket);
        } catch (IOException e) {
            System.out.println("Exception when sending the packet : " + e);
        }
    }
}