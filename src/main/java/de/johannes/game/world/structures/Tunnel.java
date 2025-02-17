package de.johannes.game.world.structures;

import de.johannes.game.world.World;
import de.johannes.game.world.tiles.materials.Floor;
import de.johannes.game.world.tiles.materials.Rock;

public class Tunnel extends Structure {

    private int thickness;
    private boolean horizontal, add;
    private int level;


    public Tunnel(int x, int y, int thickness, boolean horizontal, boolean add, int level) {
        super(x, y);
        this.thickness = thickness;
        this.horizontal = horizontal;
        this.add = add;
        this.level = level;
    }

    @Override
    public void build(World world) {
        int roomSize = 10;
        if (horizontal) {
            world.addStructure(new Room(x + (add ? 15 + roomSize / 2 : -1 * (15 + roomSize / 2)), y, roomSize, roomSize, this.level + 1));

            fillRect(world, false, x - 9, (int) (y - (thickness / 2F)), 19, thickness, null);
            fillRect(world, true, x - 9, (int) (y - (thickness / 2F)), 19, thickness, new Floor());

            fillRect(world, false, x - 9, (int) (y - (thickness / 2F)), 19, 1, new Rock());
            fillRect(world, false, x - 9, (int) (y + (thickness / 2F)), 19, 1, new Rock());

            world.setTile(x - 9, (int) (y - (thickness / 2F)), new Rock());
            world.setTile(x - 9, (int) (y + (thickness / 2F)), new Rock());
            world.setTile(x + 9, (int) (y - (thickness / 2F)), new Rock());
            world.setTile(x + 9, (int) (y + (thickness / 2F)), new Rock());
        } else {
            world.addStructure(new Room(x, y + (add ? 5 + (roomSize / 2) : -1 * (5 + (roomSize / 2))), roomSize, roomSize, this.level + 1));

            fillRect(world, false, (int) (x - (thickness / 2F)), y - 4, thickness, 9, null);
            fillRect(world, true, (int) (x - (thickness / 2F)), y - 4, thickness, 9, new Floor());

            fillRect(world, false, (int) (x - (thickness / 2F)), y - 4, 1, 9, new Rock());
            fillRect(world, false, (int) (x + (thickness / 2F)), y - 4, 1, 9, new Rock());

            world.setTile((int) (x - (thickness / 2F)), y + 4, new Rock());
            world.setTile((int) (x - (thickness / 2F)), y - 4, new Rock());
            world.setTile((int) (x + (thickness / 2F)), y + 4, new Rock());
            world.setTile((int) (x + (thickness / 2F)), y - 4, new Rock());
        }
    }
}
