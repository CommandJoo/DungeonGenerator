package de.johannes.game.ui;

import de.johannes.Logger;
import de.johannes.Main;
import de.johannes.commons.util.Files;
import de.johannes.curses.Curses;
import de.johannes.curses.CursesConstants;
import de.johannes.curses.ui.components.Text;
import de.johannes.curses.ui.components.Window;

import java.util.ArrayList;

public class LogWindow extends Window {

    public LogWindow() {
        super(null, "Log", Curses.width()/2-100, Curses.height()/2-20, 200, 40, CursesConstants.DARK_RED, CursesConstants.DARK_RED);
    }

    @Override
    public void init() {

    }

    @Override
    public void draw() {
        Curses.clearBox(x(),y(), width(), height());
        super.drawBox();
        ArrayList<String> log = (ArrayList<String>) Logger.log();
        for(int i = Math.max(log.size()-38, 0); i < log.size(); i++) {
            Text.of(log.get(i)).parent(this).at(1,1+i).setColor(color()).draw();
        }
    }

    @Override
    public boolean handleKey(char c) {
        return false;
    }
}
