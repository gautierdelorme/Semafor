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
    
    private ChatView chatView;
    private final HashMap<String, TabChatBox> tabsMap;
    private final ArrayList<TabChatBox> tabs;
    
    private final static String generalTabName = "General";
    
    public ChatBox() {
        tabsMap = new HashMap<>();
        tabs = new ArrayList<>();
        TabChatBox generalTabBox = new TabChatBox(null);
        tabsMap.put(generalTabName, generalTabBox);
        tabs.add(generalTabBox);
        this.addTab(generalTabName, generalTabBox);
    }
    
    public void setChatView(ChatView chatView) {
        this.chatView = chatView;
    }
    
    public void addUser(User user) {
        tabsMap.get(generalTabName).helloMessage(user);
    }
    
    public void removeUser(User user) {
        tabsMap.get(generalTabName).byeMessage(user);
        TabChatBox tab = tabsMap.get(user.getNickname());
        if (tab != null)
          tab.byeMessage(user);
    }
    
    protected void displayMessage(String message, User user) {
        TabChatBox tab = tabsMap.get(user.getNickname());
        if (tab == null) {
            tab = addTab(user);
        }
        tab.displayMessage(message, user);
    }
    
    protected void displayResponse(String message, User user) {
        if (user == null) {
            tabsMap.get(generalTabName).displayMessage(message, chatView.getCurrentUser());
        } else {
            TabChatBox tab = tabsMap.get(user.getNickname());
            tab.displayMessage(message, chatView.getCurrentUser());
        }
    }
    
    protected TabChatBox addTab(User user) {
        TabChatBox tab = new TabChatBox(user);
        tabsMap.put(user.getNickname(), tab);
        tabs.add(tab);
        this.addTab(user.getNickname(), tabsMap.get(user.getNickname()));
        return tab;
    }
    
    protected User getUserFromSelectedTab() {
        return tabs.get(this.getSelectedIndex()).getUser();
    }
}
