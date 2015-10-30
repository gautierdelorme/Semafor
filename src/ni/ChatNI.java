/*
 * Semafor - MIT License
 *
 * A peer-to-peer chat system
 *
 * Copyright 2015 Gautier Delorme and Arthur Papailhau.
 *
 */
package ni;

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
        UPDPacket helloMessage = new UPDPacket(UPDPacket.typeMessage.HELLO, nickname, reqReply);
        this.udpSender.sendToAll(helloMessage.toString());
    }

    @Override
    public void sendBye() {
        UPDPacket byeMessage = new UPDPacket(UPDPacket.typeMessage.BYE, false);
        this.udpSender.sendToAll(byeMessage.toString());
    }

    @Override
    public void sendMessage(String message, String ip) {
        (new TCPSender(ip, message)).start();
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
        niToCtrl.ReceiveMessage(ip, message);
    }
    
    public void close() {
        this.tcpServer.close();
        this.udpReceiver.close();
    }
}
