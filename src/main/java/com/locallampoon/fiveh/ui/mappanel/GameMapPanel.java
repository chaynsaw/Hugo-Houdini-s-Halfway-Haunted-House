package com.locallampoon.fiveh.ui.mappanel; /**
 * Game map image oldmap.jpg from https://www.123rf.com/photo_147314303_old-vintage-map-with-handmade-drawings-for-treasure-games-and-fantasy-stories.html
 * Player image palyer.png from https://pngtree.com/freepng/cartoon-animal-game-character-design_4069832.html
 */

import com.locallampoon.fiveh.core.Game;
import com.locallampoon.fiveh.core.Player;
import com.locallampoon.fiveh.core.Room;
import com.locallampoon.fiveh.ui.PanelStyles;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.List;
import java.util.Map;

import static com.locallampoon.fiveh.ui.PanelStyles.*;

public class GameMapPanel extends JPanel {
    GameMap gameMap = GameMap.getInstance();

    public GameMapPanel() {
        this.setPreferredSize(new Dimension(PanelStyles.Map.panelWidth, PanelStyles.Map.panelHeight));
        this.setBackground(Color.BLACK);
        this.setBorder(new LineBorder(Color.WHITE));
        this.setOpaque(false); // prevent other panels' text being covered during repaint()
        gameMap.setPlayer(Game.getPlayer()); // pass a reference to game map
    }

    /**
     * draw layers: grid(testing only), floor layout, rooms and the player
     * @param graph
     */
    public void draw(Graphics graph){
        graph.setFont(PanelStyles.Map.mapTextFont);
        //this.drawGrid(graph); // design purpose only
        this.drawFloorLayout(graph);
        boolean spotLight; // player's current room
        boolean neighbour; // neighbours of player's current room
        List<String> neighbours = gameMap.getPlayer().getCurrentRoom().getExits();
        for(Map.Entry<String, Room> r : gameMap.getRooms().entrySet()){
            spotLight = this.playerRoomSpotLight(r.getValue(),gameMap.getPlayer());
            neighbour = neighbours.contains(r.getKey()); // original design used <room id=""> as the key to identify room
            if(r.getKey().equalsIgnoreCase("hall") || r.getKey().equalsIgnoreCase("upstairslanding")){
                this.drawHall(graph, r.getValue(), spotLight, neighbour);
            } else
                this.drawRoom(graph, r.getValue(), spotLight, neighbour);
        }
    }

    /**
     * decide whether the Player is in this Room or not
     * @param room
     * @param player
     * @return
     */
    private boolean playerRoomSpotLight(Room room, Player player){
        if(player.getCurrentRoom().equals(room)){
            return true;
        }
        return false;
    }

    /**
     * this will draw all rooms on panel by its coordinates
     * it doesn't not draw room connections in this method
     *
     * @param graph
     * @param mapRoom
     */
    private void drawRoom(Graphics graph, Room mapRoom, boolean spotLight, boolean neighbour){
        int x_center = ((MapRoom)mapRoom).getDx();
        int y_center = ((MapRoom)mapRoom).getDy();
        if(spotLight){
            graph.setColor(PanelStyles.Map.playerColor);
        }
        if(neighbour){
            graph.setColor(PanelStyles.Map.neighbourColor);
        }
        for(int i = 0; i < PanelStyles.Map.mapRoomLength; i++){
            int x = x_center - PanelStyles.Map.mapRoomLength * PanelStyles.Map.unitSize / 2 + i * PanelStyles.Map.unitSize;
            int y = y_center - PanelStyles.Map.mapRoomLength * PanelStyles.Map.unitSize / 2 + i * PanelStyles.Map.unitSize;
            graph.drawString("R", x, y_center- PanelStyles.Map.mapRoomLength *PanelStyles.Map.unitSize/2);
            graph.drawString("R", x, y_center+ PanelStyles.Map.mapRoomLength *PanelStyles.Map.unitSize/2);
            graph.drawString("R", x_center- PanelStyles.Map.mapRoomLength *PanelStyles.Map.unitSize/2, y);
            graph.drawString("R", x_center+ PanelStyles.Map.mapRoomLength *PanelStyles.Map.unitSize/2, y);
        }
        graph.drawString("R", x_center+ PanelStyles.Map.mapRoomLength *PanelStyles.Map.unitSize/2, y_center+ PanelStyles.Map.mapRoomLength *PanelStyles.Map.unitSize/2);
        // draw room name

        graph.setColor(PanelStyles.Map.roomTextColor);
        graph.setFont(PanelStyles.Map.roomTextFont);
        graph.drawString(mapRoom.getRoomName(),x_center- PanelStyles.Map.mapRoomLength *PanelStyles.Map.unitSize/2,y_center- PanelStyles.Map.mapRoomLength *PanelStyles.Map.unitSize/2 - PanelStyles.Map.unitSize);
        graph.setFont(PanelStyles.Map.mapTextFont);
        graph.setColor(PanelStyles.Map.mapDefault);
    }

