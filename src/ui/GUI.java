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
        this.loginView = new LoginView(this);
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
        this.chatView = new ChatView(this);
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
    public void selectUsers() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void selectFile() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void sendMessage(String message) {
        uiToCtrl.performSendMessage(message, "gautier");
    }
    
    @Override
    public void refreshUsersList() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void displayMessage(String message, String nickname) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void displayFile(File file, String nickname) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
