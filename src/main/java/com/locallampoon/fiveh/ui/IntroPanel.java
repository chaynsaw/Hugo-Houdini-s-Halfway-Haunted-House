package com.locallampoon.fiveh.ui;

import javax.swing.*;

public class IntroPanel {
    JPanel panel;

    public IntroPanel() {
        this.panel = new JPanel();
        this.panel.setBounds(
                PanelStyles.Window.x,
                PanelStyles.Window.y,
                PanelStyles.Window.width,
                PanelStyles.Window.height
        );
        this.panel.setBackground(PanelStyles.BG_COLOR);
        this.panel.setForeground(PanelStyles.FG_COLOR);
        this.panel.setLayout(null);

        IntroTitlePanel introTitlePanel = new IntroTitlePanel();
        IntroOptionsPanel introOptionsPanel = new IntroOptionsPanel();

        this.panel.add(introTitlePanel.getPanel());
        this.panel.add(introOptionsPanel.getPanel());
    }

    public JPanel getPanel() {
        return panel;
    }
}