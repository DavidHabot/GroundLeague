package com.davidhabot.groundleague.render.graphics;

import com.davidhabot.groundleague.update.Updatable;
import lombok.NonNull;

import java.util.List;

/**
 * 스프라이트의 애니메이션을 위한 스프라이트 그룹
 */
public class SpriteAnim extends SpriteGroup implements Updatable { //animRate 를 주기적으로 변환하기 위하여 Update 인터페이스를 상속한다.
    private int animRate;;//해당 스프라이트 그룹의 애니메이션 기능을 위해 만든 애니메이션 상태변수
    private final int maxAnimRate, animTick; //animRate 의 최대값과, 한 스프라이트가 해당 애니메이션에서 몇 틱이나 띄워지는지를 정한다.

    public SpriteAnim(@NonNull String name, @NonNull List<Sprite> sprites, int animTick) {
        super(name, sprites);
        this.animTick = animTick; //animTick
        this.maxAnimRate = animTick * sprites.size(); //매개변수로 받은 animTick(스프라이트 변경 주기 | 단위 tick)과 스프라이트 개수를 곱해서 실제 애니메이션 한 주기의 틱 수를 구하여 maxAnimRate 에 넣는다.
    }

    @Override
    public void update() { //매 틱마다 실행되는 메서드이다. animRate 를 계속 변화시켜서 애니메이팅을 성립시킨다.
        if(animRate > maxAnimRate) {
            animRate = -1;//if문 이후에 1이 더해지므로, 0보다 1 작은 -1로 초기화한다.
        }
        animRate++;
    }

    public void clearRate() { //애니메이션을 초기상태(첫번째 스프라이트가 띄워지는 상태)로 되돌린다.
        animRate = 0; //animRate 를 0으로 바꾸어 애니메이션의 진행도를 처음으로 돌린다.
    }

    @Override
    public Sprite getSprite() { //현재 애니메이션 레이트에 맞는 스프라이트를 가져온다.
        return sprites.get(animRate / animTick); //animRate 을 animTick 으로 나눌경우, 현재 애니메이션중인 스프라이트의 인덱스를 얻게된다.
        //공식의 검토를 하려면 animRate 에 maxAnimRate 를 넣을것.
    }
}
