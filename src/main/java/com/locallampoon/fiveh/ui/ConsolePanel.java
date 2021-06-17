package com.locallampoon.fiveh.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ConsolePanel implements KeyListener {
    JPanel panel;
    JTextArea textArea;
    Font normalFont = new Font("Arial", Font.PLAIN, 18);

    public ConsolePanel() {
        panel = new JPanel();
        panel.setBounds(0, 730, 1000, 100);
        panel.setBackground(Color.BLUE);
        textArea = new JTextArea();
        textArea.setBounds(0, 0, 540, 200);
        textArea.setFont(normalFont);
        textArea.setBackground(Color.BLACK);
        textArea.setForeground(Color.WHITE);
        textArea.addKeyListener(this);
        panel.add(textArea);
    }

    public JPanel getPanel() {
        return this.panel;
    }

    public JTextArea getTextArea() {
        return textArea;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        // when user presses "Enter" key
        if (e.getKeyCode() == 10) {
            System.out.println(textArea.getText());
            textArea.setText("");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}