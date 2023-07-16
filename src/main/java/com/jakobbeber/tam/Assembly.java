package com.jakobbeber.tam;

import com.google.common.collect.Table;

import java.util.*;

public class Assembly {


    private List<Map.Entry<Integer,Integer>> queue;
    private Table<Integer, Integer, Tile> table;
    private Tile[] tiles;

    private Queue<int []> conveyor;



    // starting tiles to choose from
    public void firstQueue() {
        // we put 0, 1 and 1, 0 into queue
        queue = new ArrayList<>();
        queue.add(new AbstractMap.SimpleEntry<>(0, 1));
        queue.add(new AbstractMap.SimpleEntry<>(1, 0));
    }

    public List<Map.Entry<Integer, Integer>> getQueue() {
        return queue;
    }

    public void setQueue(List<Map.Entry<Integer, Integer>> queue) {
        this.queue = queue;
    }

    public void addQueue(int x, int y) {
        this.queue.add(new AbstractMap.SimpleEntry<>(x, y));
    }

    public boolean containsQueue(int x, int y) {
        return this.queue.contains(new AbstractMap.SimpleEntry<>(x, y));
    }

    public void removeQueue(Map.Entry<Integer, Integer> remove) {
        this.queue.remove(remove);
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

    public Queue<int[]> getConveyor() {
        return conveyor;
    }

    public void setConveyor(Queue<int[]> conveyor) {
        this.conveyor = conveyor;
    }
}

