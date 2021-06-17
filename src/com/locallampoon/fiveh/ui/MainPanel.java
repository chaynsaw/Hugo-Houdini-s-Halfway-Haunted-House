package com.locallampoon.fiveh.ui;

import javax.swing.*;
import java.awt.*;

public class MainPanel {
    JFrame window;
    Container container;
    NarrativePanel narrativePanel;
    ConsolePanel consolePanel;
    ArtPanel artPanel;
    StatsPanel statsPanel;
    MapPanel mapPanel;

    public MainPanel(
            NarrativePanel narrativePanel,
            ConsolePanel consolePanel,
            ArtPanel artPanel,
            StatsPanel statsPanel,
            MapPanel mapPanel
    ) {
        this.narrativePanel = narrativePanel;
        this.consolePanel = consolePanel;
        this.artPanel = artPanel;
        // main window settings
        window = new JFrame();
        window.setSize(1000, 800);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.BLACK);
        window.setLayout(new BorderLayout());
        container = window.getContentPane();
        // add individual panels
        container.add(narrativePanel.getPanel());
        container.add(consolePanel.getPanel());
        container.add(artPanel.getPanel());
        container.add(statsPanel.getPanel());
        container.add(mapPanel.getPanel());
        window.setVisible(true);
    }

    public NarrativePanel getNarrativePanel() {
        return narrativePanel;
    }

    public ArtPanel getArtPanel() {
        return artPanel;
    }
}