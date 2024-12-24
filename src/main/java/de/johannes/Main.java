package de.johannes;

import de.johannes.curses.window.WindowManager;
import de.johannes.game.entity.EntityPig;
import de.johannes.game.ui.GameWindow;
import de.johannes.game.ui.UIWindow;
import de.johannes.game.world.World;

import java.util.Random;

public class Main {

    public static WindowManager windowManager;

    public static void main(String[] args) throws InterruptedException {
        Game game = new Game();
        windowManager = new WindowManager(100, 230, 48);

        windowManager.render();
        windowManager.handleKey();

        windowManager.addWindow(0, new GameWindow());
        windowManager.addWindow(1, new UIWindow());

        World world = new World(1200, 1200);
        game.setWorld(world);
        world.populate();
    }
}