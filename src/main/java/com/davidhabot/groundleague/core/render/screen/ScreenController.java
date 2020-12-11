package com.davidhabot.groundleague.core.render.screen;

import lombok.Getter;
import lombok.NonNull;

import javax.swing.*;

public class ScreenController extends JFrame {
    //스크린 컨트롤러의 이름을 생성자로 지정하지 않았을경우 설정될 기본 이름
    private static final String DEFAULT_NAME = "@BasicScreenController";
    @Getter //해당 스크린 컨트롤러의 이름
    private final String name;
    @Getter //해당 스크린 컨트롤러가 조정할 스크린
    private final Screen screen;

    //ScreenController 의 기본 생성자이다.
    public ScreenController() {
        this(DEFAULT_NAME); //해당 클래스의 또다른 생성자를 호출한다.
    }
    //컨트롤러의 이름을 통해 정보를 초기화하는 생성자
    public ScreenController(String name) {
        this(name, new Screen(1, 1)); //해당 클래스의 또다른 생성자를 호출한다.
    }
    //컨트롤러의 모든 정보를 초기화하는 생성자이다.
    public ScreenController(@NonNull String name, Screen screen) {
        this.name = name; //name 초기화
        this.screen = screen; //screen 초기화
        screen.clear(); //screen 의 색을 Black 로 칠한다.
        add(screen); //screen 을 JFrame 를 상속한 ScreenController 에 추가한다.
    }

    @Override //toString 호출시 name 을 리턴한다
    public String toString() {
        return name;
    }
}
