package com.locallampoon.fiveh.ui.mappanel; /**
 * Game map image oldmap.jpg from https://www.123rf.com/photo_147314303_old-vintage-map-with-handmade-drawings-for-treasure-games-and-fantasy-stories.html
 * Player image palyer.png from https://pngtree.com/freepng/cartoon-animal-game-character-design_4069832.html
 */

import com.locallampoon.fiveh.core.Game;
import com.locallampoon.fiveh.core.Room;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

import static com.locallampoon.fiveh.ui.PanelStyles.*;

public class GameMapPanel extends JPanel {
    GameMap gameMap = GameMap.getInstance();

    public GameMapPanel() {
        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        this.setBackground(Color.BLACK);
        gameMap.setPlayer(Game.getPlayer()); // pass a reference to game map
    }

    public void draw(Graphics graph) {
        graph.setFont(MAP_TEXT_FONT);
        this.drawGrid(graph); // design purpose only
        this.drawFloorLayout(graph);
        boolean spotLight = false;
        for(Map.Entry<String, Room> r : gameMap.getRooms().entrySet()){
            if(gameMap.getPlayer().getCurrentRoom().equals(r.getValue())){
                spotLight = true;
            } else{
                spotLight = false;
            }
            if(r.getKey().toLowerCase().equals("hall") || r.getKey().toLowerCase().equals("upstairslanding")){
                this.drawHall(graph, r.getValue(), spotLight);
            } else
                this.drawRoom(graph, r.getValue(), spotLight);
        }
        this.drawPlayer(graph);

        // draw map
        // draw other items
    }

    private void drawPlayer(Graphics graph){
        for(int i = 0; i< PLAYER_SIZE; i++){
            if(i != 0){
                graph.setColor(Color.GREEN);
                graph.fillRect(XPlayer[i], YPlayer[i], UNIT_SIZE, UNIT_SIZE);
            } else {
                graph.setColor(new Color(45,180,0));
            }
        }
        graph.setColor(MAP_DEFAULT);
    }

    /**
     * this will draw all rooms on panel by its coordinates
     * it doesn't not draw room connections in this method
     * @param graph
     * @param mapRoom
     */
    private void drawRoom(Graphics graph, Room mapRoom, boolean spotLight){
        int x_center = ((MapRoom)mapRoom).getDx();
        int y_center = ((MapRoom)mapRoom).getDy();
        if(spotLight){
            graph.setColor(PLAYER_COLOR);
        }
        for(int i = 0; i < MAP_ROOM_LENGTH; i++){
            int x = x_center - MAP_ROOM_LENGTH * UNIT_SIZE / 2 + i * UNIT_SIZE;
            int y = y_center - MAP_ROOM_LENGTH * UNIT_SIZE / 2 + i * UNIT_SIZE;
            graph.drawString("R", x, y_center- MAP_ROOM_LENGTH *UNIT_SIZE/2);
            graph.drawString("R", x, y_center+ MAP_ROOM_LENGTH *UNIT_SIZE/2);
            graph.drawString("R", x_center- MAP_ROOM_LENGTH *UNIT_SIZE/2, y);
            graph.drawString("R", x_center+ MAP_ROOM_LENGTH *UNIT_SIZE/2, y);
        }
        graph.drawString("R", x_center+ MAP_ROOM_LENGTH *UNIT_SIZE/2, y_center+ MAP_ROOM_LENGTH *UNIT_SIZE/2);
        // draw room name
        graph.setColor(new Color(255,69,0));
        graph.setFont(ROOM_TEXT_FONT);
        for(int i = 0; i < mapRoom.getRoomName().length(); i++){
            graph.drawString(String.valueOf(mapRoom.getRoomName().charAt(i)),x_center- MAP_ROOM_LENGTH *UNIT_SIZE/2 + i * UNIT_SIZE,y_center- MAP_ROOM_LENGTH *UNIT_SIZE/2 - UNIT_SIZE);
        }
        graph.setFont(MAP_TEXT_FONT);
        graph.setColor(MAP_DEFAULT);
    }

