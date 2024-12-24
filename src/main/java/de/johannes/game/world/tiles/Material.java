package de.johannes.game.world.tiles;

public class Material {

    private final String character;
    private final int color;
    private final boolean transparent;
    
    public Material(String character, int color, boolean transparent) {
        this.character = character;
        this.color = color;
        this.transparent = transparent;
    }

    public String character() {
        return character;
    }

    public int color() {
        return color;
    }

    public boolean transparent() {
        return transparent;
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Material) && (((Material) obj).character.equals(character)) && ((Material) obj).color==color;
    }
}
