package com.locallampoon.fiveh.ui;

import javax.swing.*;
import java.awt.*;

public class IntroTitlePanel {
    private static final String titleText = "Hugo Houdini\'s Halfway Haunted House";
    JPanel panel;
    JLabel title;
    Font font = new Font(
            PanelStyles.Global.fontFamily,
            PanelStyles.Global.fontWeight,
            PanelStyles.Intro.titleFontSize
    );

    public IntroTitlePanel() {
        panel = new JPanel();
        panel.setBounds(
                PanelStyles.Intro.titleX,
                PanelStyles.Intro.titleY,
                PanelStyles.Intro.titleWidth,
                PanelStyles.Intro.titleHeight
        );
        panel.setBackground(PanelStyles.Global.bgColor);
        title = new JLabel(titleText);
        title.setForeground(PanelStyles.Global.fgColor);
        title.setFont(font);
        panel.add(title);
    }

    public JPanel getPanel() {
        return panel;
    }
}