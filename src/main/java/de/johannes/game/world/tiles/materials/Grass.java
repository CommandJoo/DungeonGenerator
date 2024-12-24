package de.johannes.game.world.tiles.materials;

import de.johannes.curses.util.ColorBuilder;
import de.johannes.game.world.tiles.Material;

public class Grass extends Material {
    public Grass() {
        super("M", new ColorBuilder().defineForeground("#00AA33").defineBackground("#99FF33").build(),true);
    }
}
