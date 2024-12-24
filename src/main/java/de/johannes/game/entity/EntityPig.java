package de.johannes.game.entity;

import de.johannes.curses.util.ColorBuilder;
import de.johannes.curses.util.Timer;

import java.util.Random;

public class EntityPig extends Entity{

    private final Timer dirTimer, moveTimer;

    public EntityPig() {
        super(":", new ColorBuilder().defineBackground("#DA9A79").defineForeground("#FF99C9").build());
        dirTimer = new Timer();
        moveTimer = new Timer();
    }

    @Override
    public void tick() {
        if(moveTimer.check(300)) {
            move(1,0);
            moveTimer.reset();
        }
    }
}
