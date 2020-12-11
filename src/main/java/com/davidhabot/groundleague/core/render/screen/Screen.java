package com.davidhabot.groundleague.core.render.screen;

import lombok.Getter;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Arrays;

public class Screen extends Canvas {
    private BufferedImage img;
    @Getter
    private int[] pixel;

    //스크린의 기본 생성자. width 와 height 를 초기화하며, initScreen 을 호출한다.
    public Screen(int width, int height) {
        initScreen(width, height); //스크린을 초기화한다.
    }

    //스크린에 쓰이는 이미지와 pixel 배열을 초기화한다.
    public void initScreen(int width, int height) {
        img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB); //이미지를 초기화한다.
        pixel = ((DataBufferInt) img.getRaster().getDataBuffer()).getData(); //초기화한 이미지로 int 배열인 pixel 을 초기화한다.
    }

    //스크린을 초기화(검정색으로 칠함)한다.
    public void clear() {
        paint(0);
    }
    //색상을 인자로 받아와서, 스크린을 해당 색상으로 칠한다.
    public void paint(int color) {
        Arrays.fill(pixel, color); //픽셀 배열의 색상값을 color 를 초기화한다.
    }

    //스크린의 (x,y) 좌표의 색상값을 정한다.
    public void setPixel(int x, int y, int color) {
        this.pixel[x + y * getWidth()] = color; //1차원 배열이므로 y값에 화면의 너비를 곱해 y값을 사용한다.
    }

    @Override //버퍼 전략 객체를 가져오는 getter 를 오버라이딩한다.
    public BufferStrategy getBufferStrategy() {
        int bufferNum = 3; //버퍼전략의 버퍼 수를 초기화한다.
        BufferStrategy bs; //반환한 버퍼전략
        if(super.getBufferStrategy() == null) //만약 기존의 getter 의 반환값이 null 일경우
            createBufferStrategy(bufferNum); //버퍼 수가 bufferNum 인 버퍼 전략을 생성한다.
        bs = super.getBufferStrategy(); //버퍼전략이 생성되었으므로, 기존의 getter 를 다시 호출하여 bs에 담는다.
        return bs; //bs를 반환한다.
    }
}
