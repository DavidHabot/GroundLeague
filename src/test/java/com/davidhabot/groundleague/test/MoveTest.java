package com.davidhabot.groundleague.test;

import com.davidhabot.groundleague.core.actor.Direction;
import com.davidhabot.groundleague.core.actor.pawn.Pawn;
import com.davidhabot.groundleague.core.render.Renderer;
import com.davidhabot.groundleague.core.render.screen.Screen;
import com.davidhabot.groundleague.core.render.screen.ScreenController;
import com.davidhabot.groundleague.core.update.Updater;
import org.apache.log4j.Level;
import org.junit.Test;

public class MoveTest implements Testable {
    Pawn  testPawn = new Pawn(1);
    @Test
    public void testMovement() {
        logger.log(Level.INFO, "TEST_START - testMovement");
        logger.log(Level.DEBUG, "#Case 001 - move(Direction.UP)");
        testPawn.move(Direction.UP);
        logger.log(Level.DEBUG, "#Case 002 - move(Direction.DOWN)");
        testPawn.move(Direction.DOWN);
        logger.log(Level.DEBUG, "#Case 003 - move(Direction.LEFT)");
        testPawn.move(Direction.LEFT);
        logger.log(Level.DEBUG, "#Case 004 - move(Direction.RIGHT)");
        testPawn.move(Direction.RIGHT);
        logger.log(Level.DEBUG, "#Case 005 - move(10, -10)");
        testPawn.move(10, -10);

        Updater updater = new Updater("#TestUpdater");
        Thread thread = new Thread(updater, "#TestUpdateThread");
        thread.start();
        long time = System.currentTimeMillis();
        while(System.currentTimeMillis() < time + 20);
        System.exit(0);
        updater.stopUpdate();
    }

    @Test
    public void testDisplayMove() {
        ScreenController sc = new ScreenController("#TestScreenController", new Screen(1920, 1080));
        Renderer renderer = new Renderer(sc);
        Updater updater = new Updater("#TestUpdater");
        Pawn pawn = new Pawn(1);

        Thread rThread = new Thread(renderer, "렌더러 쓰레드");
        Thread uThread = new Thread(updater, "업데이터 쓰레드");

        uThread.start();
        rThread.start();

        while (true) {
            System.out.println(sc.getScreen().getPixel()[0]);
        }

    }
}
