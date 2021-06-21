package com.locallampoon.fiveh.ui;

import com.locallampoon.fiveh.core.Game;
import com.locallampoon.fiveh.ui.mappanel.GameMapPanel;

import javax.swing.*;
import java.awt.*;

public class MapPanel {
    GameMapPanel panel;

    public MapPanel() {
        panel = new GameMapPanel();
        panel.setEnabled(false);
        panel.setBounds(0, 0, 600, 500);
        panel.setBackground(Color.black);
    }

    public JPanel getPanel() {
        return panel;
    }

    public void updateMapGUI(){
        this.panel.updateGUI();
    }
}