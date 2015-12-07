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
import java.util.List;
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
    
    public void setDependencies(CtrlToNI ctrlToNI, CtrlToDatabase ctrlToDatabase, CtrlToUI ctrlToUI) {
        this.ctrlToNI = ctrlToNI;
        this.ctrlToDatabase = ctrlToDatabase;
        this.ctrlToUI = ctrlToUI;
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
    public void performSendFile(File file, List<User> users) {
        for (User user : users) {
            ctrlToNI.sendFile(file, user.getIpAddress());
        }
    }

    @Override
    public void performSendMessage(String message, List<User> users) {
        for (User user : users) {
            ctrlToNI.sendMessage(message, user.getIpAddress());
        }
    }

    @Override
    public void receiveHello(InetAddress ip, String nickname, boolean reqReply) {
        if (ctrlToDatabase.getCurrentUser() != null) {
            if (reqReply) {
                System.out.println("REQREPLY : "+nickname);
                ctrlToNI.sendHelloTo(ip, ctrlToDatabase.getCurrentUser().getNickname(), false);
            } else {
                System.out.println("NOREQREPLY : "+nickname);
            }
            if (ctrlToDatabase.canAddUser(ip, nickname)) {
                ctrlToUI.addUser(ctrlToDatabase.addUser(ip, nickname));
            }
        }
    }

    @Override
    public void receiveBye(InetAddress ip) {
        ctrlToUI.removeUser(ctrlToDatabase.deleteUser(ip));
    }

    @Override
    public void receiveMessage(InetAddress ip, String message) {
        ctrlToUI.displayMessage(message, ctrlToDatabase.getUserWithIP(ip));
    }

    @Override
    public void receiveFile(InetAddress ip, File file) {
        ctrlToUI.displayFile(file, ctrlToDatabase.getUserWithIP(ip));
    }

    @Override
    public User getCurrentUser() {
        return ctrlToDatabase.getCurrentUser();
    }
}
