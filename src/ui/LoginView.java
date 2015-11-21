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

/**
 *
 * @author gautier
 */
public class LoginView extends JPanel {

    public LoginView() {
        JLabel iconLabel = new JLabel("Semafor", SwingConstants.CENTER);
        iconLabel.setFont(iconLabel.getFont().deriveFont(128.0f));
        
        JTextField loginInput = new JTextField(10);
        loginInput.setFont(loginInput.getFont().deriveFont(32.0f));
        loginInput.setHorizontalAlignment(SwingConstants.CENTER);
        
        JButton connectButton = new JButton("Connect");
        connectButton.setFont(loginInput.getFont().deriveFont(32.0f));
        connectButton.setPreferredSize(new Dimension(0, 64));
        
        this.setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        
        gbc.insets = new Insets(30,30,0,30);
        gbc.gridy = 0;
        this.add(iconLabel, gbc);
        
        gbc.insets = new Insets(100,100,0,100);
        gbc.gridy = 1;
        this.add(loginInput, gbc);
        
        gbc.insets = new Insets(30,100,100,100);
        gbc.gridy = 2;
        this.add(connectButton, gbc);
    }
    
}
