package com.davidhabot.groundleague.render.graphics;

import lombok.Getter;
import lombok.NonNull;

public class ScreenController {
    //스크린 컨트롤러의 이름을 생성자로 지정하지 않았을경우 설정될 기본 이름이다.
    private static final String DEFAULT_NAME = "@BasicScreenController";
    @Getter //해당 스크린 컨트롤러의 이름이다.
    private final String name;
    @Getter //해당 스크린 컨트롤러가 조정할 스크린이다.
    private final Screen screen;


    public ScreenController() {
        this(DEFAULT_NAME);
    }
    public ScreenController(String name) {
        this(name, new Screen());
    }
    public ScreenController(@NonNull String name, Screen screen) {
        this.name = name;
        this.screen = screen;
    }

    @Override
    public String toString() {
        return name;
    }
}
