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
    
    public void sendToAll(String message) {
        try {
            byte[] sendData = message.getBytes(Charset.forName("UTF-8"));
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName(BROADCAST_ADDRESS), UDPReceiver.RECEIVING_PORT);
            socket.send(sendPacket);
        } catch (UnknownHostException e) {
            System.out.println("Could not find the host : " + e);
        } catch (IOException e) {
            System.out.println("Exception when sending the packet : " + e);
        }
    }
}