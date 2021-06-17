package com.locallampoon.fiveh.core;

enum Direction {
    NORTH(0), SOUTH(1), EAST(2), WEST(3), UP(4), DOWN(5);

    private final int direction;

    Direction(int direction) {
        this.direction = direction;
    }

    public int getDirection() {
        return direction;
    }
}
