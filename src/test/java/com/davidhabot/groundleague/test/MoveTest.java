package com.davidhabot.groundleague.test;

import com.davidhabot.groundleague.core.actor.Direction;
import com.davidhabot.groundleague.core.actor.pawn.Pawn;
import org.apache.log4j.Level;
import org.junit.Test;

public class MoveTest implements Testable {
    Pawn  testPawn = new Pawn();
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
        testPawn.move(Direction.UP);
    }
}
