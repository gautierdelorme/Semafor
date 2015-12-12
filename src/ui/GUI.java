/*
 * Semafor - MIT License
 *
 * A peer-to-peer chat system
 *
 * Copyright 2015 Gautier Delorme and Arthur Papailhau.
 *
 */
package ui;

import userview.FromUser;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.util.List;
import javax.swing.JFrame;
import model.User;
import userview.*;

/**
 *
 * @author gautier
 */
public class GUI extends JFrame implements CtrlToUI, FromUser, WindowListener, Runnable {

    private final UIToCtrl uiToCtrl;
    private LoginView loginView;
    private ChatView chatView;

    public GUI(UIToCtrl uiToCtrl) {
        this.uiToCtrl = uiToCtrl;
    }
    
    @Override
    public void run() {
        this.initComponents();
    }

    private void initComponents() {
        this.setTitle("Semafor");
        this.loginView = LoginView.buildLoginView(this);
        this.chatView = ChatView.buildChatView(this);
        this.getContentPane().add(loginView);
        this.pack();
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addWindowListener(this);
    }

    @Override
    public void connect(String nickname) {
        this.loginView = LoginView.buildLoginView(this);
        this.chatView = ChatView.buildChatView(this);
        uiToCtrl.performConnect(nickname);
        this.getContentPane().removeAll();
        this.getContentPane().add(chatView);
        this.pack();
    }

    @Override
    public void disconnect() {
        uiToCtrl.performDisconnect();
        this.getContentPane().removeAll();
        this.getContentPane().add(loginView);
        this.pack();
    }

    @Override
    public void sendFile(File file, List<User> users) {
        uiToCtrl.performSendFile(file, users);
    }

    @Override
    public void sendMessage(String message, List<User> users) {
        uiToCtrl.performSendMessage(message, users);
    }
    
    @Override
    public void sendFileRequestResponse(boolean ok, File file) {
        uiToCtrl.performSendFileRequestResponse(ok, file);
    }

    @Override
    public void displayMessage(String message, User user) {
        chatView.displayMessage(message, user);
    }

    @Override
    public void displayFile(File file, User user) {
        chatView.displayFile(file, user);
    }
    
    @Override
    public void displayFileRequest(File file, User user) {
        chatView.displayFileRequest(file, user);
    }
    
    @Override
    public void displayFileResponse(File file, User user) {
        chatView.displayFileResponse(file, user);
    }

    @Override
    public User getCurrentUser() {
        return uiToCtrl.getCurrentUser();
    }

    @Override
    public void addUser(User user) {
        this.chatView.addUser(user);
    }

    @Override
    public void removeUser(User user) {
        this.chatView.removeUser(user);
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
        this.disconnect();
    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }    
}
