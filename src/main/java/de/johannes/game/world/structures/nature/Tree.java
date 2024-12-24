package de.johannes.game.world.structures.nature;

import de.johannes.game.world.World;
import de.johannes.game.world.structures.Structure;
import de.johannes.game.world.tiles.Material;
import de.johannes.game.world.tiles.materials.Leaf;
import de.johannes.game.world.tiles.materials.Wood;

import java.util.Random;

public class Tree extends Structure {
    private static final Material wood = new Wood();
    private static final Material leaf = new Leaf();

    public Tree(int x, int y) {
        super(x, y);
    }

    @Override
    public void build(World world) {
        int height = new Random().nextInt(3) + 3;
        fillRect(world, false, x-2, y-height-1, 5, 3, leaf);
        for (int j = 0; j < height; j++) {
            world.setTile(x, y - j, wood);
        }
    }
}
