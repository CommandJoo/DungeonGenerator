package de.johannes.game.events;

import de.johannes.commons.events.event.Event;
import de.johannes.commons.events.event.EventCancelable;
import de.johannes.curses.Mouse;

public class MouseEvent extends EventCancelable {

    private Mouse mouse;

    public MouseEvent(Mouse mouse) {
        this.mouse = mouse;
    }

    public Mouse mouse() {
        return mouse;
    }
}
