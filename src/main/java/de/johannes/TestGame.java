package de.johannes;

import de.johannes.commons.events.EventListener;
import de.johannes.curses.Mouse;
import de.johannes.game.events.KeyEvent;
import de.johannes.game.events.TileClickEvent;
import de.johannes.game.ui.GameWindow;
import de.johannes.game.ui.LogWindow;
import de.johannes.game.world.tiles.Material;

public class TestGame extends Game implements EventListener {
    @Override
    public void init() {
        eventManager().register(this);
        Logger.log(Logger.INFO, "Registered EventListener: TestGame");
    }

    public void onKey(KeyEvent event) {
        if(event.key() == 'l') {
            GameWindow window = ((GameWindow) Main.windowManager.getWindow(0));
            if(window != null) {
                if(window.getComponent(100) != null) {
                    window.removeComponent(100);
                }else {
                    window.addComponent(100, new LogWindow());
                    Logger.log(Logger.INFO, "Opened Log");
                }
                event.setCanceled(true);
            }
        }
    }

    public void onClick(TileClickEvent event) {
        if(Game.instance().world.tileAt(event.x(), event.y()) != null && Game.instance().world.tileAt(event.x(), event.y()).material() != null) {
            Material mat = Game.instance().world.tileAt(event.x(), event.y()).material();

            if(Game.instance().world.tileAt(event.x(), event.y()).distance((int)Game.instance().player.x(), (int)Game.instance().player.y()) < Game.instance().player.range) {
                if(event.mouse().check(Mouse.BUTTON1_CLICKED)) {
                    //TODO Check if tile is breakable
                    Game.instance().player.inventory.addInventory(mat);
                    int index = Game.instance().world.tileIndex(event.x(), event.y());
                    Game.instance().world.tiles.remove(index);
                }
            }
        }
    }
}
