package com.locallampoon.fiveh.ui;

import com.locallampoon.fiveh.core.Game;
import com.locallampoon.fiveh.core.Player;

import javax.swing.*;
import java.awt.*;

public class OutroTitlePanel {
    private static final String looseText = "GAME OVER";
    private static final String winText = "YOU WON";
    Player player;
    JPanel panel;
    JLabel title;
    Font font = new Font(
            PanelStyles.Global.FONT_FAMILY,
            PanelStyles.Global.FONT_WEIGHT,
            PanelStyles.Intro.TITLE_FONT_SIZE
    );

    public OutroTitlePanel() {
        panel = new JPanel();
        panel.setBounds(
                PanelStyles.Intro.TITLE_X,
                PanelStyles.Intro.TITLE_Y,
                PanelStyles.Intro.TITLE_WIDTH,
                PanelStyles.Intro.TITLE_HEIGHT
        );
        panel.setBackground(PanelStyles.Global.BG_COLOR);
        if (Game.hasWon) {
            title = new JLabel(winText);
        } else {
            title = new JLabel(looseText);
        }
        title.setForeground(PanelStyles.Global.FG_COLOR);
        title.setFont(font);
        panel.add(title);
    }

    public JPanel getPanel() {
        return panel;
    }
}
