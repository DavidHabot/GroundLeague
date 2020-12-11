package com.davidhabot.groundleague.test;

import com.davidhabot.groundleague.core.render.graphics.ColorSprite;
import com.davidhabot.groundleague.core.render.graphics.Sprite;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.Arrays;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertArrayEquals;

public class SpriteTest implements Testable {
    private Dimension size;
    private String path;
    private int color;

    @Before
    public void initSprite() {
        this.size = new Dimension(16, 8);
        this.path = "";
        this.color = 0xFF00FF; //MAGENTA
    }

    @Test //이미지 스프라이트가 잘 로딩되는지 검사한다.
    public void testColorSpriteLoad() {
        Sprite spr = new ColorSprite("#Test", size.width, size.height, color);
        assertEquals(spr.toString(), "스프라이트 - #Test");
        System.out.println("ColorSprite 의 toString 메서드가 정상작동합니다!");
        assertEquals(spr.getWidth(), size.width);
        assertEquals(spr.getHeight(), size.height);
        System.out.println("ColorSprite 의 생성자를 통한 변수 초기화가 정상작동합니다!");
        int[] sprite = new int[(int)(size.getHeight() * size.getWidth())];
        Arrays.fill(sprite, color); //
        assertArrayEquals(spr.getSprite(), sprite);
        System.out.println("ColorSprite 의 loadSprite 메서드가 정상작동합니다!");
    }
}
