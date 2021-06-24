package com.locallampoon.fiveh.ui;

public enum Command {
    GO_NORTH("go north"),
    GO_SOUTH("go south"),
    GO_WEST("go west"),
    GO_EAST("go east"),
    GO_UP("go up"),
    GO_DOWN("go down");

    private final String text;

    Command(String commandString) {
        this.text = commandString;
    }

    public String getText() {
        return this.text;
    }
}
