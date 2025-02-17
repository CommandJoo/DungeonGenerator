package de.johannes.game.ui;

import de.johannes.Game;
import de.johannes.curses.Curses;
import de.johannes.curses.CursesConstants;
import de.johannes.curses.Mouse;
import de.johannes.curses.ui.base.BoxComponentBuilder;
import de.johannes.curses.ui.components.Text;
import de.johannes.curses.ui.components.Window;
import de.johannes.game.entity.Player;

public class UIWindow extends Window {

    public UIWindow() {
        super(null, "Console Game", 0, 0, 21, Curses.height()-1, CursesConstants.WHITE, CursesConstants.WHITE);
    }

    @Override
    public void init() {
        for(int x = 0; x < 16; x++) {
            int row = x%4;
            int col = x/4;
            Slot slot = new BoxComponentBuilder<Slot>()
                    .parent(this)
                    .at(1+row+(row*4), 1+col+(col*2))
                    .bounds(4,2)
                    .color(CursesConstants.WHITE)
                    .build(Slot::new)
                    .index(x);
            addComponent(1000+x, slot);
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
        Text.of("Y:"+Game.instance().player.y()).parent(this).at(1,14).setColor("#9999FF").draw();
        Text.of("X:"+Game.instance().player.x()).parent(this).at(1,13).setColor("#FF9999").draw();

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
