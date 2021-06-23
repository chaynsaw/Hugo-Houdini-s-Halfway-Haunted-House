package com.locallampoon.fiveh.ui;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;

public class NarrativePanel {
    private JScrollPane pane;
    private JTextPane textArea;
    private Font normalFont = new Font(PanelStyles.FONT_FAMILY, Font.PLAIN, 18);
    public NarrativePanel() {
        textArea = new JTextPane();
        textArea.setFont(normalFont);
        textArea.setBackground(Color.BLACK);
        pane = new JScrollPane(
                textArea,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        );
        pane.setBounds(0, 500, 1000, 340);
    }

    public JScrollPane getPanel() {
        return this.pane;
    }

    public void setTextArea(String text) {
        System.out.println(text);
        textArea.setText(text);
    }

    /**
     * append text in narrative panel with customized foreground color
     * enable and disable capability to edit before and after append
     * @param text
     * @param color
     */
    public void appendTextArea(String text, Color color)
    {
        this.enableNarrative();
        this.appendToPane(this.textArea, text, color);
        this.disableNarrative();
    }

    public void disableNarrative(){
        textArea.setEditable(false);
    }

    public void enableNarrative(){
        textArea.setEditable(true);
    }

    private void appendToPane(JTextPane textPane, String msg, Color color)
    {
        StyleContext style = StyleContext.getDefaultStyleContext();
        AttributeSet attribute = style.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, color); // only change foreground color
        attribute = style.addAttribute(attribute, StyleConstants.FontFamily, PanelStyles.FONT_FAMILY);
        textPane.setCaretPosition(textPane.getDocument().getLength());
        textPane.setCharacterAttributes(attribute, false);
        textPane.replaceSelection(msg);
    }
}