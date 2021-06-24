package com.locallampoon.fiveh.ui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class StatsPanel {
    JPanel panel;
    JTextArea textArea = new JTextArea();
    Font normalFont = new Font(
            PanelStyles.Global.FONT_FAMILY,
            PanelStyles.Global.FONT_WEIGHT,
            PanelStyles.StatsPanel.FONT_SIZE
    );
    HealthBarPanel playerHealthPanel;
    HealthBarPanel monsterHealthPanel;

    public StatsPanel() {
        panel = new JPanel();
        panel.setEnabled(false);
        panel.setBounds(
                PanelStyles.StatsPanel.X,
                PanelStyles.StatsPanel.Y,
                PanelStyles.StatsPanel.WIDTH,
                PanelStyles.StatsPanel.HEIGHT
        );
        panel.setBackground(PanelStyles.Global.BG_COLOR);
        panel.setBorder(new LineBorder(Color.WHITE));
        textArea.setEnabled(false);
        textArea.setBackground(PanelStyles.Global.BG_COLOR);
        textArea.setForeground(PanelStyles.Global.FG_COLOR);
        textArea.setFont(normalFont);
        textArea.setBounds(
                PanelStyles.StatsPanel.TXT_AREA_X,
                PanelStyles.StatsPanel.TXT_AREA_Y,
                PanelStyles.StatsPanel.TXT_AREA_WIDTH,
                PanelStyles.StatsPanel.TXT_AREA_HEIGHT
        );
        textArea.setLineWrap(true);
        playerHealthPanel = new HealthBarPanel("PLAYER HEALTH:     ", 20, 20);
        monsterHealthPanel = new HealthBarPanel("MONSTER HEALTH: ", 0, 6);
        monsterHealthPanel.setVisible(false);
        panel.add(playerHealthPanel.getPanel());
        panel.add(monsterHealthPanel.getPanel());
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

    public HealthBarPanel getPlayerHealthPanel() {
        return this.playerHealthPanel;
    }

    public HealthBarPanel getMonsterHealthPanel() {
        return this.monsterHealthPanel;
    }
}