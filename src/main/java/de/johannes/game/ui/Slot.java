package de.johannes.game.ui;

import de.johannes.Game;
import de.johannes.curses.Curses;
import de.johannes.curses.CursesConstants;
import de.johannes.curses.Mouse;
import de.johannes.curses.ui.base.BoxComponent;
import de.johannes.curses.ui.base.Component;
import de.johannes.curses.util.ColorBuilder;
import de.johannes.curses.util.Timer;
import de.johannes.game.entity.Player;

public class Slot extends BoxComponent {

    private int index;
    private Player.Item item;

    public Slot() {
    }

    public Slot index(int index) {
        this.index = index;
        return this;
    }

    private final int selectedColor = new ColorBuilder().defineForeground("#5555AA").build();

    @Override
    public void init() {}

    @Override
    public void draw() {
        if(index == Game.instance().player.inventory.selected()) {
            color = CursesConstants.DARK_CYAN;
        }else {
            color = CursesConstants.WHITE;
        }
        Curses.clearBox(x(),y(),width(),height(), color());
        drawBox();
        if (item != null && item.material() != null) {
            drawString(1, 1, " " + item.material().nonempty() + " ", item.material().color());
            drawString(4, 2, "" + item.count(), color);

        }
    }

    private final Timer clickTimer = new Timer();

    @Override
    public boolean handleClick(Mouse mouse) {
        if (clickTimer.check(200)) {
            if (Game.instance().player.inventory.selected() == index) Game.instance().player.inventory.setSelected(-1);
            else Game.instance().player.inventory.setSelected(index);

            clickTimer.reset();
        }
        return true;
    }

    public void setItem(Player.Item item) {
        this.item = item;
    }

    @Override
    public boolean handleKey(char c) {
        return false;
    }
}
