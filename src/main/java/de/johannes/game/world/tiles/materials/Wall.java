package de.johannes.game.world.tiles.materials;

import de.johannes.curses.Curses;
import de.johannes.curses.CursesConstants;
import de.johannes.curses.util.ColorBuilder;
import de.johannes.game.world.tiles.Material;

public class Wall extends Material {
    public Wall(WallType type) {
        super(type.character(), new ColorBuilder().defineForeground("#564765").defineBackground("#778887").build(), false);
    }


    public enum WallType {
        VERTICAL(CursesConstants.LINE_VERTICAL),
        HORIZONTAL(CursesConstants.LINE_HORIZONTAL),
        UPPER_LEFT(CursesConstants.CORNER_UPPER_LEFT),
        UPPER_RIGHT(CursesConstants.CORNER_UPPER_RIGHT),
        LOWER_LEFT(CursesConstants.CORNER_LOWER_LEFT),
        LOWER_RIGHT(CursesConstants.CORNER_LOWER_RIGHT);

        private String character;

        private WallType(Character character) {
            this.character = character.toString();
        }

        public String character() {
            return character;
        }
    }
}
