package com.davidhabot.groundleague.core.actor;

import com.davidhabot.groundleague.core.render.Renderable;
import com.davidhabot.groundleague.core.render.Renderer;
import com.davidhabot.groundleague.core.render.graphics.ColorSprite;
import com.davidhabot.groundleague.core.render.graphics.SpriteAnim;
import com.davidhabot.groundleague.core.render.graphics.GraphicGroup;
import com.davidhabot.groundleague.core.render.screen.ScreenController;
import com.davidhabot.groundleague.core.update.Updatable;
import com.davidhabot.groundleague.core.update.Updater;
import lombok.Getter;
import lombok.NonNull;
import org.apache.log4j.Logger;

public abstract class Actor  implements Updatable, Renderable {
    private final Logger logger = Logger.getLogger(this.getClass());

    @NonNull @Getter
    protected double x, y; //해당 Actor 의 x, y 좌표
    protected GraphicGroup<SpriteAnim> sprite; //해당 Actor 의 스프라이트

    //Actor 의 기본생성자
    public Actor() {
        this(0, 0);
    }

    //Actor 의 좌표를 초기화한다.
    public Actor(double x, double y) {
        this.x = x;
        this.y = y;
        this.sprite = new GraphicGroup<SpriteAnim>("#Player");
        //TODO 더미 스프라이트 실제 스프라이트로 바꿀것
        SpriteAnim anim = new SpriteAnim(
                "player-stand",
                1,
                new ColorSprite("#player-stand-front", 1, 1, 0xFF00FF)); //DummySprite
        sprite.addGraphic(anim);
        Updater.addUpdatable(this);
        Renderer.addRenderable(this);
    }

    @Override
    public void render(ScreenController screen) {
        int idx = 0; //더미값
        //스프라이트 그룹에서 index 번쨰 스프라이트 Anim 을 가져온 뒤,
        // 해당 Anim 에서 스프라이트를 가져온다. 이후 해당 스프라이트에서 getSprite 로 배열화된 Sprite 를 가져온다.
        int[] pixel = sprite.getGraphic(idx).getGraphic().getSprite();
        pixel[(int)x + (int)y * screen.getWidth()] = 0xFF00FF;
    }
}
