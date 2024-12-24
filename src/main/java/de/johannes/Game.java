package de.johannes;

import de.johannes.game.entity.Player;
import de.johannes.game.world.World;

public class Game {

    private static Game instance;

    public Player player;
    public World world;

    public Game() {
        instance = this;
        this.player = new Player();
        this.player.move(0, 0);
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public static Game instance() {
        return instance;
    }

    public void tick() {
        this.world.tick();
    }
}
