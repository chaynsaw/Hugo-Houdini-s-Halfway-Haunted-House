package com.locallampoon.fiveh.ui;

import com.locallampoon.fiveh.core.Game;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;

public class MainPanel {
    JFrame window;
    Container container;
    SplashPanel splashPanel;
    NarrativePanel narrativePanel;
    ActionPanel actionPanel;
    ConsolePanel consolePanel;
    ArtPanel artPanel;
    StatsPanel statsPanel;
    MapPanel mapPanel;
    Thread splashAnimation;
    SplashImage splashImagePanel = new SplashImage();
    HelpPanel helpPanel;

    public MainPanel(
            SplashPanel splashPanel,
            NarrativePanel narrativePanel,
            ActionPanel actionPanel,
            ConsolePanel consolePanel,
            ArtPanel artPanel,
            StatsPanel statsPanel,
            MapPanel mapPanel,
            HelpPanel helpPanel
    ) {
        this.splashPanel = splashPanel;
        this.narrativePanel = narrativePanel;
        this.actionPanel = actionPanel;
        this.consolePanel = consolePanel;
        this.artPanel = artPanel;
        this.statsPanel = statsPanel;
        this.mapPanel = mapPanel;
        this.helpPanel = helpPanel;
        // main window settings
        window = new JFrame();
        window.setSize(PanelStyles.Window.WIDTH, PanelStyles.Window.HEIGHT);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.BLACK);
        window.getRootPane().setBorder(new MatteBorder(1, 1, 1, 1, Color.WHITE));
        container = window.getContentPane();
        // add individual panels
        container.add(splashImagePanel.getPanel());
        container.add(splashPanel.getPanel());
        splashAnimation = new Thread(splashImagePanel);
        splashAnimation.start();
        hideGame();
        window.setVisible(true);
    }

    public void hideGame() {
        narrativePanel.getPanel().setVisible(false);
        actionPanel.getPanel().setVisible(false);
        consolePanel.getPanel().setVisible(false);
        artPanel.getPanel().setVisible(false);
        statsPanel.getPanel().setVisible(false);
        mapPanel.getPanel().setVisible(false);
    }

    public void showGame() {
        splashImagePanel.cancel();
        splashImagePanel.getPanel().setVisible(false);
        container.add(narrativePanel.getPanel());
        container.add(helpPanel.getPanel());
        container.add(actionPanel.getPanel());
        container.add(consolePanel.getPanel());
        container.add(artPanel.getPanel());
        container.add(statsPanel.getPanel());
        container.add(mapPanel.getPanel());
        narrativePanel.getPanel().setVisible(true);
        helpPanel.getPanel().setVisible(true);
        actionPanel.getPanel().setVisible(true);
        consolePanel.getPanel().setVisible(true);
        consolePanel.enableConsole();
        artPanel.getPanel().setVisible(true);
        statsPanel.getPanel().setVisible(true);
        mapPanel.getPanel().setVisible(true);
    }

    public void hideSplash() {
        splashPanel.getPanel().setVisible(false);
    }

    public void showSplash() {
        window.remove(narrativePanel.getPanel());
        if (Game.hasWon) {
            window.remove(splashImagePanel.getPanel());
            getSplashTitlePanel().renderWinTitle();
        } else {
            getSplashTitlePanel().renderLoseTitle();
        }
        getSplashDescriptionPanel().renderDescription();
        getSplashOptionsPanel().renderOptions();
        splashPanel.getPanel().setVisible(true);
        splashImagePanel.getPanel().setVisible(true);
    }

    public NarrativePanel getNarrativePanel() {
        return narrativePanel;
    }

    public ActionPanel getActionPanel() { return actionPanel; }

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

    public HelpPanel getHelpPanel(){
        return this.helpPanel;
    }

    public SplashImage getSplashImagePanel() {
        return this.splashImagePanel;
    }

    public SplashTitlePanel getSplashTitlePanel(){
        return splashPanel.getSplashTitlePanel();
    }

    public SplashDescriptionPanel getSplashDescriptionPanel(){
        return splashPanel.getSplashDescriptionPanel();
    }

    public SplashOptionsPanel getSplashOptionsPanel(){
        return splashPanel.getSplashOptionsPanel();
    }
}