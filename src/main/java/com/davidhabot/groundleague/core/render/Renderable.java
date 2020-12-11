package com.davidhabot.groundleague.core.render;

import com.davidhabot.groundleague.core.render.screen.ScreenController;

@FunctionalInterface
public interface Renderable {
    void render(ScreenController screen);
}
