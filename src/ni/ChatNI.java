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
import java.util.Map.Entry;
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
    private HashMap<Integer, File> filesToSend;
    private HashMap<File, InetAddress> filesToReceivePerFile;
    private HashMap<File, Integer> filesToReceiveOfTimestamp;

    public static ChatNI buildChatNI(NIToCtrl niToCtrl) {
        ChatNI chatNI = new ChatNI();
        chatNI.niToCtrl = niToCtrl;
        chatNI.filesToSend = new HashMap<>();

        chatNI.filesToReceivePerFile = new HashMap<>();
        chatNI.filesToReceiveOfTimestamp = new HashMap<>();

        chatNI.udpReceiver = new UDPReceiver(chatNI);
        chatNI.udpSender = new UDPSender(chatNI.udpReceiver.getSocket());
        chatNI.tcpServer = new TCPServer(chatNI);
        chatNI.udpReceiver.start();
        chatNI.tcpServer.start();
        return chatNI;
    }

    private ChatNI() {
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
    }

    @Override
    public void sendFileRequestResponse(boolean ok, File file) {
        UDPPacket fileRequestResponsePacket = new FileRequestResponsePacket(ok, filesToReceiveOfTimestamp.get(file));
        this.udpSender.sendTo(filesToReceivePerFile.get(file), fileRequestResponsePacket.toString());
    }

    protected void sendFileRequest(File file, InetAddress ip) {
        FileRequestPacket fileRequestPacket = new FileRequestPacket(file.getName());
        filesToSend.put(fileRequestPacket.getTimestamp(), file);
        this.udpSender.sendTo(ip, fileRequestPacket.toString());
    }

    @Override
    public void fileRequest(InetAddress ip, String name, int timestamp) {
        File file = new File(name);
        filesToReceivePerFile.put(file, ip);
        filesToReceiveOfTimestamp.put(file, timestamp);
        niToCtrl.receiveFileRequest(ip, file);
    }

    @Override
    public void fileRequestResponse(InetAddress ip, boolean ok, int timestamp) {
        if (ok) {
            File file = filesToSend.get(timestamp);
            niToCtrl.receiveFileResponse(ip, file);
            (new TCPSender(ip, file)).start();
        } else {
            System.out.println("The user does not want the file");
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

    protected File getFileToReceived(InetAddress ip) {
        File file = null;
        for (Entry<File, InetAddress> entry : filesToReceivePerFile.entrySet()) {
            if (entry.getValue().equals(ip)) {
                file = entry.getKey();
            }
        }
        filesToReceivePerFile.remove(file);
        return file;
    }

    public void close() {
        this.tcpServer.close();
        this.udpReceiver.close();
    }
}
