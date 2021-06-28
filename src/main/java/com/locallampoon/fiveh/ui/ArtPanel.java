package com.locallampoon.fiveh.ui;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;

public class ArtPanel {
    JTextPane pane;
    JTextArea textArea;
    Font normalFont = new Font("Monospaced", Font.PLAIN, 6);

    public ArtPanel() {
        pane = new JTextPane();
        pane.setEnabled(false);
        pane.setBounds(600, 0, 400, 250);
        pane.setBackground(Color.BLACK);
        pane.setBorder(new MatteBorder(0, 1, 0, 0, Color.WHITE));
        textArea = new JTextArea();
        textArea.setBounds(
                PanelStyles.ArtPanel.X,
                PanelStyles.ArtPanel.Y,
                PanelStyles.ArtPanel.WIDTH,
                PanelStyles.ArtPanel.HEIGHT
        );
        textArea.setEnabled(false);
        textArea.setFont(normalFont);
        textArea.setBackground(Color.BLACK);
        textArea.setForeground(Color.WHITE);
        pane.add(textArea);
    }

    public JTextPane getPanel() {
        return this.pane;
    }

    public void setTextArea(String text) {
        System.out.println(text);
        textArea.setText(text);
    }
}