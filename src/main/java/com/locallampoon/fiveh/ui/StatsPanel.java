package com.locallampoon.fiveh.ui;

import javax.swing.*;
import java.awt.*;

public class StatsPanel {
    JPanel panel;

    public StatsPanel() {
        panel = new JPanel();
        panel.setEnabled(false);
        panel.setBounds(600, 250, 400, 250);
        panel.setBackground(Color.GRAY);
        JTextArea textArea = new JTextArea();
        panel.add(new JScrollPane(textArea));
    }

    public JPanel getPanel() {
        return panel;
    }
}