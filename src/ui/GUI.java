/*
 * Semafor - MIT License
 *
 * A peer-to-peer chat system
 *
 * Copyright 2015 Gautier Delorme and Arthur Papailhau.
 *
 */
package ui;

import java.io.File;
import javax.swing.JFrame;
import userview.*;
import model.User;
/**
 *
 * @author gautier
 */
public class GUI extends JFrame implements CtrlToUI, FromUser {
    
    private UIToCtrl uiToCtrl;
    private LoginView loginView;
    private ChatView chatView;
    
    public static GUI buildGUI() {
        GUI gui = new GUI();
        gui.initComponents();
        return gui;
    }

    private GUI() {}
    
    private void initComponents() {
        this.setTitle("Semafor");
        this.loginView = LoginView.buildLoginView(this);
        this.chatView = ChatView.buildChatView(this);
        this.getContentPane().add(loginView);
        this.pack();
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void setUiToCtrl(UIToCtrl uiToCtrl) {
        this.uiToCtrl = uiToCtrl;
    }

    @Override
    public void connect(String nickname) {
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
    public void sendFile(File file, User user) {
        uiToCtrl.performSendFile(file, user);
    }

    @Override
    public void sendMessage(String message, User user) {
        uiToCtrl.performSendMessage(message, user);
    }
    
    @Override
    public void refreshUsersList(User[] users) {
        chatView.refreshUsersList(users);
    }

    @Override
    public void displayMessage(String message, User user) {
        chatView.displayMessage(message, user);
    }

    @Override
    public void displayFile(File file, User user) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
