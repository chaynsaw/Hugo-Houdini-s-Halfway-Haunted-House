package com.locallampoon.fiveh.ui;

public enum Key {
    ENTER(10), DOWN(40), UP(38);

    private final int key;

    Key(int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }
}
