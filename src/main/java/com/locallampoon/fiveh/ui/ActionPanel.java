package com.locallampoon.fiveh.ui;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import java.awt.*;

import static com.locallampoon.fiveh.ui.PanelStyles.Global.*;

public class ActionPanel {
    private JScrollPane pane;
    private JTextPane textArea;
    private Font normalFont = new Font(FONT_FAMILY,FONT_WEIGHT,FONT_SIZE);

    public ActionPanel(){
        textArea = new JTextPane();
        textArea.setFont(normalFont);
        textArea.setBackground(BG_COLOR);
        textArea.setRequestFocusEnabled(false);
        pane = new JScrollPane(
                textArea,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        );
        pane.setBounds(PanelStyles.ActionPanel.X,
                PanelStyles.ActionPanel.Y,
                PanelStyles.ActionPanel.WIDTH,
                PanelStyles.ActionPanel.HEIGHT);
        pane.setBorder(new MatteBorder(0, 0, 1, 0, Color.WHITE));
    }

    public JScrollPane getPanel() {
        return this.pane;
    }

    public void setTextArea(String text) {
        System.out.println(text);
        textArea.setText(text);
    }

    public void appendTextArea(String text, Color color)
    {
        this.enableNarrativeTextArea();
        this.appendToPane(this.textArea, text, color);
        this.disableNarrativeTextArea();
    }

    public void appendTextArea(String text)
    {
        this.appendTextArea(text, FG_COLOR);
    }

    public void disableNarrativeTextArea(){
        textArea.setEditable(false);
    }

    public void enableNarrativeTextArea(){
        textArea.setEditable(true);
    }

    private void appendToPane(JTextPane textPane, String msg, Color color)
    {
        StyleContext style = StyleContext.getDefaultStyleContext();
        AttributeSet attribute = style.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, color); // only change foreground color
        attribute = style.addAttribute(attribute, StyleConstants.FontFamily, FONT_FAMILY);
        textPane.setCaretPosition(textPane.getDocument().getLength());
        textPane.setCharacterAttributes(attribute, false);
        textPane.replaceSelection(msg);
    }
}
