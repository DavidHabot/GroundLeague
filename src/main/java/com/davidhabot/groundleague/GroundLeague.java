package com.davidhabot.groundleague;

import com.davidhabot.groundleague.render.Renderer;
import com.davidhabot.groundleague.update.Updater;

/**
 * GroundLeague 프로젝트의 메인 클래스이다.
 * 게임의 생명주기와 쓰레딩을 관리한다.
 */
public class GroundLeague {
    private static Thread render;
    private static Thread update;

    public static void main(String[] args) {
        initThread();

        render.start();;
        update.start();
    }

    public static void initThread() {
        //render = new Renderer();
        update = new Updater();
    }
}
