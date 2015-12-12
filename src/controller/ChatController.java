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

    /**
     * Set relationships with the NetworkInterface, UserInterface and the Model
     * 
     * @param ctrlToNI interface between the controller and the NetworkInterface
     * @param ctrlToDatabase interface between the controller and the Model
     * @param ctrlToUI interface between the controller and the UserInterface
     */
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
        ctrlToDatabase.removeUserList();
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
    public void performSendFileRequestResponse(boolean ok, File file) {
        ctrlToNI.sendFileRequestResponse(ok, file);
    }

    @Override
    public void receiveHello(InetAddress ip, String nickname, boolean reqReply) {
        if (ctrlToDatabase.getCurrentUser() != null) {
            if (reqReply) {
                ctrlToNI.sendHelloTo(ip, ctrlToDatabase.getCurrentUser().getNickname(), false);
            }
            ctrlToUI.addUser(ctrlToDatabase.addUser(ip, nickname));
        }
    }

    @Override
    public void receiveBye(InetAddress ip) {
        if (ctrlToDatabase.getCurrentUser() != null) {
            if (ctrlToDatabase.getUserWithIP(ip) != null) {
                ctrlToUI.removeUser(ctrlToDatabase.deleteUser(ip));
            }
        }
    }

    @Override
    public void receiveMessage(InetAddress ip, String message) {
        if (ctrlToDatabase.getCurrentUser() != null) {
            ctrlToUI.displayMessage(message, ctrlToDatabase.getUserWithIP(ip));
        }
    }

    @Override
    public void receiveFile(InetAddress ip, File file) {
        if (ctrlToDatabase.getCurrentUser() != null) {
            ctrlToUI.displayFile(file, ctrlToDatabase.getUserWithIP(ip));
        }
    }

    @Override
    public void receiveFileRequest(InetAddress ip, File file) {
        if (ctrlToDatabase.getCurrentUser() != null) {
            ctrlToUI.displayFileRequest(file, ctrlToDatabase.getUserWithIP(ip));
        }
    }

    @Override
    public void receiveFileResponse(InetAddress ip, File file) {
        if (ctrlToDatabase.getCurrentUser() != null) {
            ctrlToUI.displayFileResponse(file, ctrlToDatabase.getUserWithIP(ip));
        }
    }

    @Override
    public User getCurrentUser() {
        return ctrlToDatabase.getCurrentUser();
    }
}
