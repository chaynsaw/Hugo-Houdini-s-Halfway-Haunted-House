package com.locallampoon.fiveh.ui.mappanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.locallampoon.fiveh.ui.PanelStyles.*;

class PlayerAnimation implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    /**
     * it moves player's image from one spot to another
     * this also provides animation frames for actionPerformed()
     * move from one coordinate to another
     * @param mapRoom
     * @param unitSize
     * @throws InterruptedException
     */
    private void moveTo(MapRoom mapRoom, int unitSize) throws InterruptedException {
        if(XPlayer[PLAYER_SIZE-1] < mapRoom.getDx() )
            XPlayer[PLAYER_SIZE-1] += unitSize;
        else if (XPlayer[PLAYER_SIZE-1] > mapRoom.getDx())
            XPlayer[PLAYER_SIZE-1] -= unitSize;
        if(YPlayer[PLAYER_SIZE-1] < mapRoom.getDy() )
            YPlayer[PLAYER_SIZE-1] += unitSize;
        else if (YPlayer[PLAYER_SIZE-1] > mapRoom.getDy())
            YPlayer[PLAYER_SIZE-1] -= unitSize;
    }
}
