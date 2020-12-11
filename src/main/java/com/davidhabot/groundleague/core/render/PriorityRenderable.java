package com.davidhabot.groundleague.core.render;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@AllArgsConstructor
public abstract class PriorityRenderable implements Renderable {
    @Getter @Setter @NonNull
    private int priority;
}
