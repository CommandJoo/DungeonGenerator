package de.johannes.game.world.tiles;

import de.johannes.curses.window.components.Window;
import de.johannes.game.entity.Player;

public class Tile {

    private final Material material;
    private final int x,y;

    public Tile(Material material, int x, int y) {
        this.material = material;
        this.x = x;
        this.y = y;
    }

    public Material material() {
        return material;
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }

    public void draw(Window window, Player player) {
        drawAt(window, player, x,y,material.character(), material.color());
    }

    public void draw(Window window, Player player, int color) {
        drawAt(window, player, x,y,material.character(), color);
    }

    public void drawAt(Window window, Player player, int x, int y, String s, int color) {
        window.drawString(window.width/2+(x-player.x()), window.height/2+(y-player.y()), s, color);
    }

}
