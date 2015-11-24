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
import java.net.InetAddress;
import java.net.UnknownHostException;
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
        
        /*
        * TO DO :
        * - Handle when JSON dont have good formatting
        * - Handle file (cf commun file in POO directory on GDrive)
        * - Handle when pseudo already exist
        *
        * /!\ TO TEST IN LOCAL RETURN TRUE IN UDPRECEIVER FILTER
        * /!\ ACTUALLY THE LOCAL MODE IS ON !!
        */
        ChatController controller = new ChatController();
        
        ChatNI ntwInterface = ChatNI.buildChatNI();
        ntwInterface.setNiToCtrl(controller);
        
        GUI gui = GUI.buildGUI();
        gui.setUiToCtrl(controller);
        
        Database database = new Database();
        
        controller.setCtrlToNI(ntwInterface);
        controller.setCtrlToDatabase(database);
        controller.setCtrlToUI(gui);
        
        //Followed lines are only here to test the userlist refresh (works fine!)
        
        gui.connect("Gautier");
        
        try {
            controller.receiveHello(InetAddress.getByName("127.0.0.1"), "Gautch", false);
            controller.receiveHello(InetAddress.getByName("127.0.0.2"), "Amandine", false);
            controller.receiveHello(InetAddress.getByName("127.0.0.3"), "Laure", false);
            controller.receiveHello(InetAddress.getByName("127.0.0.4"), "Pierre", false);
            controller.receiveHello(InetAddress.getByName("127.0.0.5"), "Henri", false);
            controller.receiveHello(InetAddress.getByName("127.0.0.6"), "Bastien", false);
            controller.receiveHello(InetAddress.getByName("127.0.0.6"), "Arthur", false);
            controller.receiveMessage(InetAddress.getByName("127.0.0.3"), "test");
            //controller.receiveBye(InetAddress.getByName("127.0.0.1"));
        } catch (UnknownHostException ex) {
            System.out.println("er");
        }
    }
    
}
