package com.locallampoon.fiveh.ui;

import javax.swing.*;

public class OutroPanel {
    JPanel panel;
    OutroTitlePanel outroTitlePanel;
    OutroOptionsPanel outroOptionsPanel;

    public OutroPanel() {
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

        outroTitlePanel = new OutroTitlePanel();
        outroOptionsPanel = new OutroOptionsPanel();

        this.panel.add(outroTitlePanel.getPanel());
        this.panel.add(outroOptionsPanel.getPanel());

    }

    public JPanel getPanel() {
        return panel;
    }

    public OutroTitlePanel getOutroTitlePanel() {
        return outroTitlePanel;
    }
}
