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
class GeneralTabChatBox extends TabChatBox {
    
    protected GeneralTabChatBox(ChatBox chatBox) {
        super(chatBox);
    }
    
    protected void helloMessage(User u) {
        String date = new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime());
        String rowData = "<html><em>"+date+"</em> - <em>" + u.getNickname() + "  joined the room.</em></html>";
        addElementToJList(rowData);
    }
    
    protected void byeMessage(User u) {
        String date = new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime());
        String rowData = "<html><em>"+date+"</em> - <em>" + u.getNickname() + " left the room.</em></html>";
        addElementToJList(rowData);
    }
}
