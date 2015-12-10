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
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author gautier
 */
public class TabHeader extends JPanel implements ActionListener {

    private final ChatBox pane;
    private final JButton buttonClose;

    public static TabHeader buildTabHeader(ChatBox pane, int index) {
        TabHeader tabHeader = new TabHeader(pane, index);
        tabHeader.pane.setTabComponentAt(index, tabHeader);
        tabHeader.buttonClose.addActionListener(tabHeader);
        return tabHeader;
    }

    private TabHeader(ChatBox pane, int index) {
        this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        this.pane = pane;
        setOpaque(false);
        add(new JLabel(
                pane.getTitleAt(index),
                pane.getIconAt(index),
                JLabel.LEFT));
        Icon closeIcon = new CloseIcon();
        buttonClose = new JButton(closeIcon);
        buttonClose.setPreferredSize(new Dimension(
        closeIcon.getIconWidth(), closeIcon.getIconHeight()));
        buttonClose.setOpaque(false);
        buttonClose.setContentAreaFilled(false);
        buttonClose.setBorderPainted(false);
        add(buttonClose);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int i = pane.indexOfTabComponent(this);
        if (i != -1) {
            pane.removeUserTabAt(i);
        }
    }
}

class CloseIcon implements Icon {
  @Override
  public void paintIcon(Component c, Graphics g, int x, int y) {
    g.setColor(Color.BLACK);
    g.drawLine(6, 6, getIconWidth() - 7, getIconHeight() - 7);
    g.drawLine(getIconWidth() - 7, 6, 6, getIconHeight() - 7);
  }
  @Override
  public int getIconWidth() {
    return 20;
  }
  @Override
  public int getIconHeight() {
    return 20;
  }
}
