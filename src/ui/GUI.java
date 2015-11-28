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
import java.util.List;
import javax.swing.JFrame;
import model.User;
import userview.*;

/**
 *
 * @author gautier
 */
public class GUI extends JFrame implements CtrlToUI, FromUser {
    
    private UIToCtrl uiToCtrl;
    private LoginView loginView;
    private ChatView chatView;
    
    public static GUI buildGUI(UIToCtrl uiToCtrl) {
        GUI gui = new GUI();
        gui.uiToCtrl = uiToCtrl;
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
    public void sendFile(File file, List<User> users) {
        uiToCtrl.performSendFile(file, users);
    }

    @Override
    public void sendMessage(String message, List<User> users) {
        uiToCtrl.performSendMessage(message, users);
    }

    @Override
    public void displayMessage(String message, User user) {
        chatView.displayMessage(message, user);
    }

    @Override
    public void displayFile(File file, User user) {
        throw new UnsupportedOperationException("Not supported yet.");
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
}
