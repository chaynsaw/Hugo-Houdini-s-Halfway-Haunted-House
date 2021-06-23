package com.locallampoon.fiveh.ui;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;

public class NarrativePanel {
    JScrollPane pane;
    JTextPane textArea;
    Font normalFont = new Font(PanelStyles.FONT_FAMILY, Font.PLAIN, 18);
    public NarrativePanel() {
        textArea = new JTextPane();
        textArea.setFont(normalFont);
        //textArea.setLineWrap(true);
        //textArea.setWrapStyleWord(true);
        textArea.setEnabled(false); // this also disable changing foreground color
        textArea.setBackground(Color.BLACK);
        pane = new JScrollPane(
                textArea,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        );
        pane.setBounds(0, 500, 985, 230);
    }

    public JScrollPane getPanel() {
        return this.pane;
    }

    public void setTextArea(String text) {
        System.out.println(text);
        this.textArea.setText(text);
    }

    /**
     * append text in narrative panel with customized background color
     * JTextPanel.setEnable(false) disable any user input including changing foreground color
     * @param text
     * @param color
     */
    public void appendTextArea(String text, Color color)
    {
        Document doc = textArea.getStyledDocument();

        Style style = textArea.addStyle("", null);
        // setEnable will disable setting foreground color
        // style.addAttribute(StyleConstants.Foreground, Color.RED);
        StyleConstants.setBackground(style, color);

        try {
            doc.insertString(doc.getLength(), text, style);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }
}