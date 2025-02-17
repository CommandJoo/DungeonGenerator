package de.johannes.game.events;

import de.johannes.curses.Mouse;

public class TileClickEvent extends MouseEvent {

    private int x,y;

    public TileClickEvent(Mouse mouse, int x, int y) {
        super(mouse);
        this.x = x;
        this.y = y;
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }
}
