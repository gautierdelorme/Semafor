/*
 * Semafor - MIT License
 *
 * A peer-to-peer chat system
 *
 * Copyright 2015 Gautier Delorme and Arthur Papailhau.
 *
 */
package ui;

import java.util.HashMap;
import javax.swing.*;
import model.User;

/**
 *
 * @author gautier
 */
public class ChatBox extends JTabbedPane {
    
    private final HashMap<String, TabChatBox> tabs;
    
    public ChatBox() {
        tabs = new HashMap<>();
        tabs.put("General", new TabChatBox());
        this.addTab("General", tabs.get("General"));
    }
    
    protected void displayMessage(String message, User user) {
        tabs.get("General").displayMessage(message, user);
    }
    
    protected void addTab(String nickname) {
        tabs.put(nickname, new TabChatBox());
        this.addTab(nickname, tabs.get(nickname));
    }
}
