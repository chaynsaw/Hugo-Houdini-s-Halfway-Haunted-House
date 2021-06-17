package com.locallampoon.fiveh.ui;

import javax.swing.*;
import java.awt.*;

public class MapPanel {
    JPanel panel;

    public MapPanel() {
        panel = new JPanel();
        panel.setEnabled(false);
        panel.setBounds(0, 0, 600, 500);
        panel.setBackground(Color.GREEN);
    }

    public JPanel getPanel() {
        return panel;
    }
}