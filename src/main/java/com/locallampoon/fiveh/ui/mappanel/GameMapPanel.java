package com.locallampoon.fiveh.ui.mappanel; /**
 * Game map image oldmap.jpg from https://www.123rf.com/photo_147314303_old-vintage-map-with-handmade-drawings-for-treasure-games-and-fantasy-stories.html
 * Player image palyer.png from https://pngtree.com/freepng/cartoon-animal-game-character-design_4069832.html
 */

import com.locallampoon.fiveh.core.Room;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

public class GameMapPanel extends JPanel implements ActionListener {

    public static final int UNIT_SIZE = 8; // how big each block/unit; player movement length also
    final int PANEL_WIDTH = 580; // panel size
    final int PANEL_HEIGHT = 480;
    final int ROOM_FONT = 1; // size of room name
    final int GAME_UNITS = (PANEL_WIDTH*PANEL_WIDTH)/UNIT_SIZE;
    final int X_ROOM_BOUNDARY = 0; // TODO: need calculation
    final int Y_ROOM_BOUNDARY = 0; // TODO: need calculation
    final int X_PLAYER_BOUNDARY = 0; // TODO: need calculation
    final int Y_PLAYER_BOUNDARY = 0; // TODO: need calculation
    final int DELAY = 100; // how fast the player can move
    final int PLAYER_SIZE = 2;
    final int ROOM_LENGTH = 8; //TODO: test odd number
    private int xPlayer[] = new int[PLAYER_SIZE];
    private int yPlayer[] = new int[PLAYER_SIZE];

    private Timer timer;

    private boolean[] visited = new boolean[]{false,false,false,false}; // room is visited?
    GameMap gameMap = GameMap.getInstance();
    private MouseListener mapMouseListener = new GameMapMouseListener();

    public GameMapPanel(){
        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addMouseListener(mapMouseListener); // listening mouse motion on this panel
        startGamePanel();
    }

    public void startGamePanel(){
        timer = new Timer(DELAY, this); // delay is how often the timer triggers; listening this;
        timer.start();
    }

    public void draw(Graphics graph){
        this.drawGrid(graph); // TODO: remove it in final version
        for(Room r : gameMap.getRooms()){
            this.drawRoom(graph,r);
        }
        this.drawPlayer(graph);

        // draw map
        // draw other items
    }

    private void drawPlayer(Graphics graph){
        for(int i = 0; i< PLAYER_SIZE; i++){
            if(i != 0){
                graph.setColor(Color.GREEN);
                graph.fillRect(xPlayer[i], yPlayer[i], UNIT_SIZE, UNIT_SIZE);
            } else {
                graph.setColor(new Color(45,180,0));
            }
        }
    }

    private void drawRoom(Graphics graph, Room mapRoom){
        graph.setColor(new Color(192,192,192));
        graph.setFont(new Font("TimesRoman", Font.BOLD, UNIT_SIZE*2/3));
        int x_center = ((MapRoom)mapRoom).getDx();
        int y_center = ((MapRoom)mapRoom).getDy();
        for(int i = 0; i < ROOM_LENGTH; i++){
            int x = x_center - ROOM_LENGTH * UNIT_SIZE / 2 + i * UNIT_SIZE;
            int y = y_center - ROOM_LENGTH * UNIT_SIZE / 2 + i * UNIT_SIZE;
            graph.drawString("R", x, y_center-ROOM_LENGTH*UNIT_SIZE/2);
            graph.drawString("R", x, y_center+ROOM_LENGTH*UNIT_SIZE/2);
            graph.drawString("R", x_center-ROOM_LENGTH*UNIT_SIZE/2, y);
            graph.drawString("R", x_center+ROOM_LENGTH*UNIT_SIZE/2, y);
        }
        graph.drawString("R", x_center+ROOM_LENGTH*UNIT_SIZE/2, y_center+ROOM_LENGTH*UNIT_SIZE/2);
        // draw room name
        graph.setColor(new Color(255,69,0));
        graph.setFont(new Font("Courier", Font.PLAIN, UNIT_SIZE*ROOM_FONT));
        for(int i = 0; i < mapRoom.getRoomName().length(); i++){
            graph.drawString(String.valueOf(mapRoom.getRoomName().charAt(i)),x_center-ROOM_LENGTH*UNIT_SIZE/2 + i * UNIT_SIZE*ROOM_FONT,y_center-ROOM_LENGTH*UNIT_SIZE/2 - UNIT_SIZE*ROOM_FONT);
        }
    }

    /**
     * draw grid system for design purpose
     * @param graph
     */
    private void drawGrid(Graphics graph){
        // grid system
        for(int i = 0; i <= PANEL_HEIGHT/UNIT_SIZE; i++){
            graph.drawLine(0,(i+1)*UNIT_SIZE, PANEL_WIDTH+UNIT_SIZE, (i+1)*UNIT_SIZE);
        }
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

    /**
     * helper method for this demo; need another design to skip visitedRoom
     * move from one coordinate to another
     * @param visitedRoom
     * @param mapRoom
     */
    private void moveTo(int visitedRoom, MapRoom mapRoom) throws InterruptedException {
        if(xPlayer[PLAYER_SIZE-1] == mapRoom.getDx() && yPlayer[PLAYER_SIZE-1] == mapRoom.getDy()) {
            visited[visitedRoom] = true;
            System.out.printf("Moved to Room:%s;\n", mapRoom.getRoomName());
            System.out.printf("Player head: (%d, %d)\n",xPlayer[PLAYER_SIZE-1],yPlayer[PLAYER_SIZE-1]);
            Thread.sleep(1500);
        }

        if(xPlayer[PLAYER_SIZE-1] < mapRoom.getDx() )
            xPlayer[PLAYER_SIZE-1] += UNIT_SIZE;
        else if (xPlayer[PLAYER_SIZE-1] > mapRoom.getDx())
            xPlayer[PLAYER_SIZE-1] -= UNIT_SIZE;

        if(yPlayer[PLAYER_SIZE-1] < mapRoom.getDy() )
            yPlayer[PLAYER_SIZE-1] += UNIT_SIZE;
        else if (yPlayer[PLAYER_SIZE-1] > mapRoom.getDy())
            yPlayer[PLAYER_SIZE-1] -= UNIT_SIZE;
    }

    @Override
    public void paint(Graphics graph){
        super.paint(graph);
        draw(graph);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO: complete action listener
//        try {
//            if(!visited[0]){
//                this.moveTo(0, gameMap.getRooms().get(0));
//            } else if(!visited[1]){
//                this.moveTo(1, gameMap.getRooms().get(1));
//            } else if(!visited[2]){
//                this.moveTo(2, gameMap.getRooms().get(2));
//            } else if(!visited[3]){
//                this.moveTo(3, gameMap.getRooms().get(3));
//            }
//        } catch (InterruptedException interruptedException) {
//            interruptedException.printStackTrace();
//        }
//        super.repaint();
    }
}
