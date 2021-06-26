package com.locallampoon.fiveh.ui;

import com.locallampoon.fiveh.core.Game;

import javax.swing.*;
import java.awt.*;

public class SplashDescriptionPanel {
    private static final String winText = """
                    A passageway is opened to you and it glows a warm blue, like the keys in your pack, brighter and brighter as you approach. Its warmth lifts the spirits and fills your wearied mind with hope... but each time you pass through it - nothing happens. You transport back to the other side of the room. It matters not how many times you cross.
                    
                    The realization that dawns upon you is as clear as it is maddening and final. 
                    
                    There is no end. There is no beginning. There is no escape. This is a prison.
                    """;
    JPanel panel;
    JTextArea description;
    Font font = new Font(
            PanelStyles.Global.FONT_FAMILY,
            PanelStyles.Global.FONT_WEIGHT,
            PanelStyles.Splash.DESCRIPTION_FONT_SIZE
    );

    public SplashDescriptionPanel() {
        panel = new JPanel();
        panel.setBounds(
                PanelStyles.Splash.DESCRIPTION_X,
                PanelStyles.Splash.DESCRIPTION_Y,
                PanelStyles.Splash.DESCRIPTION_WIDTH,
                PanelStyles.Splash.DESCRIPTION_HEIGHT
        );
        panel.setBackground(PanelStyles.Global.BG_COLOR);
        description = new JTextArea();
        description.setEnabled(false);
        description.setWrapStyleWord(true);
        description.setBounds(
                PanelStyles.Splash.DESCRIPTION_X,
                PanelStyles.Splash.DESCRIPTION_Y - 100,
                PanelStyles.Splash.DESCRIPTION_WIDTH,
                PanelStyles.Splash.DESCRIPTION_HEIGHT
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