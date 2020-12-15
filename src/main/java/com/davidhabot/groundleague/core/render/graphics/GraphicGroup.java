package com.davidhabot.groundleague.core.render.graphics;

import lombok.AllArgsConstructor;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class GraphicGroup<E extends Graphic> implements Graphic {
    @NonNull //해당 스프라이트 그룹의 이름
    private final String name;
    @NonNull //해당 그룹에 존재하는 모든 스프라이트의 집합
    protected final List<E> graphics;

    public GraphicGroup(String name) {
        this.name = name;
        graphics = new ArrayList<>();
    }

    //그룹 내의 가장 첫번째 스프라이트를 가져온다.
    public E getGraphic() {
        return graphics.get(0);
    }

    //그룹 내의 idx 번째 스프라이트를 가져온다.
    public E getGraphic(int idx) {
        return graphics.get(idx);
    }

    @Override //toString 을 재정의하여, 해당 스프라이트그룹의 이름을 형식에 맞게 반환하게 만든다.
   public String toString() {
        return "스프라이트 그룹 - " + name;
    }
}
