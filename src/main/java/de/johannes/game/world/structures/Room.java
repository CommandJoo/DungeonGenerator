package de.johannes.game.world.structures;

import de.johannes.game.world.World;
import de.johannes.game.world.tiles.Material;
import de.johannes.game.world.tiles.materials.Floor;
import de.johannes.game.world.tiles.materials.Grass;
import de.johannes.game.world.tiles.materials.Leaf;
import de.johannes.game.world.tiles.materials.Wall;

import java.util.Random;

public class Room extends Structure{

    private int level;
    private int sizeX, sizeY;

    public Room(int x, int y, int sizeX, int sizeY, int level) {
        super(x, y);
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.level = level;
    }

    @Override
    public void build(World world) {
        if(level < 6) {
            fillRect(world, false, x-(sizeX+1), y-(sizeY/2+1), 2*(sizeX+1)+1, sizeY+2, null);
            fillRect(world, true, x-(sizeX+1), y-(sizeY/2+1), 2*(sizeX+1)+1, sizeY+2, new Floor());

            fillRect(world, false, x-(sizeX+1), y-(sizeY/2+1), 2*(sizeX+1), 1, new Wall(Wall.WallType.HORIZONTAL));
            fillRect(world, false, x-(sizeX+1), y+(sizeY/2+1), 2*(sizeX+1), 1, new Wall(Wall.WallType.HORIZONTAL));

            fillRect(world, false, x-(sizeX+1), y-(sizeY/2+1), 1, sizeY+2, new Wall(Wall.WallType.VERTICAL));
            fillRect(world, false, x+(sizeX+1), y-(sizeY/2+1), 1, sizeY+2, new Wall(Wall.WallType.VERTICAL));

            world.setTile(x-(sizeX+1), y-(sizeY/2+1), new Wall(Wall.WallType.UPPER_LEFT));
            world.setTile(x+(sizeX+1), y-(sizeY/2+1), new Wall(Wall.WallType.UPPER_RIGHT));
            world.setTile(x-(sizeX+1), y+(sizeY/2+1), new Wall(Wall.WallType.LOWER_LEFT));
            world.setTile(x+(sizeX+1), y+(sizeY/2+1), new Wall(Wall.WallType.LOWER_RIGHT));
            if(level+1 < 6) {
                for(int i = 0; i < new Random().nextInt(4)+1; i++) {
                    int a = new Random().nextInt(4);
                    if(a==0) {
                        fillRect(world, false, x+(sizeX+1), y-1, 1, 3, null);
                        world.addStructure(new Tunnel(x+20, y, 4, true, true, level));
                    }
                    else if(a==1) {
                        fillRect(world, false, x-(sizeX+1), y-1, 1, 3, null);
                        world.addStructure(new Tunnel(x-20, y, 4, true, false, level));
                    }
                    if(a==2) {
                        fillRect(world, false, x-1, y+(sizeY/2+1), 3, 1, null);
                        world.addStructure(new Tunnel(x, y+10, 4, false, true, level));
                    }
                    if(a==3) {
                        fillRect(world, false, x-1, y-(sizeY/2+1), 3, 1, null);
                        world.addStructure(new Tunnel(x, y-10, 4, false, false, level));
                    }
                }
            }
            if(world.structureAt(x+20, y) instanceof Tunnel) {
                fillRect(world, false, x+(sizeX+1), y-1, 1, 3, null);
            }
            if(world.structureAt(x-20, y) instanceof Tunnel) {
                fillRect(world, false, x-(sizeX+1), y-1, 1, 3, null);
            }
            if(world.structureAt(x,y+10) instanceof Tunnel) {
                fillRect(world, false, x-1, y+(sizeY/2+1), 3, 1, null);
            }
            if(world.structureAt(x,y-10) instanceof Tunnel) {
                fillRect(world, false, x-1, y-(sizeY/2+1), 3, 1, null);
            }
        }
    }
}
