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
public class TabChatBox extends JPanel {
    
    private final User user;
    private final JList listMessages;
    private final DefaultListModel<String> listMessagesModel;

    public TabChatBox(User user) {
        this.user = user;
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
        listMessagesModel.addElement(rowData); 
    }
    
    protected void helloMessage(User u) {
        String rowData = "<html><em>" + u.getNickname() + "  joined the room.</em></html>";
        listMessagesModel.addElement(rowData);
    }
    
    protected void byeMessage(User u) {
        String rowData = "<html><em>" + u.getNickname() + " left the room.</em></html>";
        listMessagesModel.addElement(rowData);
    }
    
    private void removeBorder(JScrollPane p) {
        p.getViewport().setBorder(null);
        p.setViewportBorder(null);
        p.setBorder(null);
        p.setViewportBorder(null);
    }
    
    public User getUser() {
        return this.user;
    }
}
