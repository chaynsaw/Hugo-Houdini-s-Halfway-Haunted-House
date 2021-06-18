package com.locallampoon.fiveh.ui;

import com.locallampoon.fiveh.ui.mappanel.GameMapPanel;

import javax.swing.*;
import java.awt.*;

public class MapPanel {
    JPanel panel;

    public MapPanel() {
        panel = new GameMapPanel();
        panel.setEnabled(false);
        panel.setBounds(0, 0, 600, 500);
        panel.setBackground(Color.black);
    }

    public JPanel getPanel() {
        return panel;
    }
}