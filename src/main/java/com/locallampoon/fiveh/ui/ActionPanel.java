package com.locallampoon.fiveh.ui;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import java.awt.*;

import static com.locallampoon.fiveh.ui.PanelStyles.Global.FG_COLOR;
import static com.locallampoon.fiveh.ui.PanelStyles.Global.FONT_FAMILY;

public class ActionPanel {
    private JScrollPane pane;
    private JTextPane textArea;
    private Font normalFont = new Font(FONT_FAMILY, Font.PLAIN, 18);

    public ActionPanel(){
        textArea = new JTextPane();
        textArea.setFont(normalFont);
        textArea.setBackground(Color.BLACK);
        textArea.setRequestFocusEnabled(false);
        pane = new JScrollPane(
                textArea,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        );
        pane.setBounds(0, 690, 1000, 150);
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
