package com.locallampoon.fiveh.ui;

import com.locallampoon.fiveh.core.Game;

import javax.swing.*;
import java.awt.*;

public class OutroTitlePanel {
    private static final String titleText = "Hugo Houdini\'s Halfway Haunted House";
    private static final String loseText = "YOU DIED. GAME OVER!";
    private static final String winText = "YOU WON?";
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
                PanelStyles.Intro.TITLE_HEIGHT - 100
        );
        panel.setBackground(PanelStyles.Global.BG_COLOR);
        title = new JLabel(titleText);
        title.setForeground(PanelStyles.Global.FG_COLOR);
        title.setFont(font);
        panel.add(title);
    }

    public JPanel getPanel() {
        return panel;
    }

    public void renderTitle() {
        if (Game.hasWon) {
            title.setText(winText);
        } else {
            title.setText(loseText);
        }
    }
}
