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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.swing.*;
import model.User;

/**
 *
 * @author gautier
 */
public class ChatView extends JPanel implements ActionListener, MouseListener {

    private final FromUser fromUser;
    private final JList usersList;
    private final DefaultListModel<User> usersListModel;
    private final ChatBox chatBox;
    private final JTextField inputBox;
    private final CSButton linkButton;
    private final CSButton disconnectButton;
    private final JLabel titleLabel;
    private final JFileChooser fc;

    protected static ChatView buildChatView(FromUser fromUser) {
        ChatView chatView = new ChatView(fromUser);
        chatView.inputBox.addActionListener(chatView);
        chatView.linkButton.addActionListener(chatView);
        chatView.disconnectButton.addActionListener(chatView);
        chatView.usersList.addMouseListener(chatView);
        chatView.chatBox.setChatView(chatView);
        return chatView;
    }

    private ChatView(FromUser fromUser) {
        this.fromUser = fromUser;

        fc = new JFileChooser();
        usersListModel = new DefaultListModel<>();

        usersList = new JList(usersListModel);
        usersList.setBackground(new Color(0x3498db));
        usersList.setPreferredSize(new Dimension(100, 0));
        usersList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        DefaultListCellRenderer renderer = (DefaultListCellRenderer) usersList.getCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);

        chatBox = new ChatBox();
        chatBox.setPreferredSize(new Dimension(0, 400));

        inputBox = new JTextField(30);
        inputBox.setFont(inputBox.getFont().deriveFont(16.0f));
        inputBox.setBackground(new Color(0xecf0f1));
        inputBox.setBorder(javax.swing.BorderFactory.createEmptyBorder());

        linkButton = new CSButton(FontAwesome.Icon.PAPERCLIP.s());
        linkButton.setFont(FontAwesome.fontAwesome.deriveFont(20.0f));
        linkButton.setBackground(inputBox.getBackground());
        linkButton.setForeground(new Color(0x3498db));

        disconnectButton = new CSButton(FontAwesome.Icon.POWER_OFF.s());
        disconnectButton.setFont(FontAwesome.fontAwesome.deriveFont(20.0f));
        disconnectButton.setBackground(new Color(0x3498db));
        disconnectButton.setForeground(new Color(0xecf0f1));
        disconnectButton.setHorizontalAlignment(SwingConstants.RIGHT);

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
        JScrollPane scrollView = new JScrollPane(usersList);
        removeBorder(scrollView);
        this.add(scrollView, gbc);

        gbc.gridy = 1;
        gbc.gridx = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 2;
        /*scrollView = new JScrollPane(chatBox);
         removeBorder(scrollView);
         scrollView.setPreferredSize(new Dimension(0, 400));*/

        this.add(chatBox, gbc);

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
                /*if (selectedUsers.size() > 0) {
                 sendFile(file);
                 fromUser.sendFile(file, selectedUsers);
                 }*/
            } else {
                System.out.println("Open command cancelled by user.");
            }
        } else if (e.getSource() == inputBox) {
            sendMessage(chatBox.getUserFromSelectedTab());
        }
    }

    public void addUser(User user) {
        for (int i = 0; i < usersListModel.getSize(); i++) {
            User u = usersListModel.getElementAt(i);
            if (u.getIpAddress().equals(user.getIpAddress())) {
                usersListModel.removeElement(u);
            }
        }
        usersListModel.addElement(user);
        chatBox.addUser(user);
    }

    public void removeUser(User user) {
        usersListModel.removeElement(user);
        chatBox.removeUser(user);
    }

    protected void displayMessage(String message, User user) {
        chatBox.displayMessage(message, user);
    }

    protected void sendFile(File file) {
        /*String message = "Do you accept " + file.getName() + " ?";
         String usernames = selectedUsers.get(0).getNickname();
         usernames = selectedUsers.stream().skip(1).map((selectedUser) -> ", " + selectedUser.getNickname()).reduce(usernames, String::concat);
         String rowData = "<html><b>" + fromUser.getCurrentUser().getNickname() + " -> " + usernames + " : </b>" + message + "</html>";
         fromUser.sendMessage(message, selectedUsers);*/
    }

    protected void sendMessage(User user) {
        if (user == null)
            fromUser.sendMessage(inputBox.getText(), Collections.list(usersListModel.elements()));
        else
            fromUser.sendMessage(inputBox.getText(), Arrays.asList(user));
        chatBox.displayResponse(inputBox.getText(), user);
        inputBox.setText("");
    }

    private void removeBorder(JScrollPane p) {
        p.getViewport().setBorder(null);
        p.setViewportBorder(null);
        p.setBorder(null);
        p.setViewportBorder(null);
    }

    private void addTab(int i) {
        chatBox.addTab(usersListModel.get(i));
    }
    
    protected User getCurrentUser() {
        return fromUser.getCurrentUser();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JList list = (JList) e.getSource();
        if (e.getClickCount() == 2) {
            int index = list.locationToIndex(e.getPoint());
            addTab(index);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
