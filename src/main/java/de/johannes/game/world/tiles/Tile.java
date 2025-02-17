package de.johannes.game.world.tiles;

import de.johannes.Game;
import de.johannes.curses.ui.components.Window;
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

    public int renderX() {
        return x* Game.TILE_WIDTH;
    }

    public int renderY() {
        return y* Game.TILE_HEIGHT;
    }

    public void draw(Window window, Player player) {
        drawAt(window, player, renderX(),renderY(), material,material.color());
    }

    public void draw(Window window, Player player, int color) {
        drawAt(window, player, renderX(),renderY(), material, color);
    }

    public void drawAt(Window window, Player player, int x, int y, Material mat, int color) {
        for(int i = 0; i < Game.TILE_WIDTH; i++) {
            for(int j = 0; j < Game.TILE_HEIGHT; j++) {
                int rX = window.width()/2+(x-player.renderX())+i;
                int rY = window.height()/2+(y-player.renderY())+j;
                if(rX > 0 && rX < window.width() &&
                        rY > 0 && rY < window.height()) {
                    window.drawString(rX, rY, mat.texture(i,j), color);
                }
            }
        }
    }

    public float distance(int x, int y) {
        return (float) Math.sqrt(((x-this.x)*(x-this.x))+((y-this.y)*(y-this.y)));
    }

}
