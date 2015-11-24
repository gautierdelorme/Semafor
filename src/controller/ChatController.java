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
        ctrlToDatabase.setCurrentUser(nickname);
        ctrlToNI.sendHello(nickname, true);
    }

    @Override
    public void performDisconnect() {
        ctrlToDatabase.removeCurrentUser();
        ctrlToNI.sendBye();
    }
    
    @Override
    public void performSendFile(File file, User[] users) {
        for (User user : users) {
            ctrlToNI.sendFile(file, user.getIpAddress());
        }
    }

    @Override
    public void performSendMessage(String message, User[] users) {
        for (User user : users) {
            ctrlToNI.sendMessage(message, user.getIpAddress());
        }
    }

    @Override
    public void receiveHello(InetAddress ip, String nickname, boolean reqReply) {
        System.out.println("Hello received from "+nickname+" and reqReply = "+reqReply);
        if (reqReply) {
            ctrlToNI.sendHelloTo(ip, ctrlToDatabase.getCurrentUser().getNickname(), false);
        }
        ctrlToDatabase.addUser(ip, nickname);
        ctrlToUI.refreshUsersList(ctrlToDatabase.getUsers());
    }

    @Override
    public void receiveBye(InetAddress ip) {
        System.out.println("Bye received from "+ip);
        ctrlToDatabase.deleteUser(ip);
        ctrlToUI.refreshUsersList(ctrlToDatabase.getUsers());
    }

    @Override
    public void receiveMessage(InetAddress ip, String message) {
        System.out.println("Message received from "+ip+" : "+message);
        ctrlToUI.displayMessage(message, ctrlToDatabase.getUserWithIP(ip));
    }

    @Override
    public void receiveFile(InetAddress ip, File file) {
        System.out.println("File received !");
    }
}
