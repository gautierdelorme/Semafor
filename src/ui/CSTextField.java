/*
 * Semafor - MIT License
 *
 * A peer-to-peer chat system
 *
 * Copyright 2015 Gautier Delorme and Arthur Papailhau.
 *
 */
package ui;

import java.awt.Color;
import javax.swing.*;
import java.awt.event.*;
/**
 *
 * @author gautier
 */
class CSTextField extends JTextField implements FocusListener, KeyListener {
    
    private final String placeholder;
    
    protected static CSTextField buildCSTextField(String placeholder, int columns) {
        CSTextField csTextField = new CSTextField(placeholder, columns);
        csTextField.addFocusListener(csTextField);
        csTextField.addKeyListener(csTextField);
        return csTextField;
    }

    private CSTextField(String placeholder, int columns) {
        super(columns);
        this.placeholder = placeholder;
        this.setText(placeholder);
        this.setForeground(Color.GRAY);
    }    
    
    @Override
    public void focusGained(FocusEvent e) {
        if (this.getText().equals(placeholder)) {
            this.setCaretPosition(0);
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (this.getText().equals(placeholder)) {
            this.setText("");
            this.setForeground(Color.BLACK);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (this.getText().equals("")) {
            this.setText(placeholder);
            this.setForeground(Color.GRAY);
            this.setCaretPosition(0);
        }
    }
    
}
