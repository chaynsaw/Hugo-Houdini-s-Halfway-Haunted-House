package com.locallampoon.fiveh.ui;

import com.locallampoon.fiveh.core.Game;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ConsolePanel implements KeyListener {
    public static final int ENTER_KEY = 10;
    JPanel panel;
    JTextArea textArea;
    Font normalFont = new Font(
            PanelStyles.FONT_FAMILY,
            PanelStyles.FONT_WEIGHT,
            PanelStyles.FONT_SIZE
    );

    public ConsolePanel() {
        panel = new JPanel();
        panel.setBounds(
                PanelStyles.CONSOLE_PANEL_X,
                PanelStyles.CONSOLE_PANEL_Y,
                PanelStyles.CONSOLE_PANEL_WIDTH,
                PanelStyles.CONSOLE_PANEL_HEIGHT
        );
        panel.setBackground(PanelStyles.BG_COLOR);
        panel.setBorder(new MatteBorder(0, 1, 0, 1, Color.WHITE));
        textArea = new JTextArea();
        textArea.setBounds(
                PanelStyles.CONSOLE_TXT_AREA_X,
                PanelStyles.CONSOLE_TXT_AREA_Y,
                PanelStyles.CONSOLE_TXT_AREA_WIDTH,
                PanelStyles.CONSOLE_TXT_AREA_HEIGHT
        );
        textArea.setFont(normalFont);
        textArea.setBackground(PanelStyles.BG_COLOR);
        textArea.setForeground(PanelStyles.FG_COLOR);
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
        if (e.getKeyCode() == ENTER_KEY) {
            disableConsole();
            executeCommand(textArea.getText());
            clear();
            enableConsole();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == ENTER_KEY) {
            // reset caret position
            textArea.setCaretPosition(textArea.getCaretPosition() - 1);
        }
    }
}