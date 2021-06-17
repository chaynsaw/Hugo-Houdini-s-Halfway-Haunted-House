package com.locallampoon.fiveh.ui;

import javax.swing.*;
import java.awt.*;

public class NarrativePanel {
    JScrollPane pane;
    JTextArea textArea;
    Font normalFont = new Font("Arial", Font.PLAIN, 18);

    public NarrativePanel() {
        textArea = new JTextArea();
        textArea.setFont(normalFont);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEnabled(false);
        textArea.setBackground(Color.BLACK);
        textArea.setForeground(Color.WHITE);
        pane = new JScrollPane(
                textArea,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        );
        pane.setBounds(0, 500, 985, 230);
    }

    public JScrollPane getPanel() {
        return this.pane;
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