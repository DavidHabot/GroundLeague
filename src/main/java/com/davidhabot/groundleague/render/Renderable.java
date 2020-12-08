package com.davidhabot.groundleague.render;

import com.davidhabot.groundleague.render.graphics.ScreenController;

@FunctionalInterface
public interface Renderable {
    void render(ScreenController screen);
}