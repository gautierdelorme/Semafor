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
    private final GeneralTabChatBox generalTab;
    private final HashMap<User, UserTabChatBox> tabsMap;
    private final ArrayList<UserTabChatBox> tabs;

    private final static String generalTabName = "General";

    public ChatBox() {
        generalTab = new GeneralTabChatBox();
        tabsMap = new HashMap<>();
        tabs = new ArrayList<>();
        this.addTab(generalTabName, generalTab);
    }

    public void setChatView(ChatView chatView) {
        this.chatView = chatView;
    }

    public void addUser(User user) {
        generalTab.helloMessage(user);
    }

    public void removeUser(User user) {
        generalTab.byeMessage(user);
        UserTabChatBox tab = tabsMap.get(user);
        if (tab != null) {
            tab.sayBye();
        }
    }

    protected void displayMessage(String message, User user) {
        TabChatBox tab = tabsMap.get(user);
        if (tab == null) {
            tab = addTab(user);
        }
        tab.displayMessage(message, user);
    }

    protected void displayResponse(String message, User user) {
        if (user == null) {
            generalTab.displayMessage(message, chatView.getCurrentUser());
        } else {
            UserTabChatBox tab = tabsMap.get(user);
            tab.displayMessage(message, chatView.getCurrentUser());
        }
    }

    protected TabChatBox addTab(User user) {
        UserTabChatBox tab = new UserTabChatBox(user);
        tabsMap.put(user, tab);
        tabs.add(tab);
        this.addTab(user.getNickname(), tabsMap.get(user));
        this.setSelectedIndex(tabs.size());
        
        TabHeader.buildTabHeader(this, tabs.size());
        return tab;
    }

    protected User getUserFromSelectedTab() {
        if (this.getSelectedIndex() == 0) {
            return null;
        } else {
            return tabs.get(this.getSelectedIndex() - 1).getUser();
        }
    }
    
    protected void removeUserTabAt(int i) {
        tabs.remove(tabsMap.remove(tabs.get(i-1).getUser()));
        this.remove(i);
    }
}
