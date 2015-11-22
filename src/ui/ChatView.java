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
import java.io.File;
import javax.swing.*;

/**
 *
 * @author gautier
 */
public class ChatView extends JPanel implements ActionListener {

    private final FromUser fromUser;
    private final JList usersList;
    private final DefaultListModel usersListModel;
    private final JTextArea chatBox;
    private final JTextField inputBox;
    private final CSButton linkButton;
    private final CSButton disconnectButton;
    private final JLabel titleLabel;
    private final JFileChooser fc;

    public ChatView(FromUser fromUser) {
        this.fromUser = fromUser;
        
        fc = new JFileChooser();
        usersListModel = new DefaultListModel();

        String[] dataFake = {"Arthur", "Bastien", "Pierre", "Henri"};
        usersList = new JList(usersListModel);
        usersList.setBackground(new Color(0x3498db));
        usersList.setPreferredSize(new Dimension(100, 0));
        DefaultListCellRenderer renderer = (DefaultListCellRenderer) usersList.getCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);

        chatBox = new JTextArea(20, 0);
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
        linkButton.addActionListener(this);

        disconnectButton = new CSButton(FontAwesome.Icon.POWER_OFF.s());
        disconnectButton.setFont(FontAwesome.fontAwesome.deriveFont(20.0f));
        disconnectButton.setBackground(new Color(0x3498db));
        disconnectButton.setForeground(new Color(0xecf0f1));
        disconnectButton.setHorizontalAlignment(SwingConstants.RIGHT);
        disconnectButton.addActionListener(this);

        titleLabel = new JLabel(FontAwesome.Icon.COMMENTS_ALT.s());
        titleLabel.setFont(FontAwesome.fontAwesome.deriveFont(20.0f));
        titleLabel.setBackground(new Color(0x3498db));
        titleLabel.setForeground(new Color(0xecf0f1));
        titleLabel.setOpaque(true);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        this.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;

        gbc.gridy = 0;
        gbc.gridx = 0;
        this.add(titleLabel, gbc);

        gbc.gridy = 0;
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        this.add(disconnectButton, gbc);

        gbc.gridy = 1;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 2;
        this.add(usersList, gbc);

        gbc.gridy = 1;
        gbc.gridx = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 2;
        this.add(new JScrollPane(chatBox), gbc);

        gbc.gridy = 2;
        gbc.gridx = 1;
        gbc.gridwidth = 1;
        this.add(inputBox, gbc);

        gbc.gridy = 2;
        gbc.gridx = 2;
        this.add(linkButton, gbc);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == disconnectButton) {
            fromUser.disconnect();
        } else if (e.getSource() == linkButton) {
            int returnVal = fc.showOpenDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                fromUser.selectFile(file);
            } else {
                System.out.println("Open command cancelled by user.");
            }
        }
    }
    
    public void refreshUsersList(String[] nicknames) {
        usersListModel.removeAllElements();
        for(String name : nicknames){
            usersListModel.addElement(name);
        }
    }
}
