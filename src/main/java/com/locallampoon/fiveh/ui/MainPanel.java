package com.locallampoon.fiveh.ui;

import javax.swing.*;
import java.awt.*;

public class MainPanel {
    JFrame window;
    Container container;
    IntroPanel introPanel;
    NarrativePanel narrativePanel;
    ConsolePanel consolePanel;
    ArtPanel artPanel;
    StatsPanel statsPanel;
    MapPanel mapPanel;

    public MainPanel(
            IntroPanel introPanel,
            NarrativePanel narrativePanel,
            ConsolePanel consolePanel,
            ArtPanel artPanel,
            StatsPanel statsPanel,
            MapPanel mapPanel
    ) {
        this.introPanel = introPanel;
        this.narrativePanel = narrativePanel;
        this.consolePanel = consolePanel;
        this.artPanel = artPanel;
        this.statsPanel = statsPanel;
        this.mapPanel = mapPanel;
        // main window settings
        window = new JFrame();
        window.setSize(1000, 800);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.BLACK);
        window.setLayout(new BorderLayout());
        container = window.getContentPane();
        // add individual panels
        container.add(introPanel.getPanel());
        container.add(narrativePanel.getPanel());
        container.add(consolePanel.getPanel());
        container.add(artPanel.getPanel());
        container.add(statsPanel.getPanel());
        container.add(mapPanel.getPanel());
        window.setVisible(true);
    }

    public void hideIntro() {
        introPanel.getPanel().setVisible(false);
    }

    public NarrativePanel getNarrativePanel() {
        return narrativePanel;
    }

    public ArtPanel getArtPanel() {
        return artPanel;
    }

    public StatsPanel getStatsPanel() {
        return statsPanel;
    }

    public ConsolePanel getConsolePanel() {
        return consolePanel;
    }

    public MapPanel getMapPanel(){
        return this.mapPanel;
    }
}