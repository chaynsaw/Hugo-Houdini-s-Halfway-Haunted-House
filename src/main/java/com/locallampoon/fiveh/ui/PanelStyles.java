package com.locallampoon.fiveh.ui;

import java.awt.*;

public class PanelStyles {
    // global styles
    public static final String FONT_FAMILY = "Arial";
    public static final int FONT_WEIGHT = Font.PLAIN;
    public static final int FONT_SIZE = 18;
    public static final Color BG_COLOR = Color.BLACK;
    public static final Color FG_COLOR = Color.WHITE;

    public static class Window {
        public static final int x = 0;
        public static final int y = 0;
        public static final int width = 1000;
        public static final int height = 910;
    }

    // intro panel styles
    public static class Intro {
        public static final int titleX = 100;
        public static final int titleY = 100;
        public static final int titleWidth = 780;
        public static final int titleHeight = 150;
        public static final int titleFontSize = 40;
        public static final int optionsX = 400;
        public static final int optionsY = 400;
        public static final int optionsWidth = 200;
        public static final int optionsHeight = 100;
        public static final int optionsFontSize = 30;
    }

    // console styles
    public static class ConsolePanel {
        public static final int x = 0;
        public static final int y = 840;
        public static final int width = 1000;
        public static final int height = 32;
        public static final int txtAreaX = 0;
        public static final int txtAreaY = 0;
        public static final int txtAreaWidth = 540;
        public static final int txtAreaHeight = 200;
    }

    // Map panel styles
    public static class Map {
        public static final int unitSize = 8; // how big each block/unit; player movement length also
        public static final int panelWidth = 600; // panel size
        public static final int panelHeight = 500;
        public static final Font mapTextFont = new Font(FONT_FAMILY, Font.PLAIN, unitSize * 2/3);
        public static final String[] mapFloors = new String[]{"Attic", "Second", "First","Basement"};
        public static final int mapRoomLength = 4; // * unit size
        public static final int mapHallLength = 4 * mapRoomLength;
        public static final Color mapDefault = new Color(192,192,192);
        public static final Color roomTextColor = new Color(255,69,0);
        public static final Color playerColor = new Color(10,248,8);
        public static final Color neighbourColor = new Color(61,184,255);
        public static final Color floorNameColor = new Color(255,255,255);
        public static final int playerSize = 5;
        public static int[] XPlayer = new int[playerSize];
        public static int[] YPlayer = new int[playerSize];
        public static final Font floorNameFont = new Font(FONT_FAMILY, Font.PLAIN, unitSize * 3/2);
        public static final Font roomTextFont = new Font(FONT_FAMILY, Font.PLAIN, unitSize * 4/3);
    }

    public static class ArtPanel {
        public static int x = 100;
        public static int y = 20;
        public static int width = 250;
        public static int height = 300;
    }
}