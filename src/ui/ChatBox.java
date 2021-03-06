/*
 * Semafor - MIT License
 *
 * A peer-to-peer chat system
 *
 * Copyright 2015 Gautier Delorme and Arthur Papailhau.
 *
 */
package ui;

import java.awt.Color;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.*;
import model.User;

/**
 *
 * @author gautier
 */
class ChatBox extends JTabbedPane {

    private ChatView chatView;
    private final GeneralTabChatBox generalTab;
    private final HashMap<User, UserTabChatBox> tabsMap;
    private final ArrayList<UserTabChatBox> tabs;

    private final static String generalTabName = "General";

    protected ChatBox() {
        generalTab = new GeneralTabChatBox(this);
        tabsMap = new HashMap<>();
        tabs = new ArrayList<>();
        this.addTab(generalTabName, generalTab);
        this.setBackground(new Color(0xecf0f1));
    }

    protected void setChatView(ChatView chatView) {
        this.chatView = chatView;
    }

    protected void addUser(User user) {
        generalTab.helloMessage(user);
    }

    protected void removeUser(User user) {
        generalTab.byeMessage(user);
        UserTabChatBox tab = tabsMap.get(user);
        if (tab != null) {
            tab.sayBye();
        }
    }

    protected void displayMessage(String message, User user) {
        getTab(user).displayMessage(message, user);
    }
    
    protected void displayFile(File file, User user) {
        getTab(user).displayFile(file, user);
    }

    protected void displayResponse(String message, User user) {
        if (user == null) {
            generalTab.displayMessage(message, chatView.getCurrentUser());
        } else {
            UserTabChatBox tab = tabsMap.get(user);
            tab.displayMessage(message, chatView.getCurrentUser());
        }
    }
    
    protected void displayFileRequest(File file, User user) {
        getTab(user).displayFileRequest(file, user);
    }
    
    protected void displayFileResponse(File file, User user) {
        getTab(user).displayFileResponse(file, user);
    }
    
    protected void displaySendFile(File file, User user) {
        if (user == null) {
            generalTab.displaySendFile(file, chatView.getCurrentUser());
        } else {
            UserTabChatBox tab = tabsMap.get(user);
            tab.displaySendFile(file, chatView.getCurrentUser());
        }
    }
    
    protected void sendFileRequestResponse(boolean ok, File file) {
        chatView.sendFileRequestResponse(ok, file);
    }

    protected TabChatBox getTab(User user) {
        UserTabChatBox tab = tabsMap.get(user);
        if (tab == null) {
            tab = new UserTabChatBox(user, this);
            tabsMap.put(user, tab);
            tabs.add(tab);
            this.addTab(user.getNickname(), tabsMap.get(user));
            this.setSelectedIndex(tabs.size());

            TabHeader.buildTabHeader(this, tabs.size());
        }
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
