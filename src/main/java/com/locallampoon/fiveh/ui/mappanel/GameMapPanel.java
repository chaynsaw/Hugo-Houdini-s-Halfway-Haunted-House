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

public class GameMapPanel extends JPanel {
    GameMap gameMap = GameMap.getInstance();

    public GameMapPanel() {
        this.setPreferredSize(new Dimension(PanelStyles.Map.PANEL_WIDTH, PanelStyles.Map.PANEL_HEIGHT));
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
        graph.setFont(PanelStyles.Map.MAP_TEXT_FONT);
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
            graph.setColor(PanelStyles.Map.PLAYER_COLOR);
        }
        if(neighbour){
            graph.setColor(PanelStyles.Map.NEIGHBOUR_COLOR);
        }
        for(int i = 0; i < PanelStyles.Map.MAP_ROOM_LENGTH; i++){
            int x = x_center - PanelStyles.Map.MAP_ROOM_LENGTH * PanelStyles.Map.UNIT_SIZE / 2 + i * PanelStyles.Map.UNIT_SIZE;
            int y = y_center - PanelStyles.Map.MAP_ROOM_LENGTH * PanelStyles.Map.UNIT_SIZE / 2 + i * PanelStyles.Map.UNIT_SIZE;
            graph.drawString("R", x, y_center- PanelStyles.Map.MAP_ROOM_LENGTH *PanelStyles.Map.UNIT_SIZE /2);
            graph.drawString("R", x, y_center+ PanelStyles.Map.MAP_ROOM_LENGTH *PanelStyles.Map.UNIT_SIZE /2);
            graph.drawString("R", x_center- PanelStyles.Map.MAP_ROOM_LENGTH *PanelStyles.Map.UNIT_SIZE /2, y);
            graph.drawString("R", x_center+ PanelStyles.Map.MAP_ROOM_LENGTH *PanelStyles.Map.UNIT_SIZE /2, y);
        }
        graph.drawString("R", x_center+ PanelStyles.Map.MAP_ROOM_LENGTH *PanelStyles.Map.UNIT_SIZE /2, y_center+ PanelStyles.Map.MAP_ROOM_LENGTH *PanelStyles.Map.UNIT_SIZE /2);
        // draw room name

