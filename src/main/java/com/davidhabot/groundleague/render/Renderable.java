package com.davidhabot.groundleague.render;

import com.davidhabot.groundleague.render.screen.ScreenController;

@FunctionalInterface
public interface Renderable {
    void render(ScreenController screen);
}
