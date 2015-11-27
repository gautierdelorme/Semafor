/*
 * Semafor - MIT License
 *
 * A peer-to-peer chat system
 *
 * Copyright 2015 Gautier Delorme and Arthur Papailhau.
 *
 */
package ni;

import java.io.File;
import java.net.InetAddress;
import java.util.HashMap;
import netview.*;

/**
 *
 * @author gautier
 */
public class ChatNI implements CtrlToNI, FromToRmtApp {
    private UDPReceiver udpReceiver;
    private UDPSender udpSender;
    private TCPServer tcpServer;
    private NIToCtrl niToCtrl;
    
    private HashMap<Integer, File> bufferFiles;
    
    public static ChatNI buildChatNI() {
        ChatNI chatNI = new ChatNI();
        chatNI.bufferFiles = new HashMap<>();
        chatNI.udpReceiver = new UDPReceiver(chatNI);
        chatNI.udpSender = new UDPSender(chatNI.udpReceiver.getSocket());
        chatNI.tcpServer = new TCPServer(chatNI);
        chatNI.udpReceiver.start();
        chatNI.tcpServer.start();
        return chatNI;
    }

    private ChatNI() {}

    public void setNiToCtrl(NIToCtrl niToCtrl) {
        this.niToCtrl = niToCtrl;
    }
    
    @Override
    public void sendHello(String nickname, boolean reqReply) {
        UDPPacket helloMessage = new HelloPacket(reqReply, nickname);
        this.udpSender.sendToAll(helloMessage.toString());
    }
    
    @Override
    public void sendHelloTo(InetAddress ip, String nickname, boolean reqReply) {
        UDPPacket helloMessage = new HelloPacket(reqReply, nickname);
        this.udpSender.sendTo(ip, helloMessage.toString());
    }

    @Override
    public void sendBye() {
        UDPPacket byeMessage = new ByePacket();
        this.udpSender.sendToAll(byeMessage.toString());
    }

    @Override
    public void sendMessage(String message, InetAddress ip) {
        UDPPacket messagePacket = new MessagePacket(message);
        this.udpSender.sendTo(ip, messagePacket.toString());
    }
    
    @Override
    public void sendFile(File file, InetAddress ip) {
        sendFileRequest(file, ip);
        //(new TCPSender(ip, file)).start();
    }
    
    protected void sendFileRequest(File file, InetAddress ip) {
        FileRequestPacket fileRequestPacket = new FileRequestPacket(file.getName());
        bufferFiles.put(fileRequestPacket.getTimestamp(), file);
        this.udpSender.sendTo(ip, fileRequestPacket.toString());
    }
    
    protected void sendFileRequestResponse(boolean ok, int timestamp, InetAddress ip) {
        UDPPacket fileRequestResponsePacket = new FileRequestResponsePacket(ok, timestamp);
        this.udpSender.sendTo(ip, fileRequestResponsePacket.toString());
    }
    
    protected void fileRequest(InetAddress ip, String name, int timestamp) {
        System.out.println("Do you want: "+name+" ?");
        sendFileRequestResponse(true, timestamp, ip);
    }
    
    protected void fileRequestResponse(InetAddress ip, boolean ok) {
        // (new TCPSender(ip, file)).start();
        if (ok){
            System.out.println("Send file");
        } else {
            System.out.println("I don't send your fucking file!");            
        }
    }
    
    @Override
    public void hello(InetAddress ip, String nickname, boolean reqReply) {
        niToCtrl.receiveHello(ip, nickname, reqReply);
    }

    @Override
    public void bye(InetAddress ip) {
        niToCtrl.receiveBye(ip);
    }

    @Override
    public void message(InetAddress ip, String message) {
        niToCtrl.receiveMessage(ip, message);
    }

    @Override
    public void file(InetAddress ip, File file) {
        niToCtrl.receiveFile(ip, file);
    }
    
    
    public void close() {
        this.tcpServer.close();
        this.udpReceiver.close();
    }
}
