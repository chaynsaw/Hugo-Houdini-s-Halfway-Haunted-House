package com.locallampoon.fiveh.ui;

import javax.swing.*;

public class IntroPanel {
    JPanel panel;

    public IntroPanel() {
        this.panel = new JPanel();
        this.panel.setBounds(
                PanelStyles.Window.X,
                PanelStyles.Window.Y,
                PanelStyles.Window.WIDTH,
                PanelStyles.Window.HEIGHT
        );
        this.panel.setBackground(PanelStyles.Global.BG_COLOR);
        this.panel.setForeground(PanelStyles.Global.FG_COLOR);
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