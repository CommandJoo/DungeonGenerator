package de.johannes.game.world.tiles.materials;

import de.johannes.curses.util.ColorBuilder;
import de.johannes.game.world.tiles.Material;

public class Leaf extends Material {
    public Leaf() {
        super("#", new ColorBuilder().defineForeground("#007744").defineBackground("#00AA33").build(),true);
    }
}
