/*
 * Semafor - MIT License
 *
 * A peer-to-peer chat system
 *
 * Copyright 2015 Gautier Delorme and Arthur Papailhau.
 *
 */
package ui;

import model.User;


/**
 *
 * @author gautier
 */
public class GeneralTabChatBox extends TabChatBox {
    
    public GeneralTabChatBox(ChatBox chatBox) {
        super(chatBox);
    }
    
    protected void helloMessage(User u) {
        String rowData = "<html><em>" + u.getNickname() + "  joined the room.</em></html>";
        addElementToJList(rowData);
    }
    
    protected void byeMessage(User u) {
        String rowData = "<html><em>" + u.getNickname() + " left the room.</em></html>";
        addElementToJList(rowData);
    }
}
