package com.davidhabot.groundleague.core.action;

import com.davidhabot.groundleague.core.actor.Actor;

public interface Attackable {
    void attack(Actor actor, int dmg);
}
