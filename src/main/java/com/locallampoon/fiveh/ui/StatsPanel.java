package com.locallampoon.fiveh.ui;

import javax.swing.*;
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
        textArea.setBackground(Color.BLACK);
        textArea.setForeground(Color.GRAY);
        textArea.setFont(normalFont);
        panel.add(textArea);
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setTextArea(String text) {
        System.out.println(text);
        textArea.setText(text);
    }

    public void appendTextArea(String text) {
        System.out.println(text);
        textArea.append(text + "\n");
    }
}