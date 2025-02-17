package de.johannes.game.entity;

import de.johannes.Game;
import de.johannes.Logger;
import de.johannes.curses.util.ColorBuilder;
import de.johannes.game.ui.GameWindow;
import de.johannes.game.world.tiles.Material;
import de.johannes.game.world.World;
import de.johannes.game.world.tiles.materials.Wood;

import java.lang.reflect.GenericArrayType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Player extends Entity {

    public Inventory inventory;
    public float fov;
    public float range;
    public float speed;

    public Player() {
        super("o", new ColorBuilder().defineForeground("#7E4A00").defineBackground("#7E4A00").build());
        this.inventory = new Inventory();
        this.fov = 8F;
        this.range = 6F;
        this.speed = 1;
    }

    @Override
    public void draw(GameWindow window) {
        for(int i = 0; i < Game.TILE_WIDTH; i++) {
            for(int j = 0; j < Game.TILE_HEIGHT; j++) {
                window.drawString(window.width()/2+i,window.height()/2+j, character(), color());
            }
        }
    }

    @Override
    public void tick() {
    }

    public void input(char c) {
        World world = Game.instance().world;
        switch (c) {
            case 'w':
                if(y()-1>-world.sizeY-1) Game.instance().player.move(0, -1*speed);
                break;
            case 's':
                if(y()+1<world.sizeY) Game.instance().player.move(0, 1*speed);
                break;
            case 'a':
                if(x()-1>-world.sizeX-1) Game.instance().player.move(-1*speed, 0);
                break;
            case 'd':
                if(x()+1<world.sizeX) Game.instance().player.move(1*speed, 0);
                break;
        }
    }

    public class Inventory {
        private final List<Item> items;
        private int selected = -1;
        
        public Inventory() {
            this.items = new ArrayList<>();
        }

        public void addInventory(Material mat) {
            for(int i = 0; i < items.size(); i++) {
                Item inInv = items.get(i);
                if(inInv.material().equals(mat)) {
                    items.set(items.indexOf(inInv), new Item(inInv.material(), inInv.count()+1));
                    return;
                }
            }
            if(items.size() < 16) {
                items.add(new Item(mat, 1));
            }
        }

        public Item getItem(int index) {
            return items.get(index);
        }

        public void setItem(int index, Item item) {
            items.set(index, item);
        }

        public List<Item> items() {
            return items;
        }

        public int selected() {
            return selected;
        }

        public void setSelected(int selected) {
            this.selected = selected;
        }
    }

    public static class Item {
        final Material material;
        final int count;

        public Item(Material material, int count) {
            this.material = material;
            this.count = count;
        }

        public Material material() {
            return material;
        }

        public int count() {
            return count;
        }
    }


}