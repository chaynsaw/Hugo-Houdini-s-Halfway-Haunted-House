package com.locallampoon.fiveh.ui;

import java.awt.*;

public class PanelStyles {
    // global styles
    public static final String FONT_FAMILY = "Arial";
    public static final int FONT_WEIGHT = Font.PLAIN;
    public static final int FONT_SIZE = 18;
    public static final Color BG_COLOR = Color.BLACK;
    public static final Color FG_COLOR = Color.WHITE;
    public static final int WINDOW_X = 0;
    public static final int WINDOW_Y = 0;
    public static final int WINDOW_WIDTH = 1000;
    public static final int WINDOW_HEIGHT = 800;
    // intro panel styles
    public static final int INTRO_TITLE_X = 100;
    public static final int INTRO_TITLE_Y = 100;
    public static final int INTRO_TITLE_WIDTH = 780;
    public static final int INTRO_TITLE_HEIGHT = 150;
    public static final int INTRO_TITLE_FONT_SIZE = 40;
    public static final int INTRO_OPTIONS_X = 400;
    public static final int INTRO_OPTIONS_Y = 400;
    public static final int INTRO_OPTIONS_WIDTH = 200;
    public static final int INTRO_OPTIONS_HEIGHT = 100;
    public static final int INTRO_OPTIONS_FONT_SIZE = 30;
    // console styles
    public static final int CONSOLE_PANEL_X = 0;
    public static final int CONSOLE_PANEL_Y = 730;
    public static final int CONSOLE_PANEL_WIDTH = 1000;
    public static final int CONSOLE_PANEL_HEIGHT = 32;
    public static final int CONSOLE_TXT_AREA_X = 0;
    public static final int CONSOLE_TXT_AREA_Y = 0;
    public static final int CONSOLE_TXT_AREA_WIDTH = 540;
    public static final int CONSOLE_TXT_AREA_HEIGHT = 200;
    // Map panel styles
    public static final int UNIT_SIZE = 8; // how big each block/unit; player movement length also
    public static final int PANEL_WIDTH = 600; // panel size
    public static final int PANEL_HEIGHT = 500;
    public static final Font MAP_TEXT_FONT = new Font(FONT_FAMILY, Font.PLAIN, UNIT_SIZE*2/3);
    public static final String[] MAP_FLOORS = new String[]{"Attic", "Second", "First","Basement"};
    public static final int PLAYER_SIZE = 5;
    public static int[] XPlayer = new int[PLAYER_SIZE];
    public static int[] YPlayer = new int[PLAYER_SIZE];
    public static final int MAP_PLAYER_DELAY = 100; // how fast the player can move
    public static final int MAP_ROOM_LENGTH = 4; // * unit size
    public static final int MAP_HALL_LENGTH = 4*MAP_ROOM_LENGTH;
    public static final Color MAP_DEFAULT = new Color(192,192,192);
    public static final Color ROOM_TEXT_COLOR = new Color(255,69,0);
    public static final Color PLAYER_COLOR = new Color(10,248,8);
    public static final Color NEIGHBOUR_COLOR = new Color(61,184,255);
    public static final Color FLOOR_NAME_COLOR = new Color(255,255,255);
    public static final Font FLOOR_NAME_FONT = new Font(FONT_FAMILY, Font.PLAIN, UNIT_SIZE*3/2);
    public static final Font ROOM_TEXT_FONT = new Font(FONT_FAMILY, Font.PLAIN, UNIT_SIZE*4/3);
}