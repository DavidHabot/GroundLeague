package com.davidhabot.groundleague.core;

import com.davidhabot.groundleague.core.update.Updater;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * GroundLeague 프로젝트의 메인 클래스이다.
 * 게임의 생명주기와 쓰레딩을 관리한다.
 */
public class GroundLeague {
    public static Logger logger = Logger.getLogger(GroundLeague.class);
    private static Thread render;
    private static Thread update;

    public static void main(String[] args) {
        logger.log(Level.DEBUG, "PROGRAM_START - GroundLeague Program is Started!");
        logger.log(Level.DEBUG, "THREAD_START - main Thread is Running!");
        initThread();
        //render.start();;
        //update.start();
        logger.log(Level.DEBUG, "THREAD_END - main Thread is Stop!");
        logger.log(Level.ERROR, "PROGRAM_TEST - testError");
    }

    public static void initThread() {
        //render = new Renderer();
        update = new Updater();
    }
}