package de.johannes.game.world;

import de.johannes.curses.util.ColorBuilder;
import de.johannes.curses.window.components.Window;
import de.johannes.game.entity.Entity;
import de.johannes.game.entity.Player;
import de.johannes.game.ui.GameWindow;
import de.johannes.game.world.structures.Room;
import de.johannes.game.world.structures.Structure;
import de.johannes.game.world.tiles.Material;
import de.johannes.game.world.tiles.Tile;

import java.util.ArrayList;
import java.util.List;

public class World {

    public List<Tile> floor;
    public List<Tile> tiles;
    public final int sizeX, sizeY;
    public boolean loading;

    private final List<Structure> structures;
    private final List<Entity> entities;

    public World(int sizeX, int sizeY) {
        this.floor = new ArrayList<>();
        this.tiles = new ArrayList<>();
        this.sizeX = sizeX;
        this.sizeY = sizeY;

        this.structures = new ArrayList<>();
        this.entities = new ArrayList<>();
    }

    public int tileIndex(int x, int y) {
        for (Tile tile : this.tiles) {
            if (tile.x() == x && tile.y() == y) return this.tiles.indexOf(tile);
        }
        ;
        return -1;
    }

    public int floorIndex(int x, int y) {
        for (Tile tile : this.floor) {
            if (tile.x() == x && tile.y() == y) return this.floor.indexOf(tile);
        }
        ;
        return -1;
    }

    public Tile tileAt(int x, int y) {
        if (tileIndex(x, y) == -1) return null;
        return this.tiles.get(tileIndex(x, y));
    }

    public Tile floorAt(int x, int y) {
        if (tileIndex(x, y) == -1) return null;
        return this.floor.get(floorIndex(x, y));
    }

    public void populate() {
        loading = true;

        addStructure(new Room(0, 0, 10, 10, 0));
        loading = false;
    }

    public void setTile(int x, int y, Material material) {
        int index = tileIndex(x, y);
        if (index != -1) {
            this.tiles.set(index, new Tile(material, x, y));
        } else {
            this.tiles.add(new Tile(material, x, y));
        }
    }

    public void setFloor(int x, int y, Material material) {
        int index = floorIndex(x, y);
        if (index != -1) {
            this.floor.set(index, new Tile(material, x, y));
        } else {
            this.floor.add(new Tile(material, x, y));
        }
    }

    public void tick() {
        for(Entity entity : this.entities()) {
            entity.tick();
        }
    }

    public void draw(GameWindow window, Player player) {
        int hiddenFloor = new ColorBuilder().defineForeground("#346475").defineBackground("#132344").build();
        int hiddenColor = new ColorBuilder().defineForeground("#4434A5").defineBackground("#132344").build();
        try {
            drawTileList(this.floor, window, player, hiddenFloor);
            drawTileList(this.tiles, window, player, hiddenColor);
            player.draw(window);
            for(Entity entity : this.entities) {
                if(entity.isAlive() &&
                ((entity.x() - player.x()) > -window.width / 2f + 1 &&
                        (entity.x() - player.x()) < window.width / 2f &&
                        (entity.y() - player.y()) > -window.height / 2f + 1 &&
                        (entity.y() - player.y()) < window.height / 2f
                )
                ) {
                    entity.draw(window);
                }else {
                    this.removeEntity(entity);
                }
            }
        } catch (Exception _) {
        }
    }

    public void drawTileList(List<Tile> tiles, Window window, Player player, int hiddenColor) {
        float viewRadius = Math.min(player.fov, 10);
        double horizontalShrink = 10 / (viewRadius == 10 ? 10 : Math.min(viewRadius * 1.5, 10));
        for (Tile tile : tiles) {
            if (tile.material() == null) continue;
            if (10 / viewRadius * (tile.x() - player.x()) > -window.width / 2f + 1 &&
                    10 / viewRadius * (tile.x() - player.x()) < window.width / 2f &&
                    horizontalShrink * (tile.y() - player.y()) > -window.height / 2f + 1 &&
                    horizontalShrink * (tile.y() - player.y()) < window.height / 2f
            )
            {
                tile.draw(window, player);
            } else if ((tile.x() - player.x()) > -window.width / 2f + 1 &&
                    (tile.x() - player.x()) < window.width / 2f &&
                    (tile.y() - player.y()) > -window.height / 2f + 1 &&
                    (tile.y() - player.y()) < window.height / 2f
            )
            {
                tile.draw(window, player, hiddenColor);
            }
        }
    }

    public void removeEntity(Entity entity) {
        this.entities.remove(entity);
    }

    public void addEntity(Entity entity) {
        this.entities.add(entity);
    }

    public List<Entity> entities() {
        return entities;
    }

    public void addStructure(Structure structure) {
        this.structures.add(structure);
        structure.build(this);
    }

    public List<Structure> structures() {
        return structures;
    }

    public Structure structureAt(int x, int y) {
        for(Structure structure : this.structures) {
            if(structure.x == x && structure.y == y) return structure;
        }
        return null;
    }

}
