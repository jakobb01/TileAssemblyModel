package com.jakobbeber.tam;

public class Tile {
    private int[] colors;
    private int[] glue;
    private String name;

    public Tile() {
        colors = new int[4]; // initialize colors array with default values
        glue = new int[4]; // initialize glue array with default values
        name = "Test";
    }

    public int[] getColors() {
        return colors;
    }

    public void setColors(int[] colors) {
        this.colors = colors;
    }

    public int[] getGlue() {
        return glue;
    }

    public void setGlue(int[] glue) {
        this.glue = glue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
