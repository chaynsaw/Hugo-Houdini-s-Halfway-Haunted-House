package com.locallampoon.fiveh.ui;

import com.locallampoon.fiveh.core.Game;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ConsolePanel implements KeyListener {
    JPanel panel;
    JTextArea textArea;
    Font normalFont = new Font(
            PanelStyles.Global.fontFamily,
            PanelStyles.Global.fontWeight,
            PanelStyles.Global.fontSize
    );

    public ConsolePanel() {
        panel = new JPanel();
        panel.setBounds(
                PanelStyles.ConsolePanel.x,
                PanelStyles.ConsolePanel.y,
                PanelStyles.ConsolePanel.width,
                PanelStyles.ConsolePanel.height
        );
        panel.setBackground(PanelStyles.Global.bgColor);
        panel.setBorder(new MatteBorder(0, 1, 0, 1, Color.WHITE));
        textArea = new JTextArea();
        textArea.setBounds(
                PanelStyles.ConsolePanel.txtAreaX,
                PanelStyles.ConsolePanel.txtAreaY,
                PanelStyles.ConsolePanel.txtAreaWidth,
                PanelStyles.ConsolePanel.txtAreaHeight
        );
        textArea.setFont(normalFont);
        textArea.setBackground(PanelStyles.Global.bgColor);
        textArea.setForeground(PanelStyles.Global.fgColor);
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
        disableConsole();
        Game.handleCommand(command);
        clear();
        enableConsole();
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
        int keyCode = e.getKeyCode();
        if (keyCode == Key.ENTER.getKey()) {
            executeCommand(textArea.getText());
        } else if (keyCode == Key.UP.getKey()) {
            executeCommand(Command.GO_NORTH.getText());
        } else if (keyCode == Key.DOWN.getKey()) {
            executeCommand(Command.GO_SOUTH.getText());
        } else if (keyCode == Key.LEFT.getKey()) {
            executeCommand(Command.GO_WEST.getText());
        } else if (keyCode == Key.RIGHT.getKey()) {
            executeCommand(Command.GO_EAST.getText());
        } else if (keyCode == Key.PG_UP.getKey()) {
            executeCommand(Command.GO_UP.getText());
        } else if (keyCode == Key.PG_DOWN.getKey()) {
            executeCommand(Command.GO_DOWN.getText());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == Key.ENTER.getKey()) {
            if (textArea.getCaretPosition() != 0) {
                // reset caret position
                textArea.setCaretPosition(0);
            }
        }
    }
}