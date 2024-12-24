package de.johannes.game.world.tiles.materials;

import de.johannes.curses.util.ColorBuilder;
import de.johannes.game.world.tiles.Material;

public class Floor extends Material {
    public Floor() {
        super("=", ColorBuilder.create().defineBackground("#615f5f").defineForeground("#393838").build(), true);
    }
}
