package de.johannes.game.ui;

import de.johannes.Game;
import de.johannes.curses.Curses;
import de.johannes.curses.CursesConstants;
import de.johannes.curses.Mouse;
import de.johannes.curses.window.components.Window;
import de.johannes.game.entity.Player;

public class UIWindow extends Window {

    public UIWindow() {
        super(null, 0, 0, 21, Curses.height()-1, CursesConstants.WHITE, "Console Game", false);
    }

    @Override
    public void init() {
        for(int x = 0; x < 16; x++) {
            int row = x%4;
            int col = x/4;
            addComponent(1000+x, new Slot(this, x, 1+row+(row*4), 1+col+(col*2)));
        }
    }

    @Override
    public void draw() {
        Player player = Game.instance().player;
        int index = 0;

        for(int i = 0; i < Math.min(player.inventory.items().size(), 16); i++) {
            Player.Item item = player.inventory.getItem(i);
            Slot slot = ((Slot) getComponent(1000 + i));
            slot.setItem(item);
        }
        drawString( 1, 13, "$cf{#FF9999}X:"+Game.instance().player.x(), CursesConstants.WHITE);
        drawString( 1, 14, "$cf{#9999FF}Y:"+Game.instance().player.y(), CursesConstants.WHITE);

        try {
            drawString(1, 16, "Selected: "+Game.instance().player.inventory.getItem(Game.instance().player.inventory.selected()).material().getClass().getSimpleName(), CursesConstants.WHITE);
        } catch(Exception _) {
        }
    }

    @Override
    public boolean handleKey(char c) {
        return false;
    }

    @Override
    public boolean handleClick(Mouse mouse) {
        return super.handleClick(mouse);
    }
}
