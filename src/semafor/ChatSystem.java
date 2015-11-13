/*
 * Semafor - MIT License
 *
 * A peer-to-peer chat system
 *
 * Copyright 2015 Gautier Delorme and Arthur Papailhau.
 *
 */
package semafor;

import controller.*;
import ni.*;
/**
 *
 * @author gautier
 */
public class ChatSystem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ChatController controller = new ChatController();
        ChatNI ntwInterface = ChatNI.buildChatNI();
        
        controller.setCtrlToNI(ntwInterface);
        ntwInterface.setNiToCtrl(controller);
        
        controller.performConnect("gautier");
        controller.performSendMessage("Hello bro", "127.0.0.1");
        
        //ntwInterface.close();
    }
    
}
