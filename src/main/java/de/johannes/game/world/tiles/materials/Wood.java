package de.johannes.game.world.tiles.materials;

import de.johannes.curses.util.ColorBuilder;
import de.johannes.game.world.tiles.Material;

public class Wood extends Material {
    public Wood() {
        super("|", new ColorBuilder().defineBackground("#7F3a00").defineForeground("#340100").build(), false);
    }
}
