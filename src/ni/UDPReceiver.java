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
import org.json.*;

/**
 *
 * @author gautier
 */
public class UDPReceiver extends Thread {

    private DatagramSocket socket;
    private final ChatNI chatNI;
    private boolean canRun;
    public final static int RECEIVING_PORT = 8045;
    
    public UDPReceiver(ChatNI chatNI){
        this.canRun = true;
        this.chatNI = chatNI;
        socket = null;
        try {
            socket = new DatagramSocket(RECEIVING_PORT);
        } catch (SocketException e) {
            System.out.println("Exception when creating the DatagramSocket into the UDPReceiver : " + e);
        }
    }
    
    public DatagramSocket getSocket() {
        return this.socket;
    }

    @Override
    public void run() {
        byte[] receiveData = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        while (canRun) {
            try {
                socket.receive(receivePacket);
                handlePacket(receivePacket);
            } catch (IOException e) {
                System.out.println("Exception when receive : " + e);
            }
        }
    }
    
    private void handlePacket(DatagramPacket packet) {
        String stringReceive = new String(packet.getData(), 0, packet.getLength());
        UDPPacket message = new UDPPacket(new JSONObject(stringReceive));
        switch (message.getType()) {
            case HELLO:
                HelloPacket helloMessage = new HelloPacket(new JSONObject(stringReceive));
                this.chatNI.hello(packet.getAddress().toString(), helloMessage.getNickname(), helloMessage.isReqReply());
                break;
            case BYE:
                this.chatNI.bye(packet.getAddress().toString());
                break;
            case MESSAGE:
                MessagePacket messageMessage = new MessagePacket(new JSONObject(stringReceive));
                this.chatNI.message(packet.getAddress().toString(), messageMessage.getMessage());
                break;
            case FILE_REQUEST:
                //processFile();
            default:
                System.out.println("Error when handling the received packet.");
        }
    }
    
    public void close() {
        this.canRun = false;
        this.socket.close();
    }
}
