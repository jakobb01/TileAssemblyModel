package com.jakobbeber.tam;

import com.google.common.collect.Table;

import java.util.Queue;
import java.util.Set;

public class Assembly {


    private Set<Duo> queue;
    private Table<Integer, Integer, Tile> table;
    private Tile[] tiles;



    // starting tiles to choose from
    public void firstsetQueue() {
        // we put 0, 1 and 1, 0 into queue
        queue.add(new Duo(0, -1));
        queue.add(new Duo(1, 0));
    }

    public Set<Duo> getQueue() {
        return queue;
    }

    public void setQueue(Set<Duo> queue) {
        this.queue = queue;
    }

    public Table<Integer, Integer, Tile> getTable() {
        return table;
    }

    public void setTable(Table<Integer, Integer, Tile> table) {
        this.table = table;
    }

    public Tile[] getTiles() {
        return tiles;
    }

    public void setTiles(Tile[] tiles) {
        this.tiles = tiles;
    }


}

