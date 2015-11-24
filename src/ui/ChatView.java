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
import java.util.stream.Stream;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import model.User;
/**
 *
 * @author gautier
 */
public class ChatView extends JPanel implements ActionListener, ListSelectionListener {

    private final FromUser fromUser;
    private final JList usersList;
    private final DefaultListModel<User> usersListModel;
    private final JTable chatBox;
    private final DefaultTableModel chatBoxModel;
    private final JTextField inputBox;
    private final CSButton linkButton;
    private final CSButton disconnectButton;
    private final JLabel titleLabel;
    private final JFileChooser fc;
    private User selectedUser;

    public ChatView(FromUser fromUser) {
        this.fromUser = fromUser;
        
        fc = new JFileChooser();
        usersListModel = new DefaultListModel<>();
        chatBoxModel = new DefaultTableModel();
        chatBoxModel.addColumn("ChatBox");

        usersList = new JList(usersListModel);
        usersList.addListSelectionListener(this);
        usersList.setBackground(new Color(0x3498db));
        usersList.setPreferredSize(new Dimension(100, 0));
        
        DefaultListCellRenderer renderer = (DefaultListCellRenderer) usersList.getCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);

        chatBox = new JTable(chatBoxModel);
        chatBox.setFont(chatBox.getFont().deriveFont(12.0f));

        inputBox = new JTextField(30);
        inputBox.setFont(inputBox.getFont().deriveFont(16.0f));
        inputBox.setBackground(new Color(0xecf0f1));
        inputBox.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        inputBox.addActionListener(this);

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
                fromUser.sendFile(file, null);
            } else {
                System.out.println("Open command cancelled by user.");
            }
        } else if (e.getSource() == inputBox) {
            if (selectedUser != null) {
                sendMessage();
            }
        }
    }
    
    public void refreshUsersList(User[] users) {
        usersListModel.removeAllElements();
        Stream.of(users).forEach(user -> usersListModel.addElement(user));
    }
    
    public void displayMessage(String message, User user) {
        String[] rowData = {"<html><b>"+user.getNickname()+" : </b>"+message+"</html>"};
        chatBoxModel.addRow(rowData);
    }
    
    public void sendMessage() {
        fromUser.sendMessage(inputBox.getText(), selectedUser);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            selectedUser = usersListModel.get(e.getFirstIndex());
            System.out.println("u : "+selectedUser.getNickname());
        }
    }
}
