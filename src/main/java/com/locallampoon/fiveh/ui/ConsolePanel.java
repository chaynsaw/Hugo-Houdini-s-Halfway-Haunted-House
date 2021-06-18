package com.locallampoon.fiveh.ui;

import com.locallampoon.fiveh.core.Game;

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
        panel.setBounds(0, 730, 1000, 32);
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

    public void setTextArea(String text) {
        textArea.setText(text);
    }

    public void executeCommand(String command) {
        Game.handleCommand(command);
    }

    public void clear() {
        setTextArea("");
    }

    public void enableConsole() {
        textArea.setEnabled(true);
    }

    public void disableConsole() {
        textArea.setEnabled(false);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        // when user presses "Enter" key
        if (e.getKeyCode() == 10) {
            disableConsole();
            executeCommand(textArea.getText());
            clear();
            enableConsole();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == 10) {
            textArea.setCaretPosition(textArea.getCaretPosition() - 1);
        }
    }
}