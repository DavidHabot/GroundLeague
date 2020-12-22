package com.davidhabot.groundleague.core.render;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

public abstract class PriorityRenderable implements Renderable {
    @Getter @Setter @NonNull
    private int priority;

    public PriorityRenderable(int priority) {
        if(priority >= 10000) {
            throw new ArithmeticException("중요도 값이 최대치(9999)을 넘었습니다!");
        }
        this.priority = priority;
    }
}
