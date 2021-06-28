package com.locallampoon.fiveh.ui;

import javax.swing.*;
import java.awt.*;

public class SplashTitlePanel {
    private static final String titleText = "Hugo Houdini\'s Halfway Haunted House";
    private static final String loseText = "YOU DIED. GAME OVER!";
    private static final String winText = "YOU WON?";
    JPanel panel;
    JLabel title;
    Font font = new Font(
            PanelStyles.Global.FONT_FAMILY,
            PanelStyles.Global.FONT_WEIGHT,
            PanelStyles.Splash.TITLE_FONT_SIZE
    );

    public SplashTitlePanel() {
        panel = new JPanel();
        panel.setBounds(
                PanelStyles.Splash.TITLE_X,
                PanelStyles.Splash.TITLE_Y,
                PanelStyles.Splash.TITLE_WIDTH,
                PanelStyles.Splash.TITLE_HEIGHT
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

    public void renderWinTitle() {
        title.setText(winText);
    }

    public void renderLoseTitle() {
        title.setText(loseText);
    }
}
