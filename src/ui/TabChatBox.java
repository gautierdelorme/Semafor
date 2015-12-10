/*
 * Semafor - MIT License
 *
 * A peer-to-peer chat system
 *
 * Copyright 2015 Gautier Delorme and Arthur Papailhau.
 *
 */
package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;
import javax.swing.table.*;
import model.User;

/**
 *
 * @author gautier
 */
public abstract class TabChatBox extends JPanel implements ActionListener {

    private ChatBox chatBox;

    private JTable listMessages;
    private DefaultTableModel listMessagesModel;

    private HashMap<Integer, File> files;

    protected TabChatBox(ChatBox chatBox) {
        this.chatBox = chatBox;
        files = new HashMap<>();
        initComponents();
    }

    private void initComponents() {
        this.setLayout(new BorderLayout());

        listMessagesModel = new DefaultTableModel();
        listMessagesModel.addColumn("");
        listMessagesModel.addColumn("");

        listMessages = new JTable(listMessagesModel);
        listMessages.setFont(listMessages.getFont().deriveFont(12.0f));

        setWidthAsPercentages(listMessages, 0.9, 0.1);

        JScrollPane scrollView = new JScrollPane(listMessages);
        removeBorder(scrollView);

        this.add(scrollView);
    }

    protected void displayMessage(String message, User user) {
        String rowData = "<html><b>" + user.getNickname() + " : </b>" + message + "</html>";
        addElementToJList(rowData);
    }

    protected void displayFileRequest(File file, User user) {
        String rowData[] = {"<html><b>" + user.getNickname() + " : </b> Do you accept <em>" + file.getName() + "</em> ?", "Accept"};
        files.put(addElementToJList(rowData), file);
    }

    private void removeBorder(JScrollPane p) {
        p.getViewport().setBorder(null);
        p.setViewportBorder(null);
        p.setBorder(null);
        p.setViewportBorder(null);
    }

    protected void addElementToJList(String s) {
        String[] ss = {s};
        listMessagesModel.addRow(ss);
    }

    protected Integer addElementToJList(String[] s) {
        listMessagesModel.addRow(s);
        ButtonColumn buttonColumn = new ButtonColumn(listMessages, this, 1);
        return listMessagesModel.getRowCount() - 1;
    }

    private static void setWidthAsPercentages(JTable table, double... percentages) {
        final double factor = 10000;
        TableColumnModel model = table.getColumnModel();
        for (int columnIndex = 0; columnIndex < percentages.length; columnIndex++) {
            TableColumn column = model.getColumn(columnIndex);
            column.setPreferredWidth((int) (percentages[columnIndex] * factor));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JTable table = (JTable) e.getSource();
        int modelRow = Integer.valueOf(e.getActionCommand());
        this.chatBox.sendFileRequestResponse(true, files.get(modelRow));
        files.remove(modelRow);
        table.getSelectionModel().clearSelection();
    }
}
