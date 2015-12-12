/*
 * Semafor - MIT License
 *
 * A peer-to-peer chat system
 *
 * Copyright 2015 Gautier Delorme and Arthur Papailhau.
 *
 */
package semafor;

import controller.ChatController;
import javax.swing.SwingUtilities;
import model.Database;
import ni.ChatNI;
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
        Database database = new Database();
        ChatNI ntwInterface = ChatNI.buildChatNI(controller);
        GUI gui = new GUI(controller);
        controller.setDependencies(ntwInterface, database, gui);
        SwingUtilities.invokeLater(gui);
    }
    
}
