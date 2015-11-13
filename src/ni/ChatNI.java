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
    
    public static ChatNI buildChatNI() {
        ChatNI chatNI = new ChatNI();
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
    public void sendBye() {
        UDPPacket byeMessage = new ByePacket();
        this.udpSender.sendToAll(byeMessage.toString());
    }

    @Override
    public void sendMessage(String message, String ip) {
        //(new TCPSender(ip, message)).start();
        MessagePacket messagePacket = new MessagePacket(message);
        this.udpSender.sendTo(ip, messagePacket.toString());
    }
    
    @Override
    public void hello(String ip, String nickname, boolean reqReply) {
        niToCtrl.receiveHello(ip, nickname, reqReply);
    }

    @Override
    public void bye(String ip) {
        niToCtrl.receiveBye(ip);
    }

    @Override
    public void message(String ip, String message) {
        niToCtrl.receiveMessage(ip, message);
    }

    @Override
    public void file(String ip, File file) {
        niToCtrl.receiveFile(ip, file);
    }
    
    public void close() {
        this.tcpServer.close();
        this.udpReceiver.close();
    }
}
