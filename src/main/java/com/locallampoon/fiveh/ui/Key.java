package com.locallampoon.fiveh.ui;

public enum Key {
    ENTER(10), DOWN(40), UP(38), LEFT(37), RIGHT(39), PG_UP(33), PG_DOWN(34);

    private final int key;

    Key(int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }
}
