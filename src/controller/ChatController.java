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
import model.*;
import netview.*;
import userview.*;

/**
 *
 * @author gautier
 */
public class ChatController implements NIToCtrl, UIToCtrl {
    
    private CtrlToNI ctrlToNI;
    private CtrlToUI ctrlToUI;
    private CtrlToDatabase ctrlToDatabase;

    public void setCtrlToNI(CtrlToNI ctrlToNI) {
        this.ctrlToNI = ctrlToNI;
    }
    
    public void setCtrlToUI(CtrlToUI ctrlToUI) {
        this.ctrlToUI = ctrlToUI;
    }
    
    public void setCtrlToDatabase(CtrlToDatabase ctrlToDatabase){
        this.ctrlToDatabase = ctrlToDatabase;
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
    public void performSendFile(File file, String ip) {
        try {
            ctrlToNI.sendFile(file, InetAddress.getByName(ip));
        } catch (UnknownHostException e) {
            System.out.println("Exception when performSendMessage : "+e); 
        }
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
        ctrlToDatabase.addUser(ip, nickname);
        ctrlToUI.refreshUsersList(ctrlToDatabase.getNicknames());
    }

    @Override
    public void receiveBye(InetAddress ip) {
        ctrlToDatabase.deleteUser(ip);
        ctrlToUI.refreshUsersList(ctrlToDatabase.getNicknames());
        System.out.println("Bye received from "+ip);
    }

    @Override
    public void receiveMessage(InetAddress ip, String message) {
        System.out.println("Message received from "+ip+" : "+message);
        ctrlToUI.displayMessage(message, "gautch");
    }

    @Override
    public void receiveFile(InetAddress ip, File file) {
        System.out.println("File received !");
    }
}
