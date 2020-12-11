package com.davidhabot.groundleague.core.render.graphics;

import lombok.Getter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageSprite extends Sprite{
    @Getter
    private final String path;

    //해당 클래스의 기본생성자이다.
    public ImageSprite(String name, String path) {
        super(name, 0, 0, null);
        this.path = path;
        loadSprite(); //스프라이트를 로딩한다.
    }

    @Override //해당 클래스에 있는 이미지 주소(path)를 통해 스프라이트를 로딩하는 메서드이다.
    protected void loadSprite() {
        try {
            BufferedImage img = ImageIO.read(ImageSprite.class.getResource(path)); //Image IO 에 이미지의 주소를 인자로 넘겨 BufferedImage 의 형식으로 이미지파일을 불러온다
            width = img.getWidth();
            height = img.getHeight();
            img.getRGB(0, 0, width, height, sprite, 0, width);
        } catch (IOException e) {
            System.out.println("이미지의 경로를 찾을 수 없습니다!");
            e.printStackTrace();
        }
    }
}
