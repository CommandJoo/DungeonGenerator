package de.johannes.game.world.structures;

import de.johannes.game.world.World;
import de.johannes.game.world.tiles.Material;

public abstract class Structure {

    public final int x,y;
    
    public Structure(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public abstract void build(World world);

    public void fillRect(World world, boolean floor, int x, int y, int width, int height, Material mat) {
        for(int i = 0; i < width; i++) {
            for(int j = 0; j < height; j++) {
                if(floor) world.setFloor(x+i,y+j, mat);
                else world.setTile(x+i,y+j, mat);
            }
        }
    }

    public boolean checkCoords(int x, int y) {
        return this.x == x && this.y == y;
    }

}
