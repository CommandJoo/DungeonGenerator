package de.johannes.game.ui;

import de.johannes.Game;
import de.johannes.curses.Curses;
import de.johannes.curses.CursesConstants;
import de.johannes.curses.Mouse;
import de.johannes.curses.ui.base.BoxComponent;
import de.johannes.curses.ui.base.BoxComponentBuilder;
import de.johannes.curses.ui.base.Component;
import de.johannes.curses.ui.components.Window;
import de.johannes.curses.util.ColorBuilder;
import de.johannes.curses.util.Timer;
import de.johannes.game.events.KeyEvent;
import de.johannes.game.events.MouseEvent;
import de.johannes.game.events.TickEvent;
import de.johannes.game.events.TileClickEvent;

public class GameWindow extends Window {

    public GameWindow() {
        super(null, "", -1, -1, Curses.width()+1, Curses.height()+1, CursesConstants.WHITE, CursesConstants.WHITE);
    }

    @Override
    public void init() {
        for(int i = 0; i < 10; i++) {
            addComponent(1000+i,new BoxComponentBuilder<Slot>().parent(this).at(width/2+((i-5)*7), height-5).bounds(5,2).color(color).build(Slot::new).index(i));
        }
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

            for(Component comp : this.getComponents()) {
                comp.draw();
            }
        }

        if(tickTimer.check(1000/20)) {
            Game.instance().eventManager().callEvent(new TickEvent());
            Game.instance().tick();
            tickTimer.reset();
        }
    }

    @Override
    public boolean handleKey(char c) {
        KeyEvent event = new KeyEvent(c);
        Game.instance().eventManager().callEvent(event);
        if(!event.isCanceled()) {
            Game.instance().player.input((char)event.key());
            return true;
        }
        return false;
    }

    @Override
    public boolean handleClick(Mouse mouse) {
        MouseEvent mouseEvent = new MouseEvent(mouse);
        Game.instance().eventManager().callEvent(mouseEvent);
        for(Component comp : this.getComponents()) {
            if(comp instanceof BoxComponent boxComponent) {
                if(mouse.x >= boxComponent.x() && mouse.x <= boxComponent.x()+boxComponent.width() &&
                mouse.y >= boxComponent.y() && mouse.y <= boxComponent.y()+boxComponent.height()) {
                    if(boxComponent.handleClick(mouse)) return true;
                }
            }
        }
        if(!mouseEvent.isCanceled()) {
            if(mouse.x < this.x || mouse.x > this.x+this.width || mouse.y < this.y || mouse.y > this.y+this.height) return false;
            int cX = ((mouse.x-this.x)/5)-(this.width/(Game.TILE_WIDTH*2))+((int)Game.instance().player.x());
            int cY = ((mouse.y-this.y)/2)-(this.height/(Game.TILE_HEIGHT*2))+((int)Game.instance().player.y());

            TileClickEvent clickEvent = new TileClickEvent(mouse, cX, cY);
            Game.instance().eventManager().callEvent(clickEvent);
        }

        return true;
    }
}
