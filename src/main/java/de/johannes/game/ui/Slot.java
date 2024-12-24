package de.johannes.game.ui;

import de.johannes.Game;
import de.johannes.curses.CursesConstants;
import de.johannes.curses.Mouse;
import de.johannes.curses.util.ColorBuilder;
import de.johannes.curses.util.Timer;
import de.johannes.curses.window.Component;
import de.johannes.game.entity.Player;
import de.johannes.game.world.tiles.materials.Wood;

public class Slot extends Component {

    private int index;
    private Player.Item item;

    public Slot(UIWindow parent, int index, int x, int y) {
        super(parent, x, y, 4, 2, CursesConstants.WHITE, true);
        this.index = index;
    }

    private final int selectedColor = new ColorBuilder().defineForeground("#5555AA").build();

    @Override
    public void draw() {
        drawBox(-1);
        if (item != null && item.material() != null) {
            drawBox(index == Game.instance().player.inventory.selected() ? selectedColor : -1);
            drawString(1, 1, " " + item.material().character() + " ", item.material().color());
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
