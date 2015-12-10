/*
 * Semafor - MIT License
 *
 * A peer-to-peer chat system
 *
 * Copyright 2015 Gautier Delorme and Arthur Papailhau.
 *
 */
package ui;

import java.text.SimpleDateFormat;
import java.util.Calendar;
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
        String date = new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime());
        String rowData = "<html><em>"+date+"</em> - <em>" + user.getNickname() + " left the room.</em></html>";
        addElementToJList(rowData);
    }
}
