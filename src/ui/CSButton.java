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
import javax.swing.JButton;

/**
 *
 * @author gautier
 */
class CSButton extends JButton {

    protected CSButton(String text) {
        super(text);
        this.setForeground(Color.WHITE);
        this.setBackground(new Color(0x3498db));
        this.setOpaque(true);
        this.setBorderPainted(false);
    }
}
