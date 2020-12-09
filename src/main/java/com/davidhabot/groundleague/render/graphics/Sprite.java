package com.davidhabot.groundleague.render.graphics;

import lombok.Getter;

public class Sprite {
    @Getter
    private int[] sprite;
    private int width;
    private int height;

    public Sprite(int width, int height , String path) {
        this.width = width;
        this.height = height;
        this.sprite = new int[width * height];
    }
}
