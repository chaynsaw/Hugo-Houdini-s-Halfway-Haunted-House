package com.locallampoon.fiveh.ui;

import javax.swing.*;
import java.awt.*;

public class IntroTitlePanel {
    JPanel panel;
    JLabel title;
    Font font = new Font("Arial", Font.PLAIN, 40);

    public IntroTitlePanel() {
        panel = new JPanel();
        panel.setBounds(100, 100, 780, 150);
        panel.setBackground(Color.BLACK);
        title = new JLabel("Hugo Houdini\'s Halfway Haunted House");
        title.setForeground(Color.WHITE);
        title.setFont(font);
        panel.add(title);
    }

    public JPanel getPanel() {
        return panel;
    }
}