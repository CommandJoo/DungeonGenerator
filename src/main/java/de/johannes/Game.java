package de.johannes;

import de.johannes.commons.events.EventManager;
import de.johannes.game.entity.Player;
import de.johannes.game.world.World;

public abstract class Game {

    public static final int TILE_WIDTH = 5, TILE_HEIGHT = 2;
    private static Game instance;

    private final EventManager eventManager;
    public Player player;
    public World world;

    public Game() {
        instance = this;
        this.eventManager = new EventManager();
        this.player = new Player();
        this.player.move(0, 0);
    }

    public abstract void init();

    public void setWorld(World world) {
        this.world = world;
    }

    public World world() {
        return world;
    }

    public void tick() {
        this.world.tick();
    }

    public EventManager eventManager() {
        return eventManager;
    }

    public static Game instance() {
        return instance;
    }
}
