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
import model.*;
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
        Database database = Database.buildDatabase();
        
        controller.setCtrlToNI(ntwInterface);
        controller.setCtrlToDatabase(database);
        ntwInterface.setNiToCtrl(controller);
        
        //GUI gui = GUI.buildGUI();
        //gui.setUiToCtrl(controller);
        
        controller.performConnect("Arthur");
        controller.performSendMessage("Hello bro", "127.0.0.1");
        
        //ntwInterface.close();
    }
    
}
