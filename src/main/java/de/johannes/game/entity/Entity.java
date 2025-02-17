package de.johannes.game.entity;

import de.johannes.Game;
import de.johannes.game.ui.GameWindow;
import de.johannes.game.world.tiles.Tile;

public abstract class Entity {

    private final String character;
    private int color;
    private boolean alive = true;

    private float x,y;

    public Entity(String character, int color) {
        this.character = character;
        this.color = color;
    }

    public String character() {
        return character;
    }

    public void draw(GameWindow window) {
        for(int i = 0; i < Game.TILE_WIDTH; i++) {
            for(int j = 0; j < Game.TILE_HEIGHT; j++) {
                window.drawString(window.width()/2+((int)x-(int)Game.instance().player.x())+i, window.height()/2+((int)y-(int)Game.instance().player.y())+j, character, color);
            }
        }
    }

    public abstract void tick();

    public int color() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public float x() {
        return x;
    }

    public float y() {
        return y;
    }

    public int renderX() {
        return (int) (x*Game.TILE_WIDTH);
    }

    public int renderY() {
        return (int) (y*Game.TILE_HEIGHT);
    }

    public void move(float x, float y) {
        if(Game.instance().world == null) return;
        Tile at = Game.instance().world.tileAt((int)(this.x+x),(int) (this.y+y));
        if(at == null || at.material() == null || (Game.instance().world != null && at.material().transparent())) {
            this.x+=x;
            this.y+=y;
        }
    }

    public void position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean isAlive() {
        return alive;
    }

    public void kill() {
        this.alive = false;
    }
}
