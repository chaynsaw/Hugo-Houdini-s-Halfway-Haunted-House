package com.locallampoon.fiveh.ui;

import javax.swing.*;
import java.awt.*;

public class IntroPanel {
    JPanel panel;

    public IntroPanel() {
        this.panel = new JPanel();
        this.panel.setBounds(0, 0, 1000, 800);
        this.panel.setBackground(Color.BLACK);
        this.panel.setForeground(Color.WHITE);
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