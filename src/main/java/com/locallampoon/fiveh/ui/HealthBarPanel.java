package com.locallampoon.fiveh.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class HealthBarPanel {
    JPanel panel;
    JLabel label;
    JProgressBar healthBar;
    Font normalFont = new Font(
            PanelStyles.Global.FONT_FAMILY,
            PanelStyles.Global.FONT_WEIGHT,
            PanelStyles.StatsPanel.FONT_SIZE
    );

    HealthBarPanel(String labelText, int defaultHealth, int maxHealth) {
        panel = new JPanel();
        panel.setBackground(PanelStyles.Global.BG_COLOR);
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        label = new JLabel(labelText);
        label.setForeground(PanelStyles.Global.FG_COLOR);
        label.setFont(normalFont);
        panel.add(label);
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);
        healthBar = new JProgressBar(0, maxHealth);
        healthBar.setBorder(new EmptyBorder(0,0,0,0));
        healthBar.setBorderPainted(false);
        healthBar.setBackground(Color.LIGHT_GRAY); // health lost
        healthBar.setForeground(PanelStyles.GameMap.PLAYER_COLOR); // current health
        healthBar.setValue(defaultHealth);
        panel.add(healthBar);
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setHealthBar(int value) {
        healthBar.setValue(value);
    }

    public void setVisible(boolean bool) {
        panel.setVisible(bool);
    }
}