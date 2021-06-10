package com.locallampoon.fiveh.client;

import com.locallampoon.fiveh.core.Game;

import java.io.IOException;

public class FiveHClient {
    static Game game;

    public static void main(String[] args) throws IOException {
        game = new Game();
        game.start();

    }
}