    /**
     * hall is a rectangle instead of a square
     *
     * @param graph
     * @param hall
     */
    private void drawHall(Graphics graph, Room hall, boolean spotlight, boolean neighbour){
        int x_center = ((MapRoom)hall).getDx();
        int y_center = ((MapRoom)hall).getDy();
        if(spotlight){
            graph.setColor(PanelStyles.Map.playerColor);
        }
        if(neighbour){
            graph.setColor(PanelStyles.Map.neighbourColor);
        }
        // draw horizontal
        for(int i = 0; i < PanelStyles.Map.mapHallLength; i++){
            int x = x_center - PanelStyles.Map.mapHallLength * PanelStyles.Map.unitSize/3 + i * PanelStyles.Map.unitSize; // top left corner
            graph.drawString("H", x, y_center - PanelStyles.Map.mapRoomLength * PanelStyles.Map.unitSize/2);
            graph.drawString("H", x, y_center + PanelStyles.Map.mapRoomLength * PanelStyles.Map.unitSize/2);
        }
        // draw vertical
        for(int i = 0; i < PanelStyles.Map.mapRoomLength; i++){
            int y = y_center - PanelStyles.Map.mapRoomLength * PanelStyles.Map.unitSize/2 + i * PanelStyles.Map.unitSize; // top left corner
            graph.drawString("H", x_center - PanelStyles.Map.mapHallLength * PanelStyles.Map.unitSize/3, y);
            graph.drawString("H", x_center + PanelStyles.Map.mapHallLength * PanelStyles.Map.unitSize*2/3, y);
        }
        graph.drawString("H", x_center + PanelStyles.Map.mapHallLength * PanelStyles.Map.unitSize*2/3, y_center+ PanelStyles.Map.mapRoomLength *PanelStyles.Map.unitSize/2); // right bottom corner
        // draw room name
        graph.setColor(PanelStyles.Map.roomTextColor);
        graph.setFont(PanelStyles.Map.roomTextFont);
        graph.drawString(hall.getRoomName(), x_center - PanelStyles.Map.mapHallLength * PanelStyles.Map.unitSize/3,y_center- PanelStyles.Map.mapRoomLength *PanelStyles.Map.unitSize/2 - PanelStyles.Map.unitSize);
        graph.setFont(PanelStyles.Map.mapTextFont);
        graph.setColor(PanelStyles.Map.mapDefault); // reset color
    }

    /**
     * 3 levels: basement, first floor and second
     * @param graph
     */
    private void drawFloorLayout(Graphics graph){
        int[] floorUnit = {10*PanelStyles.Map.unitSize,(PanelStyles.Map.panelHeight-20*PanelStyles.Map.unitSize)/2, (PanelStyles.Map.panelHeight-20*PanelStyles.Map.unitSize)/2, 10*PanelStyles.Map.unitSize};
        // draw floor
        graph.setColor(PanelStyles.Map.floorNameColor);
        graph.setFont(PanelStyles.Map.floorNameFont);

        int floorHeight = 0;
        for(int i = 0; i < floorUnit.length; i++){
            floorHeight +=floorUnit[i];
            graph.drawLine(0,floorHeight,PanelStyles.Map.panelWidth+PanelStyles.Map.unitSize,floorHeight);
            graph.drawString(PanelStyles.Map.mapFloors[i], PanelStyles.Map.unitSize, floorHeight-PanelStyles.Map.unitSize);
        }
        graph.setFont(PanelStyles.Map.mapTextFont);
        graph.setColor(PanelStyles.Map.mapDefault);
    }

    /**
     * draw grid system for design purpose
     *
     * @param graph
     */
    private void drawGrid(Graphics graph){
        // grid system
        // vertical lines
        for (int i = 0; i <= PanelStyles.Map.panelHeight / PanelStyles.Map.unitSize; i++) {
            graph.drawLine(0, (i + 1) * PanelStyles.Map.unitSize, PanelStyles.Map.panelWidth + PanelStyles.Map.unitSize, (i + 1) * PanelStyles.Map.unitSize);
        }
        // horizontal lines
        for (int j = 0; j <= PanelStyles.Map.panelWidth / PanelStyles.Map.unitSize; j++) {
            graph.drawLine((j + 1) * PanelStyles.Map.unitSize, 0, (j + 1) * PanelStyles.Map.unitSize, PanelStyles.Map.panelHeight + PanelStyles.Map.unitSize);
        }
        graph.setColor(Color.RED);
        graph.setFont(new Font("TimesRoman", Font.PLAIN, PanelStyles.Map.unitSize * 2 / 3));
        for (int i = 0; i <= PanelStyles.Map.panelHeight / PanelStyles.Map.unitSize; i++) {
            graph.drawString(String.valueOf(i), 0, (i + 1) * PanelStyles.Map.unitSize);
        }
        for (int i = 0; i <= PanelStyles.Map.panelWidth / PanelStyles.Map.unitSize; i++) {
            graph.drawString(String.valueOf(i), (i) * PanelStyles.Map.unitSize, PanelStyles.Map.unitSize);
        }
        graph.setColor(PanelStyles.Map.mapDefault); // reset color
    }

    public void updateGUI() {
        this.revalidate();
        this.repaint();
    }

    @Override
    public void paint(Graphics graph){
        super.paint(graph);
        draw(graph);
    }
}
