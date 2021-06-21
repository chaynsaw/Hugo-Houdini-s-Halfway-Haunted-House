package com.locallampoon.fiveh.ui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class StatsPanel {
    JPanel panel;
    JTextArea textArea = new JTextArea();
    Font normalFont = new Font("Arial", Font.PLAIN, 16);

    public StatsPanel() {
        panel = new JPanel();
        panel.setEnabled(false);
        panel.setBounds(600, 250, 400, 250);
        panel.setBackground(Color.BLACK);
        panel.setLayout(null);
        panel.setBorder(new LineBorder(Color.WHITE));
        textArea.setEnabled(false);
        textArea.setBackground(Color.BLACK);
        textArea.setForeground(Color.GRAY);
        textArea.setFont(normalFont);
        textArea.setBounds(20, 10, 300, 210);
        textArea.setLineWrap(true);
        panel.add(textArea);
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setTextArea(String text) {
        textArea.setText(text);
    }

    public void appendTextArea(String text) {
        textArea.append(text + "\n");
    }
}