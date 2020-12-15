package com.davidhabot.groundleague.core.actor;

import lombok.Getter;

public enum Direction {
    UP(0, -1), DOWN(0, 1), LEFT(-1, 0), RIGHT(1, 0);

    @Getter
    private final int xWeight, yWeight;

    Direction(int xWeight, int yWeight) {
        this.xWeight = xWeight;
        this.yWeight = yWeight;
    }
}
