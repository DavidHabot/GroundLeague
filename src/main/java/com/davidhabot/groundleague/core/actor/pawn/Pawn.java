package com.davidhabot.groundleague.core.actor.pawn;

import com.davidhabot.groundleague.core.action.Movable;
import com.davidhabot.groundleague.core.action.Rotatable;
import com.davidhabot.groundleague.core.actor.Actor;
import com.davidhabot.groundleague.core.actor.Directon;
import com.davidhabot.groundleague.core.render.screen.ScreenController;
import com.davidhabot.groundleague.core.update.Updatable;
import lombok.Getter;

public abstract class Pawn extends Actor implements Movable, Rotatable , Updatable {
    @Getter
    protected Directon dir;
    @Getter
    protected double speed;
    protected double xWeight, yWeight;

    @Override
    public void move(int xWeight, int yWeight) {
        this.xWeight += xWeight;
        this.yWeight += yWeight;
    }

    @Override
    public void rotate(Directon dir) {
        this.dir = dir;
    }

    @Override
    public void render(ScreenController screen) {
    }

    @Override
    public void update() {
        int dirX = 1, dirY = 1;
        if(xWeight < 0)
            dirX = -1;
        if(yWeight < 0)
            dirY = -1;

        if(xWeight != 0) {
            x += speed * dirX;
            xWeight -= speed;
        }
        if(yWeight != 0) {
            y += speed * dirY;
            yWeight -= speed;
        }
    }
}