    /**
     * 3 levels: basement, first floor and second
     * @param graph
     */
    private void drawFloorLayout(Graphics graph){
        int[] floorUnit = {10*UNIT_SIZE,(PANEL_HEIGHT-20*UNIT_SIZE)/2, (PANEL_HEIGHT-20*UNIT_SIZE)/2, 10*UNIT_SIZE};
        // draw floor
        graph.setColor(FLOOR_NAME_COLOR);
        graph.setFont(FLOOR_NAME_FONT);

        int floorHeight = 0;
        for(int i = 0; i < floorUnit.length; i++){
            floorHeight +=floorUnit[i];
            graph.drawLine(0,floorHeight,PANEL_WIDTH+UNIT_SIZE,floorHeight);
            graph.drawString(MAP_FLOORS[i], UNIT_SIZE, floorHeight-UNIT_SIZE);
        }
        graph.setFont(MAP_TEXT_FONT);
        graph.setColor(MAP_DEFAULT);
    }

    /**
     * easy stairs that only draw square starting from top left corner to bottom right
     * @param x_topLeft
     * @param y_topLeft
     */
    private void drawStairs(Graphics graph, int x_topLeft, int y_topLeft, int size){
        // draw vertical
//        int index = 0;
//        for(int i = 0; i < height; i++){
//
//        }
        graph.setColor(MAP_DEFAULT); // reset color
    }

    /**
     * hall is a rectangle instead of a square
     * @param graph
     * @param hall
     */
    private void drawHall(Graphics graph, Room hall, boolean spotlight){
        int x_center = ((MapRoom)hall).getDx();
        int y_center = ((MapRoom)hall).getDy();
        if(spotlight){
            graph.setColor(PLAYER_COLOR);
        }
        // draw horizontal
        for(int i = 0; i < MAP_HALL_LENGTH; i++){
            int x = x_center - MAP_HALL_LENGTH * UNIT_SIZE/3 + i * UNIT_SIZE; // top left corner
            graph.drawString("H", x, y_center - MAP_ROOM_LENGTH * UNIT_SIZE/2);
            graph.drawString("H", x, y_center + MAP_ROOM_LENGTH * UNIT_SIZE/2);
        }
        // draw vertical
        for(int i = 0; i < MAP_ROOM_LENGTH; i++){
            int y = y_center - MAP_ROOM_LENGTH * UNIT_SIZE/2 + i * UNIT_SIZE; // top left corner
            graph.drawString("H", x_center - MAP_HALL_LENGTH * UNIT_SIZE/3, y);
            graph.drawString("H", x_center + MAP_HALL_LENGTH * UNIT_SIZE*2/3, y);
        }
        graph.drawString("H", x_center + MAP_HALL_LENGTH * UNIT_SIZE*2/3, y_center+ MAP_ROOM_LENGTH *UNIT_SIZE/2); // right bottom corner
        // draw room name
        graph.setColor(ROOM_TEXT_COLOR);
        graph.setFont(ROOM_TEXT_FONT);
        for(int i = 0; i < hall.getRoomName().length(); i++){
            graph.drawString(String.valueOf(hall.getRoomName().charAt(i)), x_center - MAP_HALL_LENGTH * UNIT_SIZE/3 + i * UNIT_SIZE,y_center- MAP_ROOM_LENGTH *UNIT_SIZE/2 - UNIT_SIZE);
        }
        graph.setFont(MAP_TEXT_FONT);
        graph.setColor(MAP_DEFAULT); // reset color
    }

    /**
     * draw grid system for design purpose
     * @param graph
     */
    private void drawGrid(Graphics graph){
        // grid system
        // vertical lines
        for(int i = 0; i <= PANEL_HEIGHT/UNIT_SIZE; i++){
            graph.drawLine(0,(i+1)*UNIT_SIZE, PANEL_WIDTH+UNIT_SIZE, (i+1)*UNIT_SIZE);
        }
        // horizontal lines
        for(int j = 0; j <= PANEL_WIDTH/UNIT_SIZE; j++){
            graph.drawLine((j+1)*UNIT_SIZE,0, (j+1)*UNIT_SIZE, PANEL_HEIGHT+UNIT_SIZE);
        }
        graph.setColor(Color.RED);
        graph.setFont(new Font("TimesRoman", Font.PLAIN, UNIT_SIZE*2/3));
        for(int i = 0; i <= PANEL_HEIGHT/UNIT_SIZE; i++) {
            graph.drawString(String.valueOf(i),0,(i+1)*UNIT_SIZE);
        }
        for(int i = 0; i <= PANEL_WIDTH/UNIT_SIZE; i++) {
            graph.drawString(String.valueOf(i),(i)*UNIT_SIZE,UNIT_SIZE);
        }
        graph.setColor(MAP_DEFAULT); // reset color
    }

    public void updateGUI(){
        super.revalidate(); // highlight room
        super.repaint();
    }

    @Override
    public void paint(Graphics graph){
        super.paint(graph);
        draw(graph);
    }
}
