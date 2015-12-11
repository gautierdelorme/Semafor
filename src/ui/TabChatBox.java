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
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    private HashMap<Integer, File> filesToReceive;
    private HashMap<Integer, File> filesToOpen;

    protected TabChatBox(ChatBox chatBox) {
        this.chatBox = chatBox;
        filesToReceive = new HashMap<>();
        filesToOpen = new HashMap<>();
        initComponents();
    }

    private void initComponents() {
        this.setLayout(new BorderLayout());

        listMessagesModel = new DefaultTableModel();
        listMessagesModel.addColumn("");
        listMessagesModel.addColumn("");

        listMessages = new JTable(listMessagesModel) {
            @Override
            public boolean isCellEditable(int row, int column) {                
                return column==1;               
            }
        };
        listMessages.setFont(listMessages.getFont().deriveFont(12.0f));
        listMessages.setBackground(new Color(0xecf0f1));
        listMessages.setGridColor(listMessages.getBackground());
        listMessages.setTableHeader(null);
        listMessages.setRowHeight(30);
        
        setWidthAsPercentages(listMessages, 0.85, 0.15);

        JScrollPane scrollView = new JScrollPane(listMessages);
        scrollView.getViewport().setBackground(listMessages.getBackground());
        removeBorder(scrollView);

        this.add(scrollView);
    }

    protected void displayMessage(String message, User user) {
        String date = new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime());
        String rowData = "<html><em>"+date+"</em> - <b>" + user.getNickname() + " : </b>" + message + "</html>";
        addElementToJList(rowData);
    }
    
    protected void displayFile(File file, User user) {
        String date = new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime());
        String[] rowData = {"<html><em>"+date+"</em> - <b><em>" + file.getName() + "</em> downloaded !</b>", "Open"};
        filesToOpen.put(addElementToJList(rowData), file);
    }

    protected void displayFileRequest(File file, User user) {
        String date = new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime());
        String rowData[] = {"<html><em>"+date+"</em> - <b>" + user.getNickname() + " sent you <em>" + file.getName() + "</em></b>", "Download"};
        filesToReceive.put(addElementToJList(rowData), file);
    }
    
    protected void displayFileResponse(File file, User user) {
        String date = new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime());
        String rowData = "<html><em>"+date+"</em> - <b>" + user.getNickname() + " started to download <em>" + file.getName() + "</em></b>";
        addElementToJList(rowData);
    }
    
    protected void displaySendFile(File file, User user) {
        String date = new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime());
        String rowData = "<html><em>"+date+"</em> - <b>"+user.getNickname()+" sent <em>" + file.getName() + "</em></b>";
        addElementToJList(rowData);
    }

    private void removeBorder(JScrollPane p) {
        p.getViewport().setBorder(null);
        p.setViewportBorder(BorderFactory.createEmptyBorder());
        p.setBorder(BorderFactory.createEmptyBorder());
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
        if (filesToOpen.get(modelRow) != null) {
            try {
                Desktop.getDesktop().open(filesToOpen.get(modelRow));
            } catch (IOException ex) {
                System.out.println("Error when open file : "+ex);
            }
        } else {
            this.chatBox.sendFileRequestResponse(true, filesToReceive.get(modelRow));
            filesToReceive.remove(modelRow);
        }
    }
}
