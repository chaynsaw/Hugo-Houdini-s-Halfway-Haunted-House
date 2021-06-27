package com.locallampoon.fiveh.ui;

import com.locallampoon.fiveh.core.GameArt;

import javax.swing.*;
import java.awt.*;

public class SplashImage {
    JTextArea textArea;
    Font normalFont = new Font("Monospaced", Font.PLAIN, 14);

    public SplashImage() {
        textArea = new JTextArea();
        textArea.setBounds(
                PanelStyles.TitleSplashImage.X,
                PanelStyles.TitleSplashImage.Y,
                PanelStyles.TitleSplashImage.WIDTH,
                PanelStyles.TitleSplashImage.HEIGHT
        );
        textArea.setEnabled(false);
        textArea.setBackground(Color.BLACK);
        textArea.setForeground(Color.WHITE);
        textArea.setFont(normalFont);
        textArea.setText(GameArt.renderHouse());
    }

    public JTextArea getPanel() {
        return this.textArea;
    }

    public void setTextArea(String text) {
        System.out.println(text);
        textArea.setText(text);
    }
}
