/*
 * Semafor - MIT License
 *
 * A peer-to-peer chat system
 *
 * Copyright 2015 Gautier Delorme and Arthur Papailhau.
 *
 */
package ui;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

/**
 *
 * @author gautier
 */
public class ChatView extends JPanel {
    
    private GUI gui;
    private JList<String> usersList;
    private JTextArea chatBox;
    private JTextField inputBox;
    private CSButton linkButton;
    
    public ChatView(GUI gui) {
        this.gui = gui;
        
        String[] data = {"Arthur", "Bastien", "Pierre", "Henri"};
        usersList = new JList<String>(data);
        usersList.setBackground(new Color(0x3498db));
        usersList.setPreferredSize(new Dimension(100, 0));
        DefaultListCellRenderer renderer =  (DefaultListCellRenderer)usersList.getCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);

        chatBox = new JTextArea(20,0);
        chatBox.setFont(chatBox.getFont().deriveFont(16.0f));
        chatBox.setEditable(false);
        chatBox.setBorder(javax.swing.BorderFactory.createEmptyBorder());

        inputBox = new JTextField(30);
        inputBox.setFont(inputBox.getFont().deriveFont(16.0f));
        inputBox.setBackground(new Color(0xecf0f1));
        inputBox.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        
        linkButton = new CSButton(FontAwesome.Icon.PAPERCLIP.s());
        linkButton.setFont(FontAwesome.fontAwesome.deriveFont(20.0f));
        linkButton.setBackground(inputBox.getBackground());
        linkButton.setForeground(new Color(0x3498db));
        
        this.setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.gridheight = 2;
        this.add(usersList, gbc);
        
        gbc.gridy = 0;
        gbc.gridx = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 2;
        this.add(new JScrollPane(chatBox), gbc);
        
        gbc.gridy = 1;
        gbc.gridx = 1;
        gbc.gridwidth = 1;
        this.add(inputBox, gbc);
        
        gbc.gridy = 1;
        gbc.gridx = 2;
        this.add(linkButton, gbc);
    }
}
