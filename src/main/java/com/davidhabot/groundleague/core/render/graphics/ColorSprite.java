package com.davidhabot.groundleague.core.render.graphics;

import java.util.Arrays;

public class ColorSprite extends Sprite{
    int color;

    public ColorSprite(String name, int width, int height, int color) {
        super(name, width, height, new int[width * height]); //상위클래스인 Sprite의 생성자
        this.color = color; //색상 초기화
        loadSprite(); //스프라이트를 로딩한다.
    }

    @Override //스프라이트의 색상값을 통해 스프라이트를 로딩하는 메서드이다.
    protected void loadSprite() {
        Arrays.fill(this.sprite, color); //해당 스프라이트를 인자로 받은 색상으로 채운다.
    }
}
