package de.johannes.game.world.tiles;

import de.johannes.Game;
import de.johannes.curses.Curses;

import java.util.Arrays;

public class Material {

    private final int color;
    private final boolean transparent;
    private final char[][] text = new char[5][2];
    
    public Material(String character, int color, boolean transparent) {
        fill(!character.isEmpty() ? character.toCharArray()[0] : ' ');
        this.color = color;
        this.transparent = transparent;
    }


    public Material(char[][] texture, int color, boolean transparent) {
        fill(texture);
        this.color = color;
        this.transparent = transparent;
    }

    private void fill(char[][] texture) {
        if(texture.length < Game.TILE_WIDTH) {
            Curses.kill();
            throw new RuntimeException("Invalid Texture width");
        }
        if(texture[0].length < Game.TILE_HEIGHT) {
            Curses.kill();
            throw new RuntimeException("Invalid Texture height");
        }

        for(int i = 0; i < Game.TILE_WIDTH;i++) {
            for(int j = 0; j < Game.TILE_HEIGHT; j++) {
                this.text[i][j] = texture[i][j];
            }
        }
    }

    private void fill(char texture) {
        for(int i = 0; i < Game.TILE_WIDTH;i++) {
            for(int j = 0; j < Game.TILE_HEIGHT; j++) {
                this.text[i][j] = texture;
            }
        }
    }


    public int color() {
        return color;
    }

    public boolean transparent() {
        return transparent;
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Material) && (Arrays.deepEquals(((Material) obj).text, text)) && ((Material) obj).color==color;
    }

    public String texture(int x, int y) {
        return text[x][y] != 0 ? ""+text[x][y] : " ";
    }

    public String nonempty() {
        for(int i = 0; i < Game.TILE_WIDTH; i++) {
            for(int j = 0; j < Game.TILE_HEIGHT; j++) {
                if(!texture(i,j).isEmpty()) return texture(i,j);
            }
        }
        return "";
    }
}
