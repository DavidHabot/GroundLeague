package com.davidhabot.groundleague.level;

public abstract class Level {
    private int[] map;
    private final int width, height;

    public Level(int width, int height) {
        this.width = width;
        this.height = height;
        map = new int[width * height];
    }

    public void getTile(int x, int y) {

    }

    public abstract void generateLevel();
}
