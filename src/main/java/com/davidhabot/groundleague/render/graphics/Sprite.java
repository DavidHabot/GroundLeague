package com.davidhabot.groundleague.render.graphics;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor //이 클래스내에 있는 모든 맴버변수를 초기화하는 생성자이다.
public abstract class Sprite {
    @Getter //해당 스프라이트의 이름
    private final String name;
    @Getter //해당 스프라이트의 높이와 너비
    protected int width, height;
    @Getter //해당 스프라이트의 이미지를 담은 정수형 배열
    protected int[] sprite;

    @Override //toString 을 재정의하여, 해당 스프라이트의 이름을 형식에 맞게 반환하게 만든다.
    public String toString() {
        return "스프라이트 - " + name;
    }
}
