package de.johannes.game.entity;

import de.johannes.Game;
import de.johannes.curses.window.components.Window;
import de.johannes.game.ui.GameWindow;
import de.johannes.game.world.tiles.Tile;

public abstract class Entity {

    private final String character;
    private int color;
    private boolean alive = true;

    private int x,y;

    public Entity(String character, int color) {
        this.character = character;
        this.color = color;
    }

    public String character() {
        return character;
    }

    public void draw(GameWindow window) {
        window.drawString(window.width/2+(x-Game.instance().player.x()), window.height/2+(y-Game.instance().player.y()), character, color);
    }

    public abstract void tick();

    public int color() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }

    public void move(int x, int y) {
        if(Game.instance().world == null) return;
        Tile at = Game.instance().world.tileAt(this.x+x, this.y+y);
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
