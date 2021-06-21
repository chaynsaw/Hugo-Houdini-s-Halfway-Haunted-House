package com.locallampoon.fiveh.ui.mappanel;
import javax.swing.event.MouseInputListener;
import java.awt.event.MouseEvent;

class GameMapMouseListener implements MouseInputListener {
    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.printf("clicked at location:%s\n", e.getLocationOnScreen());
        System.out.println("Which room clicked? Do you want to move to that room?");
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
