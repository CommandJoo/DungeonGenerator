package de.johannes.game.ui;

import de.johannes.Game;
import de.johannes.curses.Curses;
import de.johannes.curses.CursesConstants;
import de.johannes.curses.Mouse;
import de.johannes.curses.util.ColorBuilder;
import de.johannes.curses.util.Timer;
import de.johannes.curses.window.components.Window;
import de.johannes.game.entity.Player;
import de.johannes.game.world.tiles.Material;

public class GameWindow extends Window {

    public GameWindow() {
        super(null, 22, 0, Curses.width()-23, Curses.height()-1, CursesConstants.WHITE, "", false);
    }

    private final Timer tickTimer = new Timer();
    private final Timer loadTimer = new Timer();
    @Override
    public void draw() {
        Curses.clearBox(x+1,y+1, width-1, height-1, new ColorBuilder().defineBackground("#000000").build());

        if(Game.instance().world != null && Game.instance().world.loading) {
            int col = new ColorBuilder().defineBackground("#676789").defineForeground("#FFFFFF").build();
            Curses.clearBox(x+1,y+1, width-1, height-1, col);
            drawString( width/2-("Creating World".length()/2), height/2, "Creating World"+(loadTimer.check(300) ? loadTimer.check(600) ? "..." : ".." : "."), col);
            if(loadTimer.check(900)) loadTimer.reset();
        }

        if((Game.instance().world != null && !Game.instance().world.loading) && Game.instance().player != null) {
            Game.instance().world.draw(this, Game.instance().player);
        }
        if(tickTimer.check(1000/20)) {
            Game.instance().tick();
            tickTimer.reset();
        }
    }

    @Override
    public boolean handleKey(char c) {
        Player player = Game.instance().player;
        player.input(c);
        return true;
    }

    @Override
    public boolean handleClick(Mouse mouse) {
        if(mouse.x < this.x || mouse.x > this.x+this.width || mouse.y < this.y || mouse.y > this.y+this.height) return false;
        int x = (mouse.x-this.x)-width/2+Game.instance().player.x();
        int y = (mouse.y-this.y)-height/2+Game.instance().player.y();

        if(Game.instance().world.tileAt(x,y) != null && Game.instance().world.tileAt(x,y).material() != null) {
            Material mat = Game.instance().world.tileAt(x,y).material();

            if(Game.instance().world.tileAt(x,y).distance(Game.instance().player.x(), Game.instance().player.y()) < Game.instance().player.range) {
                if(mouse.state == Mouse.BUTTON_1_CLICKED) {
                    //TODO Check if tile is breakable
                    Game.instance().player.inventory.addInventory(mat);
                    int index = Game.instance().world.tileIndex(x,y);
                    Game.instance().world.tiles.remove(index);
                }
            }
        }

        return true;
    }
}
