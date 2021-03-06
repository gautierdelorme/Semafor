/*
 * Semafor - MIT License
 *
 * A peer-to-peer chat system
 *
 * Copyright 2015 Gautier Delorme and Arthur Papailhau.
 *
 */
package ui;

import userview.FromUser;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import javax.swing.*;
import model.User;

/**
 *
 * @author gautier
 */
class ChatView extends JPanel implements ActionListener, MouseListener {

    private final FromUser fromUser;
    private final JList<User> usersList;
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

        usersList = new JList<>(usersListModel);
        usersList.setBackground(new Color(0x3498db));

        DefaultListCellRenderer renderer = (DefaultListCellRenderer) usersList.getCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);

        chatBox = new ChatBox();

        inputBox = CSTextField.buildCSTextField("Enter a message...",50);
        inputBox.setFont(inputBox.getFont().deriveFont(16.0f));
        inputBox.setBackground(new Color(0xecf0f1));
        inputBox.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        inputBox.setBorder(BorderFactory.createCompoundBorder(
                inputBox.getBorder(),
                BorderFactory.createEmptyBorder(0, 10, 0, 0)
        ));

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
        titleLabel.setHorizontalAlignment(SwingConstants.LEFT);
        titleLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        titleLabel.setBorder(BorderFactory.createCompoundBorder(
                titleLabel.getBorder(),
                BorderFactory.createEmptyBorder(0, 90, 0, 0)
        ));

        this.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;

        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        this.add(titleLabel, gbc);

        gbc.gridy = 0;
        gbc.gridx = 2;
        gbc.gridwidth = 1;
        this.add(disconnectButton, gbc);

        gbc.gridy = 1;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 2;
        JScrollPane scrollView = new JScrollPane(usersList);
        scrollView.setPreferredSize(new Dimension(200, 0));
        removeBorder(scrollView);
        this.add(scrollView, gbc);

        gbc.gridy = 1;
        gbc.gridx = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 2;
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
                sendFile(file, chatBox.getUserFromSelectedTab());
            } else {
                System.out.println("Open command cancelled by user.");
            }
        } else if (e.getSource() == inputBox) {
            sendMessage(chatBox.getUserFromSelectedTab());
        }
    }

    protected void addUser(User user) {
        for (int i = 0; i < usersListModel.getSize(); i++) {
            User u = usersListModel.getElementAt(i);
            if (u.getIpAddress().equals(user.getIpAddress())) {
                usersListModel.removeElement(u);
            }
        }
        usersListModel.addElement(user);
        chatBox.addUser(user);
    }

    protected void removeUser(User user) {
        usersListModel.removeElement(user);
        chatBox.removeUser(user);
    }

    protected void displayMessage(String message, User user) {
        chatBox.displayMessage(message, user);
    }
    
    protected void displayFile(File file, User user) {
        chatBox.displayFile(file, user);
    }
    
    protected void displayFileRequest(File file, User user) {
        chatBox.displayFileRequest(file, user);
    }
    
    protected void displayFileResponse(File file, User user) {
        chatBox.displayFileResponse(file, user);
    }

    protected void sendFile(File file, User user) {
        String message = "Asking for send " + file.getName();
        if (user == null) {
            fromUser.sendFile(file, Collections.list(usersListModel.elements()));
        } else {
            fromUser.sendFile(file, Arrays.asList(user));
        }
        chatBox.displaySendFile(file, user);
    }

    protected void sendMessage(User user) {
        if (user == null) {
            fromUser.sendMessage(inputBox.getText(), Collections.list(usersListModel.elements()));
        } else {
            fromUser.sendMessage(inputBox.getText(), Arrays.asList(user));
        }
        chatBox.displayResponse(inputBox.getText(), user);
        inputBox.setText("");
    }
    
    protected void sendFileRequestResponse(boolean ok, File file) {
        fromUser.sendFileRequestResponse(ok, file);
    }

    private void removeBorder(JScrollPane p) {
        p.getViewport().setBorder(null);
        p.setViewportBorder(null);
        p.setBorder(null);
        p.setViewportBorder(null);
    }

    private void addTab(int i) {
        chatBox.getTab(usersListModel.get(i));
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
