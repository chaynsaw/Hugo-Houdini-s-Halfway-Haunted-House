package com.locallampoon.fiveh.ui;

import javax.swing.*;
import java.awt.*;

public class IntroTitlePanel {
    private static final String titleText = "Hugo Houdini\'s Halfway Haunted House";
    JPanel panel;
    JLabel title;
    Font font = new Font(
            PanelStyles.Global.FONT_FAMILY,
            PanelStyles.Global.FONT_WEIGHT,
            PanelStyles.Intro.TITLE_FONT_SIZE
    );

    public IntroTitlePanel() {
        panel = new JPanel();
        panel.setBounds(
                PanelStyles.Intro.TITLE_X,
                PanelStyles.Intro.TITLE_Y,
                PanelStyles.Intro.TITLE_WIDTH,
                PanelStyles.Intro.TITLE_HEIGHT
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
}