package de.johannes.game.events;

import de.johannes.commons.events.event.EventCancelable;

public class KeyEvent extends EventCancelable {

    private int key;

    public KeyEvent(int key) {
        this.key = key;
    }

    public int key() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }
}