        graph.setColor(PanelStyles.Map.ROOM_TEXT_COLOR);
        graph.setFont(PanelStyles.Map.ROOM_TEXT_FONT);
        graph.drawString(mapRoom.getRoomName(),x_center- PanelStyles.Map.MAP_ROOM_LENGTH *PanelStyles.Map.UNIT_SIZE /2,y_center- PanelStyles.Map.MAP_ROOM_LENGTH *PanelStyles.Map.UNIT_SIZE /2 - PanelStyles.Map.UNIT_SIZE);
        graph.setFont(PanelStyles.Map.MAP_TEXT_FONT);
        graph.setColor(PanelStyles.Map.MAP_DEFAULT);
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
            graph.setColor(PanelStyles.Map.PLAYER_COLOR);
        }
        if(neighbour){
            graph.setColor(PanelStyles.Map.NEIGHBOUR_COLOR);
        }
        // draw horizontal
        for(int i = 0; i < PanelStyles.Map.MAP_HALL_LENGTH; i++){
            int x = x_center - PanelStyles.Map.MAP_HALL_LENGTH * PanelStyles.Map.UNIT_SIZE /3 + i * PanelStyles.Map.UNIT_SIZE; // top left corner
            graph.drawString("H", x, y_center - PanelStyles.Map.MAP_ROOM_LENGTH * PanelStyles.Map.UNIT_SIZE /2);
            graph.drawString("H", x, y_center + PanelStyles.Map.MAP_ROOM_LENGTH * PanelStyles.Map.UNIT_SIZE /2);
        }
        // draw vertical
        for(int i = 0; i < PanelStyles.Map.MAP_ROOM_LENGTH; i++){
            int y = y_center - PanelStyles.Map.MAP_ROOM_LENGTH * PanelStyles.Map.UNIT_SIZE /2 + i * PanelStyles.Map.UNIT_SIZE; // top left corner
            graph.drawString("H", x_center - PanelStyles.Map.MAP_HALL_LENGTH * PanelStyles.Map.UNIT_SIZE /3, y);
            graph.drawString("H", x_center + PanelStyles.Map.MAP_HALL_LENGTH * PanelStyles.Map.UNIT_SIZE *2/3, y);
        }
        graph.drawString("H", x_center + PanelStyles.Map.MAP_HALL_LENGTH * PanelStyles.Map.UNIT_SIZE *2/3, y_center+ PanelStyles.Map.MAP_ROOM_LENGTH *PanelStyles.Map.UNIT_SIZE /2); // right bottom corner
        // draw room name
        graph.setColor(PanelStyles.Map.ROOM_TEXT_COLOR);
        graph.setFont(PanelStyles.Map.ROOM_TEXT_FONT);
        graph.drawString(hall.getRoomName(), x_center - PanelStyles.Map.MAP_HALL_LENGTH * PanelStyles.Map.UNIT_SIZE /3,y_center- PanelStyles.Map.MAP_ROOM_LENGTH *PanelStyles.Map.UNIT_SIZE /2 - PanelStyles.Map.UNIT_SIZE);
        graph.setFont(PanelStyles.Map.MAP_TEXT_FONT);
        graph.setColor(PanelStyles.Map.MAP_DEFAULT); // reset color
    }

    /**
     * 3 levels: basement, first floor and second
     * @param graph
     */
    private void drawFloorLayout(Graphics graph){
        int[] floorUnit = {10*PanelStyles.Map.UNIT_SIZE,(PanelStyles.Map.PANEL_HEIGHT -20*PanelStyles.Map.UNIT_SIZE)/2, (PanelStyles.Map.PANEL_HEIGHT -20*PanelStyles.Map.UNIT_SIZE)/2, 10*PanelStyles.Map.UNIT_SIZE};
        // draw floor
        graph.setColor(PanelStyles.Map.FLOOR_NAME_COLOR);
        graph.setFont(PanelStyles.Map.FLOOR_NAME_FONT);

        int floorHeight = 0;
        for(int i = 0; i < floorUnit.length; i++){
            floorHeight +=floorUnit[i];
            graph.drawLine(0,floorHeight,PanelStyles.Map.PANEL_WIDTH +PanelStyles.Map.UNIT_SIZE,floorHeight);
            graph.drawString(PanelStyles.Map.MAP_FLOORS[i], PanelStyles.Map.UNIT_SIZE, floorHeight-PanelStyles.Map.UNIT_SIZE);
        }
        graph.setFont(PanelStyles.Map.MAP_TEXT_FONT);
        graph.setColor(PanelStyles.Map.MAP_DEFAULT);
    }

    /**
     * draw grid system for design purpose
     *
     * @param graph
     */
    private void drawGrid(Graphics graph){
        // grid system
        // vertical lines
        for (int i = 0; i <= PanelStyles.Map.PANEL_HEIGHT / PanelStyles.Map.UNIT_SIZE; i++) {
            graph.drawLine(0, (i + 1) * PanelStyles.Map.UNIT_SIZE, PanelStyles.Map.PANEL_WIDTH + PanelStyles.Map.UNIT_SIZE, (i + 1) * PanelStyles.Map.UNIT_SIZE);
        }
        // horizontal lines
        for (int j = 0; j <= PanelStyles.Map.PANEL_WIDTH / PanelStyles.Map.UNIT_SIZE; j++) {
            graph.drawLine((j + 1) * PanelStyles.Map.UNIT_SIZE, 0, (j + 1) * PanelStyles.Map.UNIT_SIZE, PanelStyles.Map.PANEL_HEIGHT + PanelStyles.Map.UNIT_SIZE);
        }
        graph.setColor(Color.RED);
        graph.setFont(new Font("TimesRoman", Font.PLAIN, PanelStyles.Map.UNIT_SIZE * 2 / 3));
        for (int i = 0; i <= PanelStyles.Map.PANEL_HEIGHT / PanelStyles.Map.UNIT_SIZE; i++) {
            graph.drawString(String.valueOf(i), 0, (i + 1) * PanelStyles.Map.UNIT_SIZE);
        }
        for (int i = 0; i <= PanelStyles.Map.PANEL_WIDTH / PanelStyles.Map.UNIT_SIZE; i++) {
            graph.drawString(String.valueOf(i), (i) * PanelStyles.Map.UNIT_SIZE, PanelStyles.Map.UNIT_SIZE);
        }
        graph.setColor(PanelStyles.Map.MAP_DEFAULT); // reset color
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
