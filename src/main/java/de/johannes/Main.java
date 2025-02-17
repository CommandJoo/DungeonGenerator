package de.johannes;

import de.johannes.curses.ui.WindowManager;
import de.johannes.curses.util.Files;
import de.johannes.game.ui.GameWindow;
import de.johannes.game.ui.UIWindow;
import de.johannes.game.world.World;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Random;

public class Main {

    public static WindowManager windowManager;

    public static void main(String[] args) throws InterruptedException, FileNotFoundException {
        System.setOut(outputFile("log.txt"));
        Logger.log(Logger.INFO, "Set logfile to log.txt");
        windowManager = new WindowManager(90, 230, 48, false);
        windowManager.render();
        windowManager.handleKey();
        Logger.log(Logger.INFO, "Started WindowManager");

        windowManager.addWindow(0, new GameWindow());
        Logger.log(Logger.INFO, "Added GameWindow");

        Game game = new TestGame();
        game.init();
        Logger.log(Logger.INFO, "Initialized Game");

        World world = new World(1200, 1200);
        game.setWorld(world);
        world.populate();
        Logger.log(Logger.INFO, "Populated World");
    }

    protected static PrintStream outputFile(String name) throws FileNotFoundException {
        return new PrintStream(new BufferedOutputStream(new FileOutputStream(name)), true);
    }
}