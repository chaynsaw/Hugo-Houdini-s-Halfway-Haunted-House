package com.locallampoon.fiveh.ui;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import javax.swing.text.*;
import java.awt.*;

import static com.locallampoon.fiveh.ui.PanelStyles.Global.*;

public class NarrativePanel{
    private JScrollPane pane;
    private JTextPane textArea;
    private Font normalFont = new Font(FONT_FAMILY, Font.PLAIN, 18);
    public NarrativePanel() {
        textArea = new JTextPane();
        textArea.setFont(normalFont);
        textArea.setBackground(Color.BLACK);
        textArea.setRequestFocusEnabled(false);
        pane = new JScrollPane(
                textArea,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        );
        pane.setBorder(new MatteBorder(1, 0, 0, 0, Color.WHITE));
        pane.setBounds(0, 500, 1000, 305);
    }

    public JScrollPane getPanel() {
        return this.pane;
    }

    public void setTextArea(String text) {
        System.out.println(text);
        textArea.setText(text);
    }

    /**
     * append text in narrative panel with fixed foreground color
     * @param text display text
     */
    public void appendTextArea(String text)
    {
        this.appendTextArea(text, FG_COLOR);
    }

    /**
     * append text in narrative panel with customized foreground color
     * enable and disable capability to edit before and after append
     * @param text display text
     * @param color display color
     */
    public void appendTextArea(String text, Color color)
    {
        this.appendTextArea(text, color, false);
    }

    /**
     * append text in narrative panel with customized foreground color
     * enable and disable capability to edit before and after append
     * @param text display text
     * @param color display color
     */
    public void appendTextArea(String text, Color color, boolean tab)
    {
        this.enableNarrativeTextArea();
        this.appendToPane(this.textArea, text, color, tab);
        this.disableNarrativeTextArea();
    }

    public void disableNarrativeTextArea(){
        textArea.setEditable(false);
    }

    public void enableNarrativeTextArea(){
        textArea.setEditable(true);
    }

    /**
     * apply style change on given JTextPane
     * it only changes color in this application
     * @param textPane
     * @param msg
     * @param color
     */
    private void appendToPane(JTextPane textPane, String msg, Color color, boolean tab)
    {
        StyleContext style = StyleContext.getDefaultStyleContext();
        AttributeSet attribute = style.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, color); // only change foreground color
        attribute = style.addAttribute(attribute, StyleConstants.FontFamily, FONT_FAMILY);
        attribute = style.addAttribute(attribute, StyleConstants.Alignment, StyleConstants.ALIGN_LEFT);
        if(tab){
            attribute = style.addAttribute(attribute, StyleConstants.TabSet, new TabSet(new TabStop[] {
                    new TabStop(200f, TabStop.ALIGN_LEFT, TabStop.LEAD_NONE)}));
        }
        textPane.setCaretPosition(textPane.getDocument().getLength());
        textPane.setCharacterAttributes(attribute, false);
        textPane.replaceSelection(msg);
    }
}