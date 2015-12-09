/*
 * Semafor - MIT License
 *
 * A peer-to-peer chat system
 *
 * Copyright 2015 Gautier Delorme and Arthur Papailhau.
 *
 */
package ui;

import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.*;
import model.User;

/**
 *
 * @author gautier
 */
public class ChatBox extends JTabbedPane {
    
    private final HashMap<String, TabChatBox> tabsMap;
    private final ArrayList<TabChatBox> tabs;
    
    private static String generalTabName = "General";
    
    public ChatBox() {
        tabsMap = new HashMap<>();
        tabs = new ArrayList<>();
        TabChatBox generalTabBox = new TabChatBox(null);
        tabsMap.put(generalTabName, generalTabBox);
        tabs.add(generalTabBox);
        this.addTab(generalTabName, generalTabBox);
    }
    
    public void addUser(User user) {
        tabsMap.get(generalTabName).displayMessage("joined the room", user);
    }
    
    protected void displayMessage(String message, User user) {
        tabsMap.get(user.getNickname()).displayMessage(message, user);
    }
    
    protected void addTab(User user) {
        TabChatBox tab = new TabChatBox(user);
        tabsMap.put(user.getNickname(), tab);
        tabs.add(tab);
        this.addTab(user.getNickname(), tabsMap.get(user.getNickname()));
    }
    
    protected User getUserFromSelectedTab() {
        return tabs.get(this.getSelectedIndex()).getUser();
    }
}
