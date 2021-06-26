package com.locallampoon.fiveh.ui;

import javax.swing.*;

public class SplashPanel {
    JPanel panel;
    SplashTitlePanel splashTitlePanel;
    SplashOptionsPanel splashOptionsPanel;
    SplashDescriptionPanel splashDescriptionPanel;

    public SplashPanel() {
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

        splashTitlePanel = new SplashTitlePanel();
        splashDescriptionPanel = new SplashDescriptionPanel();
        splashOptionsPanel = new SplashOptionsPanel();

        this.panel.add(splashTitlePanel.getPanel());
        this.panel.add(splashDescriptionPanel.getPanel());
        this.panel.add(splashOptionsPanel.getPanel());
    }

    public JPanel getPanel() {
        return panel;
    }

    public SplashTitlePanel getSplashTitlePanel() {
        return splashTitlePanel;
    }

    public SplashDescriptionPanel getSplashDescriptionPanel() {
        return splashDescriptionPanel;
    }

    public SplashOptionsPanel getSplashOptionsPanel() {
        return splashOptionsPanel;
    }
}
