package com.locallampoon.fiveh.ui;

import com.locallampoon.fiveh.core.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class SplashOptionsPanel implements KeyListener {
    private final String bullet = "> ";
    int selectedIndex = 0;
    JPanel panel;
    List<JLabel> optionLabels = new ArrayList<>();
    Font font = new Font(
            PanelStyles.Global.FONT_FAMILY,
            PanelStyles.Global.FONT_WEIGHT,
            PanelStyles.Splash.OPTIONS_FONT_SIZE
    );

    public SplashOptionsPanel() {
        panel = new JPanel(new GridLayout(0, 1));
        panel.setBounds(
                PanelStyles.Splash.OPTIONS_X,
                PanelStyles.Splash.OPTIONS_Y + 100,
                PanelStyles.Splash.OPTIONS_WIDTH,
                PanelStyles.Splash.OPTIONS_HEIGHT
        );
        panel.setBackground(PanelStyles.Global.BG_COLOR);
        panel.setFocusable(true);
        panel.addKeyListener(this);

        for (SplashOption option : SplashOption.values()) {
            JLabel label = new JLabel(option.toString(), SwingConstants.CENTER);
            setLabelStyle(label);
            optionLabels.add(label);
            panel.add(label);
        }
        setSelected();
    }

    private JLabel getSelectedLabel() {
        return optionLabels.get(selectedIndex);
    }

    private void setSelected() {
        getSelectedLabel().setText(bullet + getSelectedLabel().getText());
    }

    private void removeSelected() {
        getSelectedLabel().setText(getSelectedLabel().getText().replace(bullet, ""));
    }

    private void setLabelStyle(JLabel label) {
        if (label != null) {
            label.setBackground(Color.BLACK);
            label.setForeground(Color.WHITE);
            label.setFont(font);
        }
    }

    private void traverseOption(int num) {
        removeSelected();
        selectedIndex += num;
        setSelected();
    }

    public JPanel getPanel() {
        return panel;
    }

    public void renderOptions() {
        if (Game.hasWon || Game.hasLost) {
            optionLabels.get(0).setVisible(false);
            optionLabels.remove(0);
            setSelected();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == Key.DOWN.getKey()) {
            // right bounds check
            if (selectedIndex + 1 <= optionLabels.size() - 1) {
                traverseOption(1);
            }
        }
        if (e.getKeyCode() == Key.UP.getKey()) {
            // left bounds check
            if (selectedIndex - 1 >= 0) {
                traverseOption(-1);
            }
        }
        if (e.getKeyCode() == Key.ENTER.getKey()) {
            String selectedOption = optionLabels.get(selectedIndex).getText();
            SplashOption option = SplashOption.valueOf(selectedOption.replace(bullet, ""));
            Game.handleIntro(option);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
