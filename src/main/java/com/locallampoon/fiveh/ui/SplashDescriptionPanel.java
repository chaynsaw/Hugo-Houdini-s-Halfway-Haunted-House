package com.locallampoon.fiveh.ui;

import com.locallampoon.fiveh.core.Game;

import javax.swing.*;
import java.awt.*;

public class SplashDescriptionPanel {
    private static final String winText = """
                    \tA passageway is opened to you and it glows a warm blue, like the keys in your pack, brighter and brighter as you approach. Its warmth lifts the spirits and fills your wearied mind with hope... but each time you pass through it - nothing. You transport to the same place. It matters not how many times you cross.
                    \tThe realization that dawns upon you is as clear as it is maddening and final. There is no end. There is no beginning. There is no escape. This is a prison.
                    """;
    JPanel panel;
    JTextArea description;
    Font font = new Font(
            PanelStyles.Global.FONT_FAMILY,
            PanelStyles.Global.FONT_WEIGHT,
            18
    );

    public SplashDescriptionPanel() {
        panel = new JPanel();
        panel.setBounds(
                PanelStyles.Intro.TITLE_X,
                PanelStyles.Intro.TITLE_Y + 100,
                PanelStyles.Intro.TITLE_WIDTH,
                PanelStyles.Intro.TITLE_HEIGHT + 150
        );
        panel.setBackground(PanelStyles.Global.BG_COLOR);
        description = new JTextArea();
        description.setEnabled(false);
        description.setWrapStyleWord(true);
        description.setBounds(
                PanelStyles.Intro.TITLE_X,
                PanelStyles.Intro.TITLE_Y,
                PanelStyles.Intro.TITLE_WIDTH,
                PanelStyles.Intro.TITLE_HEIGHT + 150
        );
        description.setBackground(PanelStyles.Global.BG_COLOR);
        description.setForeground(PanelStyles.Global.FG_COLOR);
        description.setFont(font);
        description.setLineWrap(true);
        panel.add(description);
    }

    public JPanel getPanel() {
        return panel;
    }

    public void renderDescription() {
        if (Game.hasWon) {
            description.setText(winText);
        } else {
            description.setText("");
        }
    }
}