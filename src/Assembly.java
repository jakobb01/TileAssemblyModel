import java.util.Queue;

public class Assembly {
    private Tile seed;
    private Queue<Tile> queue;

    public Tile getSeed() {
        return seed;
    }

    public void setSeed(Tile seed) {
        this.seed = seed;
    }

    public Queue<Tile> getQueue() {
        return queue;
    }

    public void setQueue(Queue<Tile> queue) {
        this.queue = queue;
    }
}
