package de.johannes.game.world.tiles.materials;

import de.johannes.curses.util.ColorBuilder;
import de.johannes.game.world.tiles.Material;

public class Rock extends Material {
    public Rock() {
        super("R", ColorBuilder.create().defineBackground("#667688").defineForeground("#444242").build(), false);
    }
}
