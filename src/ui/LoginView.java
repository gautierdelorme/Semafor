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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author gautier
 */
public class LoginView extends JPanel implements ActionListener {
    
    private final FromUser fromUser;
    private final JLabel iconLabel;
    //private CustomTextField loginInput;
    private final JTextField loginInput;
    private final CSButton connectButton;
    private final JLabel errorLabel;
    
    protected static LoginView buildLoginView(FromUser fromUser) {
        LoginView loginView = new LoginView(fromUser);
        loginView.connectButton.addActionListener(loginView);
        return loginView;
    }

    private LoginView(FromUser fromUser) {
        this.fromUser = fromUser;
        
        iconLabel = new JLabel(FontAwesome.Icon.COMMENTS_ALT.s(), SwingConstants.CENTER);
        iconLabel.setFont(FontAwesome.fontAwesome.deriveFont(128.0f));
        iconLabel.setForeground(new Color(0x3498db));
        
        loginInput = new JTextField(10);
        //loginInput = new CustomTextField(10);
        loginInput.setFont(loginInput.getFont().deriveFont(32.0f));
        loginInput.setHorizontalAlignment(SwingConstants.CENTER);
        //loginInput.setPlaceholder("Pseudo");
        
        
        connectButton = new CSButton("Connect");
        connectButton.setFont(loginInput.getFont().deriveFont(32.0f));
        connectButton.setPreferredSize(new Dimension(0, 45));
        
        errorLabel = new JLabel("Only letters and numbers are allowed !", SwingConstants.CENTER);
        errorLabel.setFont(errorLabel.getFont().deriveFont(12.0f));
        errorLabel.setForeground(new Color(0xe74c3c));
        errorLabel.setVisible(false);
        
        this.setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        
        gbc.insets = new Insets(10,10,0,10);
        gbc.gridy = 0;
        this.add(iconLabel, gbc);
        
        gbc.insets = new Insets(30,30,0,30);
        gbc.gridy = 1;
        this.add(loginInput, gbc);
        
        gbc.insets = new Insets(10,30,10,30);
        gbc.gridy = 2;
        this.add(connectButton, gbc);
        
        gbc.insets = new Insets(0,30,30,30);
        gbc.gridy = 3;
        this.add(errorLabel, gbc);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        errorLabel.setVisible(false);
        if (e.getSource() == connectButton && loginInput.getText().length() > 0 && loginInput.getText().matches("[a-zA-Z0-9]+")) {
            fromUser.connect(loginInput.getText());
        } else {
            errorLabel.setVisible(true);
        }
    }
    
}
