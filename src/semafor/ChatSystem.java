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
import ui.GUI;
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
        
        GUI gui = new GUI();
        gui.setUiToCtrl(controller);
        
        //controller.performConnect("gautier");
        //controller.performSendMessage("Hello bro", "10.32.0.133");
        
        //ntwInterface.close();
    }
    
}
