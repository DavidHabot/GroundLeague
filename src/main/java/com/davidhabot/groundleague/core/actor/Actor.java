package com.davidhabot.groundleague.core.actor;

import com.davidhabot.groundleague.core.render.Renderable;
import com.davidhabot.groundleague.core.render.graphics.Sprite;
import com.davidhabot.groundleague.core.update.Updatable;
import lombok.Getter;
import lombok.NonNull;

public abstract class Actor  implements Updatable, Renderable {
    @NonNull @Getter
    protected int x, y;
    protected Sprite sprite;
}
