public class Tile {
    private int[] colors;
    private int[] glue;
    private Tile[] neighbors;

    public Tile() {
        colors = new int[4]; // initialize colors array with default values
        glue = new int[4]; // initialize glue array with default values
        neighbors = new Tile[4]; // initialize neighbors array with null values
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

    public Tile[] getNeighbors() {
        return neighbors;
    }

    public void setNeighbors(Tile[] neighbors) {
        this.neighbors = neighbors;
    }
}
