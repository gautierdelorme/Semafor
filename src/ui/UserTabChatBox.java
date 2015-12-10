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
public class UserTabChatBox extends TabChatBox {
    private final User user;
    
    public UserTabChatBox(User user, ChatBox chatBox) {
        super(chatBox);
        this.user = user;
    }
    
    public User getUser() {
        return this.user;
    }
    
    public void sayBye() {
        String rowData = "<html><em>" + user.getNickname() + " left the room.</em></html>";
        addElementToJList(rowData);
    }
}
