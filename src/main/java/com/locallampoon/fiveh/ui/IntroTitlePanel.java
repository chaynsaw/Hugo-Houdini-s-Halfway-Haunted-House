package com.locallampoon.fiveh.ui;

import javax.swing.*;
import java.awt.*;

public class IntroTitlePanel {
    private static final String titleText = "Hugo Houdini\'s Halfway Haunted House";
    JPanel panel;
    JLabel title;
    Font font = new Font(
            PanelStyles.FONT_FAMILY,
            PanelStyles.FONT_WEIGHT,
            PanelStyles.INTRO_TITLE_FONT_SIZE
    );

    public IntroTitlePanel() {
        panel = new JPanel();
        panel.setBounds(
                PanelStyles.INTRO_TITLE_X,
                PanelStyles.INTRO_TITLE_Y,
                PanelStyles.INTRO_TITLE_WIDTH,
                PanelStyles.INTRO_TITLE_HEIGHT
        );
        panel.setBackground(PanelStyles.BG_COLOR);
        title = new JLabel(titleText);
        title.setForeground(PanelStyles.FG_COLOR);
        title.setFont(font);
        panel.add(title);
    }

    public JPanel getPanel() {
        return panel;
    }
}