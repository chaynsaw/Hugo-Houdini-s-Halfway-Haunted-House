package com.locallampoon.fiveh.ui.mappanel; /**
 * Game map image oldmap.jpg from https://www.123rf.com/photo_147314303_old-vintage-map-with-handmade-drawings-for-treasure-games-and-fantasy-stories.html
 * Player image palyer.png from https://pngtree.com/freepng/cartoon-animal-game-character-design_4069832.html
 */

import com.locallampoon.fiveh.core.Room;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.util.Map;

import static com.locallampoon.fiveh.ui.PanelStyles.*;

public class GameMapPanel extends JPanel{
    private Timer timer;
    private PlayerAnimation playerAnimation = new PlayerAnimation();
    private MouseListener mapMouseListener = new GameMapMouseListener();
    GameMap gameMap = GameMap.getInstance();

    public GameMapPanel(){
        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addMouseListener(mapMouseListener); // listening mouse motion on this panel
        startGamePanel();
    }

    public void startGamePanel(){
        timer = new Timer(MAP_PLAYER_DELAY, this.playerAnimation); // delay is how often the timer triggers; listening this;
        timer.start();
    }

    public void draw(Graphics graph){
        graph.setFont(MAP_TEXT_FONT);
        this.drawGrid(graph); // TODO: remove it in final version
        this.drawFloorLayout(graph);
        for(Map.Entry<String, Room> r : gameMap.getRooms().entrySet()){
            if(r.getKey().toLowerCase().equals("hall") || r.getKey().toLowerCase().equals("upstairslanding")){
                this.drawHall(graph, r.getValue());
            } else
                this.drawRoom(graph,r.getValue());
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
    }

    /**
     * this will draw all rooms on panel by its coordinates
     * it doesn't not draw room connections in this method
     * @param graph
     * @param mapRoom
     */
    private void drawRoom(Graphics graph, Room mapRoom){
        graph.setColor(new Color(192,192,192));
        int x_center = ((MapRoom)mapRoom).getDx();
        int y_center = ((MapRoom)mapRoom).getDy();
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
        graph.setFont(new Font("TimesRoman", Font.PLAIN, UNIT_SIZE*4/3));
        for(int i = 0; i < mapRoom.getRoomName().length(); i++){
            graph.drawString(String.valueOf(mapRoom.getRoomName().charAt(i)),x_center- MAP_ROOM_LENGTH *UNIT_SIZE/2 + i * UNIT_SIZE* ROOM_TEXT_FONT,y_center- MAP_ROOM_LENGTH *UNIT_SIZE/2 - UNIT_SIZE* ROOM_TEXT_FONT);
        }
        graph.setFont(MAP_TEXT_FONT);
    }

    /**
     * 3 levels: basement, first floor and second
     * @param graph
     */
    private void drawFloorLayout(Graphics graph){
        int floorUnit = PANEL_HEIGHT/3;
        // draw floor
        graph.setColor(new Color(255,255,255));
        for(int i = 0; i < 4; i ++){
            if(i == 1 || i == 2)
                graph.drawLine(0,i*floorUnit+UNIT_SIZE*5,PANEL_WIDTH+UNIT_SIZE,i*floorUnit+UNIT_SIZE*5);
            else
                graph.drawLine(0,i*floorUnit+UNIT_SIZE,PANEL_WIDTH+UNIT_SIZE,i*floorUnit+UNIT_SIZE);
            //draw stairs
        }
        // draw floor name
        graph.setFont(new Font("TimesRoman", Font.PLAIN, UNIT_SIZE*3/2));
        graph.drawString(MAP_FLOORS[0], UNIT_SIZE, floorUnit+UNIT_SIZE*7);
        graph.drawString(MAP_FLOORS[1], UNIT_SIZE, UNIT_SIZE*3);
        graph.drawString(MAP_FLOORS[2], UNIT_SIZE, floorUnit*2+UNIT_SIZE*7);
        graph.setFont(MAP_TEXT_FONT);
    }

    /**
     * easy stairs that only draw square starting from top left corner to bottom right
     * @param x_topLeft
     * @param y_topLeft
     */
    private void drawStairs(int x_topLeft, int y_topLeft, int size){
        // draw vertical
//        int index = 0;
//        for(int i = 0; i < height; i++){
//
//        }
    }

    /**
     * hall is a rectangle instead of a square
     * @param graph
     * @param hall
     */
    private void drawHall(Graphics graph, Room hall){
        graph.setColor(new Color(192,192,192));
        int x_center = ((MapRoom)hall).getDx();
        int y_center = ((MapRoom)hall).getDy();
        // draw horizontal
        for(int i = 0; i < MAP_ROOM_LENGTH *6; i++){
            int x = x_center - MAP_ROOM_LENGTH * UNIT_SIZE*2 + i * UNIT_SIZE; // top left corner
            graph.drawString("H", x, y_center- MAP_ROOM_LENGTH *UNIT_SIZE/2);
            graph.drawString("H", x, y_center+ MAP_ROOM_LENGTH *UNIT_SIZE/2);
        }
        // draw vertical
        for(int i = 0; i < MAP_ROOM_LENGTH; i++){
            int y = y_center - MAP_ROOM_LENGTH * UNIT_SIZE/2 + i * UNIT_SIZE; // top left corner
            graph.drawString("H", x_center- MAP_ROOM_LENGTH *UNIT_SIZE*2, y);
            graph.drawString("H", x_center + MAP_ROOM_LENGTH *4 * UNIT_SIZE, y);
        }
        graph.drawString("H", x_center + MAP_ROOM_LENGTH *4 * UNIT_SIZE, y_center+ MAP_ROOM_LENGTH *UNIT_SIZE/2); // right bottom corner
        // draw room name
        graph.setColor(new Color(255,69,0));
        graph.setFont(new Font("TimesRoman", Font.PLAIN, UNIT_SIZE*4/3));
        for(int i = 0; i < hall.getRoomName().length(); i++){
            graph.drawString(String.valueOf(hall.getRoomName().charAt(i)),x_center- MAP_ROOM_LENGTH *UNIT_SIZE/2 + i * UNIT_SIZE* ROOM_TEXT_FONT,y_center- MAP_ROOM_LENGTH *UNIT_SIZE/2 - UNIT_SIZE* ROOM_TEXT_FONT);
        }
        graph.setFont(MAP_TEXT_FONT);
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
    }

    @Override
    public void paint(Graphics graph){
        super.paint(graph);
        draw(graph);
    }
}
