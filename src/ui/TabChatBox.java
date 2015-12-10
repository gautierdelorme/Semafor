/*
 * Semafor - MIT License
 *
 * A peer-to-peer chat system
 *
 * Copyright 2015 Gautier Delorme and Arthur Papailhau.
 *
 */
package ui;

import javax.swing.*;
import java.awt.*;
import model.User;

/**
 *
 * @author gautier
 */
public abstract class TabChatBox extends JPanel {
    
    
    private JList listMessages;
    private DefaultListModel<String> listMessagesModel;
    
    protected void initComponents() {
        this.setOpaque(false);
        
        listMessagesModel = new DefaultListModel<>();
        listMessages = new JList(listMessagesModel);
        listMessages.setFont(listMessages.getFont().deriveFont(12.0f));
        
        this.setLayout(new BorderLayout());
        JScrollPane scrollView = new JScrollPane(listMessages);
        removeBorder(scrollView);
        
        this.add(scrollView);
    }
    
    protected void displayMessage(String message, User user) {
        String rowData = "<html><b>"+user.getNickname()+" : </b>"+message+"</html>";
        addElementToJList(rowData); 
    }
        
    private void removeBorder(JScrollPane p) {
        p.getViewport().setBorder(null);
        p.setViewportBorder(null);
        p.setBorder(null);
        p.setViewportBorder(null);
    }
    
    protected void addElementToJList(String s) {
        listMessagesModel.addElement(s);
    }
}
