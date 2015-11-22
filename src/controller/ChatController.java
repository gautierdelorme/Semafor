/*
 * Semafor - MIT License
 *
 * A peer-to-peer chat system
 *
 * Copyright 2015 Gautier Delorme and Arthur Papailhau.
 *
 */
package controller;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import netview.*;
import userview.*;

/**
 *
 * @author gautier
 */
public class ChatController implements NIToCtrl, UIToCtrl {
    
    private CtrlToNI ctrlToNI;

    public void setCtrlToNI(CtrlToNI ctrlToNI) {
        this.ctrlToNI = ctrlToNI;
    }

    @Override
    public void performConnect(String nickname) {
        ctrlToNI.sendHello(nickname, true);
    }

    @Override
    public void performDisconnect() {
        ctrlToNI.sendBye();
    }
    
    @Override
    public void performSendFile(File file) {
        System.out.println("Opening: " + file.getPath() + ".");
    }

    @Override
    public void performSendMessage(String message, String ip) {
        try {
            ctrlToNI.sendMessage(message, InetAddress.getByName(ip));
        } catch (UnknownHostException e) {
            System.out.println("Exception when performSendMessage : "+e); 
        }
    }

    @Override
    public void receiveHello(InetAddress ip, String nickname, boolean reqReply) {
        System.out.println("Hello received from "+nickname+" and reqReply = "+reqReply);
        if (reqReply) {
            ctrlToNI.sendHelloTo(ip, "gautier", false);
        }
    }

    @Override
    public void receiveBye(InetAddress ip) {
        System.out.println("Bye received from "+ip);
    }

    @Override
    public void receiveMessage(InetAddress ip, String message) {
        System.out.println("Message received from "+ip+" : "+message);
    }

    @Override
    public void receiveFile(InetAddress ip, File file) {
        System.out.println("File received !");
    }

}
