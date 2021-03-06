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
import org.json.*;

/**
 * Handle UDP packet reception
 *
 * @author gautier
 */
class UDPReceiver extends Thread {

    private DatagramSocket socket;
    private final FromRmtApp chatNI;
    private InetAddress localAddress;
    private boolean canRun;
    protected final static int RECEIVING_PORT = 8045;

    protected UDPReceiver(ChatNI chatNI) {
        this.canRun = true;
        this.chatNI = chatNI;
        socket = null;
        try {
            localAddress = InetAddress.getLocalHost();
            socket = new DatagramSocket(RECEIVING_PORT);
        } catch (SocketException e) {
            System.out.println("Exception when creating the DatagramSocket into the UDPReceiver : " + e);
        } catch (UnknownHostException e) {
            System.out.println("Could not find the localAddress : " + e);
        }
    }

    protected DatagramSocket getSocket() {
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

    private boolean filterSucceed(InetAddress adress) {
        boolean canPassed = false;
        try {
            canPassed = !adress.equals(localAddress) && !adress.equals(InetAddress.getByName("127.0.0.1"));
        } catch (UnknownHostException e) {
            System.out.println("Could not find the localAddress : " + e);
        }
        return canPassed;
    }

    private void handlePacket(DatagramPacket packet) {
        if (filterSucceed(packet.getAddress())) {
            String stringReceive = new String(packet.getData(), 0, packet.getLength());
            JSONObject json = new JSONObject(stringReceive);
            UDPPacket message = new UDPPacket(json);
            switch (message.getType()) {
                case HELLO:
                    System.out.println("[UDP] Receive HELLO from "+packet.getAddress());
                    HelloPacket helloMessage = new HelloPacket(json);
                    this.chatNI.hello(packet.getAddress(), helloMessage.getNickname(), helloMessage.isReqReply());
                    break;
                case BYE:
                    System.out.println("[UDP] Receive BYE from "+packet.getAddress());
                    this.chatNI.bye(packet.getAddress());
                    break;
                case MESSAGE:
                    System.out.println("[UDP] Receive MESSAGE from "+packet.getAddress());
                    MessagePacket messageMessage = new MessagePacket(json);
                    this.chatNI.message(packet.getAddress(), messageMessage.getMessage());
                    break;
                case FILE_REQUEST:
                    System.out.println("[UDP] Receive FILE_REQUEST from "+packet.getAddress());
                    FileRequestPacket fileRequestPacket = new FileRequestPacket(json);
                    this.chatNI.fileRequest(packet.getAddress(), fileRequestPacket.getName(), fileRequestPacket.getTimestamp());
                    break;
                case FILE_REQUEST_RESPONSE:
                    System.out.println("[UDP] Receive FILE_REQUEST_RESPONSE from "+packet.getAddress());
                    FileRequestResponsePacket fileRequestResponsePacket = new FileRequestResponsePacket(json);
                    this.chatNI.fileRequestResponse(packet.getAddress(), fileRequestResponsePacket.getOk(), fileRequestResponsePacket.getTimestamp());
                    break;
                default:
                    System.out.println("Error when handling the received packet.");
            }
        }
    }

    protected void close() {
        this.canRun = false;
        this.socket.close();
    }
}
